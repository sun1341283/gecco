package com.geccocrawler.gecco.pipeline;

import com.geccocrawler.gecco.spider.SpiderBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("myConsolePipeline")
@Slf4j
public class ConsolePipeline implements Pipeline<SpiderBean> {

    @SneakyThrows
    @Override
    public void process(SpiderBean bean) {
        log.info("get titles from {},total num:{}",bean.getClass().getDeclaredField("operator").get(bean),bean.getClass().getDeclaredFields().length);
    }

}