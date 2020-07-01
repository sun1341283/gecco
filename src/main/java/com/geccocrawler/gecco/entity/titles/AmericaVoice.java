package com.geccocrawler.gecco.entity.titles;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

import static com.geccocrawler.gecco.config.TargetSites.AMERICA_VOICE;

/**
 * @Author: lolo
 * @Date: 2020/6/29 0029
 */
@Gecco(matchUrl = "https://www.voachinese.com/", pipelines = "myConsolePipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class AmericaVoice implements HtmlBean{
    public static String operator = AMERICA_VOICE;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(1) > div > div > a")
    private String title1;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(2) > div > div > a")
    private String title2;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(3) > div > div > a")
    private String title3;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(4) > div > div > a")
    private String title4;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(5) > div > div > a")
    private String title5;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(6) > div > div > a")
    private String title6;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(7) > div > div > a")
    private String title7;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(8) > div > div > a")
    private String title8;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(9) > div > div > a")
    private String title9;

    @Href(click = true)
    @HtmlField(cssPath = "#wrowblock-6075_56 > div > ul > li:nth-child(10) > div > div > a")
    private String title10;


}
