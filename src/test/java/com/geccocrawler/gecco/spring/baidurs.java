package com.geccocrawler.gecco.spring;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lolo
 * @Date: 2020/6/24 0024
 */
@Gecco(matchUrl = "https://www.baidu.com", pipelines = "consolePipeline")
@Getter
@Setter
public class baidurs implements HtmlBean {

    @Text
    @HtmlField(cssPath = ".s-news-rank-content > li:nth-child(1) > a > span.title-content-title")
    private String title;


    @Href(click=false)
    @HtmlField(cssPath="#s-top-left > a:nth-child(1)")
    private String test;

    @Text
    @HtmlField(cssPath = "#s-top-left > a:nth-child(1)")
    private String test1;





}
