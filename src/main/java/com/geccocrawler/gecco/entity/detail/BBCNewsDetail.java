package com.geccocrawler.gecco.entity.detail;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.base.GetInfo;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

import static com.geccocrawler.gecco.config.TargetSites.BBC_NEWS;

/**
 * @Author: lolo
 * @Date: 2020/6/28 0028
 */
@Gecco(matchUrl = "https://www.bbc.com/zhongwen/simp/world-{article_num}", pipelines = "redisPipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class BBCNewsDetail implements HtmlBean, GetInfo {
    private String operator = BBC_NEWS;

    @Text
    @HtmlField(cssPath = "#page > div > div.container > div > div.column--primary > div.story-body > h1")
    private String title;

    @Html
    @HtmlField(cssPath = "#page > div > div.container > div > div.column--primary > div.story-body > div.story-body__inner")
    private String content;


}
