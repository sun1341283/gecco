package com.geccocrawler.gecco.entity;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lolo
 * @Date: 2020/6/24 0024
 */
@Gecco(matchUrl = "https://s.weibo.com/top/summary?cate=realtimehot", pipelines = "consolePipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class WeiboRS implements HtmlBean {

    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(1) > td.td-02 > a")
    private String title1;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(2) > td.td-02 > a")
    private String title2;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(3) > td.td-02 > a")
    private String title3;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(4) > td.td-02 > a")
    private String title4;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(5) > td.td-02 > a")
    private String title5;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(6) > td.td-02 > a")
    private String title6;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(7) > td.td-02 > a")
    private String title7;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(8) > td.td-02 > a")
    private String title8;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(9) > td.td-02 > a")
    private String title9;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(10) > td.td-02 > a")
    private String title10;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(11) > td.td-02 > a")
    private String title11;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(12) > td.td-02 > a")
    private String title12;
    @Href(click = true)
    @HtmlField(cssPath = "#pl_top_realtimehot > table > tbody > tr:nth-child(13) > td.td-02 > a")
    private String title13;
}
