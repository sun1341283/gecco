package com.geccocrawler.gecco.myGecco;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.util.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

/**
 * 派生队列上下文，可以在运行时将请求放入派生队列
 *
 * @author huchengyi
 */
public class DeriveSchedulerContext {

    private static String cacheUrls = "cacheUrls";

    @Autowired
    private static ValueOperations valueOperations = SpringBeanUtils.getBean(ValueOperations.class);

    @Autowired
    private static HashOperations<String,String,String > hashOperations = SpringBeanUtils.getBean(HashOperations.class);

    public static void into(HttpRequest request) {
        if (hashOperations.get(cacheUrls,request.getUrl()) == null){
            hashOperations.put(cacheUrls,request.getUrl(),"");
            SpiderThreadLocal.get().getSpiderScheduler().into(request);
        }
    }

}

