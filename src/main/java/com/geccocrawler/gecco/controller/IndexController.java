package com.geccocrawler.gecco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.geccocrawler.gecco.config.Constance.TITLE_CONTENT_DELIMITER;

@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private ZSetOperations zSetOperations;

    @GetMapping
    public String index(Model modelAndView) {
        final Set<String> weibo = zSetOperations.rangeByScore("tx_weibo", 0, System.currentTimeMillis());
        final Set<String[]> strings = Objects.requireNonNull(weibo).stream()
                .map(line -> line.replaceFirst("#",""))
                .map(line -> line.replaceFirst("#",""))
                .filter(line -> !line.startsWith("null"))
                .map(line -> line.split(TITLE_CONTENT_DELIMITER))
                .collect(Collectors.toSet());
        modelAndView.addAttribute("news", strings);
        return "index";
    }
}
