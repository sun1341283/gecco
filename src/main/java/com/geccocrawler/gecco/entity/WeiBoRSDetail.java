package com.geccocrawler.gecco.entity;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.base.GetInfo;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lolo
 * @Date: 2020/6/24 0024
 */
@Gecco(matchUrl = "https://s.weibo.com/weibo?q={key}&Refer={top}", pipelines = "redisPipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class WeiBoRSDetail implements HtmlBean , GetInfo {
    private String operator = "tx_weibo";

    @Text
    @HtmlField(cssPath = "#pl_topic_header > div.card-topic-a > div > div.title > h1 > a")
    private String title;

    @Html
    @HtmlField(cssPath = "#pl_feedlist_index > div:nth-child(1) > div:nth-child(2) > div.card > div.card-feed")
    private String content;


}
