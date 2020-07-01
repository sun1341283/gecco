package com.geccocrawler.gecco.entity.detail;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.base.GetInfo;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

import static com.geccocrawler.gecco.config.TargetSites.AMERICA_VOICE;

/**
 * @Author: lolo
 * @Date: 2020/6/29 0029
 */
@Gecco(matchUrl = "https://www.voachinese.com/a/{article}/{num.html}", pipelines = "redisPipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class AmericaVoiceDetail implements HtmlBean, GetInfo {

    private String operator = AMERICA_VOICE;

    @Text
    @HtmlField(cssPath = "#content > div:nth-child(1) > div.hdr-container > div > div.col-title.col-xs-12.col-md-10.pull-right > h1")
    private String title;

    @Html
    @HtmlField(cssPath = "#article-content > div.wsw")
    private String content;
}
