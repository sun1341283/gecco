package com.geccocrawler.gecco.entity.titles;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

import static com.geccocrawler.gecco.config.TargetSites.BBC_NEWS;

/**
 * @Author: lolo
 * @Date: 2020/6/28 0028
 */
@Gecco(matchUrl = "https://www.bbc.com/zhongwen/simp/world", pipelines = "myConsolePipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class BBC implements HtmlBean {

    public static String operator = BBC_NEWS;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(1) > a")
    private String title1;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(2) > a")
    private String title2;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(3) > a")
    private String title3;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(4) > a")
    private String title4;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(5) > a")
    private String title5;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(6) > a")
    private String title6;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(7) > a")
    private String title7;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(8) > a")
    private String title8;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(9) > a")
    private String title9;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(10) > a")
    private String title10;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(11) > a")
    private String title11;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(12) > a")
    private String title12;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(13) > a")
    private String title13;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(14) > a")
    private String title14;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(15) > a")
    private String title15;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(16) > a")
    private String title16;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(17) > a")
    private String title17;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(18) > a")
    private String title18;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(19) > a")
    private String title19;

    @Href(click = true)
    @HtmlField(cssPath = "#comp-recent-media > div > div:nth-child(20) > a")
    private String title20;
}
