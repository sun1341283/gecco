package com.geccocrawler.gecco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("detail")
public class DetailController {

    @PostMapping
    public String getDetail(@RequestParam("content") String content, @RequestParam("title") String title, Model model){
        model.addAttribute("content",content);
        model.addAttribute("title",title);
        return "detail";
    }
}
