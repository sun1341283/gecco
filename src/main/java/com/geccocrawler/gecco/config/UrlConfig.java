package com.geccocrawler.gecco.config;

import com.geccocrawler.gecco.myGecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UrlConfig {

    @Bean
    public SpringGeccoEngine bbczhongwen() {
        return new SpringGeccoEngine() {
            @Override
            public void init() {
                System.out.println("开始初始化客户端");
                GeccoEngine.create()
                        .pipelineFactory(springPipelineFactory)
                        .classpath("com.geccocrawler.gecco.spring")
                        .interval(5_000)
                        .loop(true).start();
            }
        };
    }
}
