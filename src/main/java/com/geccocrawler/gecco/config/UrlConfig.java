package com.geccocrawler.gecco.config;

import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlConfig {
    @Bean
    public SpringGeccoEngine initGecco() {
        return new SpringGeccoEngine() {
            @Override
            public void init() {
                com.geccocrawler.gecco.myGecco.GeccoEngine.create()
                        .pipelineFactory(springPipelineFactory)
                        .classpath("com.geccocrawler.gecco.spring")
                        .start("https://s.weibo.com/top/summary?cate=realtimehot")
                        .interval(60_000)
                        .loop(true)
                        .start();
            }
        };
    }
}
