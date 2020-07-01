package com.geccocrawler.gecco.controller;

import com.geccocrawler.gecco.config.TargetSites;
import com.geccocrawler.gecco.entity.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.geccocrawler.gecco.config.Constance.TITLE_CONTENT_DELIMITER;

@Controller
@RequestMapping("index")
public class IndexController {

    private static Set<String> sites = new HashSet<>();

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    static {
        Class sitesClass = TargetSites.class;
        Field[] sitesFields = sitesClass.getDeclaredFields();
        for (Field field : sitesFields
        ) {
            try {
                sites.add((String) field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping
    public String index(Model modelAndView) {
        Set<NewsVO> articles = new HashSet<>();
        for (String site : sites
        ) {
            Set<Object> articleString = zSetOperations.rangeByScore(site, 0, System.currentTimeMillis());
            articles.add(new NewsVO(site, Objects.requireNonNull(articleString).stream()
                    .map(line -> ((String)line).split(TITLE_CONTENT_DELIMITER))
                    .collect(Collectors.toSet())));
        }
        modelAndView.addAttribute("news", articles);
        return "index";
    }
}
