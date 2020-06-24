package com.geccocrawler.gecco.spring;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lolo
 * @Date: 2020/6/24 0024
 */
@Gecco(matchUrl = "https://s.weibo.com/weibo?q=%23{key}%23&Refer={top}", pipelines = "consolePipeline")
@Getter
@Setter
public class WeiBoRSDetail implements HtmlBean {

    @Text
    @HtmlField(cssPath = "#pl_topic_header > div.card-topic-a > div > div.title > h1 > a")
    private String title;

    @Text
    @HtmlField(cssPath = "body")
    private String content;
}
