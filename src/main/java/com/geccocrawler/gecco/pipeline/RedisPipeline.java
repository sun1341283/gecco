package com.geccocrawler.gecco.pipeline;

import com.geccocrawler.gecco.base.GetInfo;
import com.geccocrawler.gecco.entity.WeiBoRSDetail;
import com.geccocrawler.gecco.spider.SpiderBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.geccocrawler.gecco.config.Constance.TITLE_CONTENT_DELIMITER;

@Service("redisPipeline")
public class RedisPipeline implements Pipeline{
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ListOperations listOperations;

    @Autowired
    private ZSetOperations zSetOperations;

    @Override
    public void process(SpiderBean bean) {
        GetInfo info = (GetInfo)bean;
        String list_key_operator = info.getOperator();
        String list_value_ylsl20200625_content = info.getTitle()+TITLE_CONTENT_DELIMITER+info.getContent();
        zSetOperations.add(list_key_operator,list_value_ylsl20200625_content,System.currentTimeMillis());
    }
}
