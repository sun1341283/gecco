package com.geccocrawler.gecco.entity.titles;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lolo
 * @Date: 2020/6/30 0030
 */
@Gecco(matchUrl = "https://cn.wsj.com/zh-hans/news/world", pipelines = "myConsolePipeline",downloader = "myhttpClientDownloader")
@Getter
@Setter
public class WallStreetJourneyWorld implements HtmlBean {


}
