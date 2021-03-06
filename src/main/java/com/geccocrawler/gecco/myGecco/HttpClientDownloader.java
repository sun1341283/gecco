package com.geccocrawler.gecco.myGecco;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.geccocrawler.gecco.downloader.AbstractDownloader;
import com.geccocrawler.gecco.downloader.DownloadException;
import com.geccocrawler.gecco.downloader.DownloadServerException;
import com.geccocrawler.gecco.downloader.UserAgent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.CharArrayBuffer;

import com.geccocrawler.gecco.downloader.proxy.Proxys;
import com.geccocrawler.gecco.downloader.proxy.ProxysContext;
import com.geccocrawler.gecco.request.HttpPostRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.utils.UrlUtils;

/**
 * 利用httpclient下载
 *
 * @author huchengyi
 *
 */
@com.geccocrawler.gecco.annotation.Downloader("myhttpClientDownloader")
public class HttpClientDownloader extends AbstractDownloader {

    private static Log log = LogFactory.getLog(com.geccocrawler.gecco.downloader.HttpClientDownloader.class);

    private CloseableHttpClient httpClient;

    private HttpClientContext cookieContext;

    public HttpClientDownloader() {

        cookieContext = HttpClientContext.create();
        cookieContext.setCookieStore(new BasicCookieStore());

        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
        try {
            //构造一个信任所有ssl证书的httpclient
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf)
                    .build();
        } catch(Exception ex) {
            socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", SSLConnectionSocketFactory.getSocketFactory())
                    .build();
        }
        RequestConfig clientConfig = RequestConfig.custom().setRedirectsEnabled(false).build();
        PoolingHttpClientConnectionManager syncConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        syncConnectionManager.setMaxTotal(1000);
        syncConnectionManager.setDefaultMaxPerRoute(50);
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(clientConfig)
                .setConnectionManager(syncConnectionManager)
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                        int retryCount = SpiderThreadLocal.get().getEngine().getRetry();
                        boolean retry = (executionCount <= retryCount);
                        if(log.isDebugEnabled() && retry) {
                            log.debug("retry : " + executionCount);
                        }
                        return retry;
                    }
                }).build();
    }

    @Override
    public HttpResponse download(HttpRequest request, int timeout) throws DownloadException {
        if(log.isDebugEnabled()) {
            log.debug("downloading..." + request.getUrl());
        }
        HttpRequestBase reqObj = null;
        if(request instanceof HttpPostRequest) {//post
            HttpPostRequest post = (HttpPostRequest)request;
            reqObj = new HttpPost(post.getUrl());
            //post fields
            List<NameValuePair> fields = new ArrayList<NameValuePair>();
            for(Map.Entry<String, String> entry : post.getFields().entrySet()) {
                NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
                fields.add(nvp);
            }
            try {
                HttpEntity entity = new UrlEncodedFormEntity(fields, "UTF-8");
                ((HttpEntityEnclosingRequestBase) reqObj).setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {//get
            reqObj = new HttpGet(request.getUrl());
        }
        //header
        boolean isMobile = SpiderThreadLocal.get().getEngine().isMobile();
        reqObj.addHeader("User-Agent", UserAgent.getUserAgent(isMobile));
        for(Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            reqObj.setHeader(entry.getKey(), entry.getValue());
        }
        //request config
        HttpHost proxy = new HttpHost("119.3.108.241",20207);
        RequestConfig.Builder builder = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)//从连接池获取连接的超时时间
                .setSocketTimeout(timeout)//获取内容的超时时间
                .setConnectTimeout(timeout)//建立socket连接的超时时间
                .setRedirectsEnabled(false).setProxy(proxy);
        //proxy


        reqObj.setConfig(builder.build());
        //request and response
        try {
            for(Map.Entry<String, String> entry : request.getCookies().entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
                cookie.setPath("/");
                cookie.setDomain(reqObj.getURI().getHost());
                cookieContext.getCookieStore().addCookie(cookie);
            }
            org.apache.http.HttpResponse response = httpClient.execute(reqObj, cookieContext);
            int status = response.getStatusLine().getStatusCode();
            HttpResponse resp = new HttpResponse();
            resp.setStatus(status);
            if(status == 302 || status == 301) {
                String redirectUrl = response.getFirstHeader("Location").getValue();
                resp.setContent(UrlUtils.relative2Absolute(request.getUrl(), redirectUrl));
            } else if(status == 200) {
                HttpEntity responseEntity = response.getEntity();
                ByteArrayInputStream raw = toByteInputStream(responseEntity.getContent());
                resp.setRaw(raw);
                String contentType = null;
                Header contentTypeHeader = responseEntity.getContentType();
                if(contentTypeHeader != null) {
                    contentType = contentTypeHeader.getValue();
                }
                resp.setContentType(contentType);
                if(!isImage(contentType)) {
                    String charset = getCharset(request.getCharset(), contentType);
                    resp.setCharset(charset);
                    //String content = EntityUtils.toString(responseEntity, charset);
                    String content = getContent(raw, responseEntity.getContentLength(), charset);
                    resp.setContent(content);
                }
            } else {
                //404，500等
                if(proxy != null) {
                }
                throw new DownloadServerException("" + status);
            }
            if(proxy != null) {
            }
            return resp;
        } catch (IOException e) {
            //超时等
            if(proxy != null) {
            }
            throw new DownloadException(e);
        } finally {
            reqObj.releaseConnection();
        }
    }

    @Override
    public void shutdown() {
        try {
            httpClient.close();
        } catch (IOException e) {
            httpClient = null;
        }
    }

    public String getContent(InputStream instream, long contentLength, String charset) throws IOException {
        try {
            if (instream == null) {
                return null;
            }
            int i = (int)contentLength;
            if (i < 0) {
                i = 4096;
            }
            Reader reader = new InputStreamReader(instream, charset);
            CharArrayBuffer buffer = new CharArrayBuffer(i);
            char[] tmp = new char[1024];
            int l;
            while((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.reset();
        }

    }

    private boolean isImage(String contentType) {
        if(contentType == null) {
            return false;
        }
        if(contentType.toLowerCase().startsWith("image")) {
            return true;
        }
        return false;
    }
}

