package com.geccocrawler.gecco.pipeline;

import com.geccocrawler.gecco.base.GetInfo;
import com.geccocrawler.gecco.spider.SpiderBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import static com.geccocrawler.gecco.config.Constance.TITLE_CONTENT_DELIMITER;
import static com.geccocrawler.gecco.config.TargetSites.TX_WEIBO;

@Service("redisPipeline")
@Slf4j
public class RedisPipeline implements Pipeline {
    private static final String NULL_STRING = "null";

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Override
    public void process(SpiderBean bean) {
        GetInfo info = (GetInfo) bean;
        String operator = info.getOperator();
        String title = info.getTitle();
        if (title == null) {
            log.warn("title is null! operator is {}",operator);
            return;
        }
        if (operator.equals(TX_WEIBO)) {
            title = title.replaceFirst("#", "");
            title = title.replaceFirst("#", "");
        }
        String value = title + TITLE_CONTENT_DELIMITER + info.getContent();
        zSetOperations.add(operator, value, System.currentTimeMillis());
        log.info("get detail from {},title is {}", ((GetInfo) bean).getOperator(), ((GetInfo) bean).getTitle());
    }
}
