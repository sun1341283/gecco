package com.geccocrawler.gecco.spring;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * @Author: lolo
 * @Date: 2020/6/24 0024
 */
@Gecco(matchUrl = "https://www.baidu.com", pipelines = "consolePipeline")
public class baidurs implements HtmlBean {

    @Href(click=false)
    @HtmlField(cssPath = "#s_xmancard_news_new > div > div.s-news-rank-wrapper.s-news-special-rank-wrapper.c-container-r > ul > li:nth-child(1) > a ")
    private String title;


    @Href(click=false)
    @HtmlField(cssPath="#s-top-left > a:nth-child(1)")
    private String test;

    @Text
    @HtmlField(cssPath = "#s-top-left > a:nth-child(1)")
    private String test1;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }
}
