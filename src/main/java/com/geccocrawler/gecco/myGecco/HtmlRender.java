package com.geccocrawler.gecco.myGecco;

import com.geccocrawler.gecco.myGecco.html.AjaxFieldRender;
import com.geccocrawler.gecco.myGecco.html.HtmlFieldRender;
import com.geccocrawler.gecco.myGecco.html.ImageFieldRender;
import com.geccocrawler.gecco.myGecco.html.JSVarFieldRender;
import net.sf.cglib.beans.BeanMap;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;

/**
 * 将下载下来的html映射到bean中
 *
 * @author huchengyi
 *
 */
public class HtmlRender extends AbstractRender {

    private HtmlFieldRender htmlFieldRender;

    private AjaxFieldRender ajaxFieldRender;

    private JSVarFieldRender jsVarFieldRender;

    private ImageFieldRender imageFieldRender;

    public HtmlRender() {
        super();
        this.htmlFieldRender = new HtmlFieldRender();
        this.ajaxFieldRender = new AjaxFieldRender();
        this.jsVarFieldRender = new JSVarFieldRender();
        this.imageFieldRender = new ImageFieldRender();
    }

    @Override
    public void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
        htmlFieldRender.render(request, response, beanMap, bean);
        ajaxFieldRender.render(request, response, beanMap, bean);
        jsVarFieldRender.render(request, response, beanMap, bean);
        imageFieldRender.render(request, response, beanMap, bean);
    }

}
