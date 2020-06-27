package com.geccocrawler.gecco.myGecco;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.util.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * 派生队列上下文，可以在运行时将请求放入派生队列
 *
 * @author huchengyi
 */
public class DeriveSchedulerContext {


    @Autowired
    private static ValueOperations valueOperations = SpringBeanUtils.getBean(ValueOperations.class);


    public static void into(HttpRequest request) {
        if (valueOperations.get(request.getUrl()) == null) {
            valueOperations.set(request.getUrl(),"");
            SpiderThreadLocal.get().getSpiderScheduler().into(request);
        }
    }

}

