package com.geccocrawler.gecco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

/**
 * @Author: lolo
 * @Date: 2020/6/28 0028
 */
@Data
@AllArgsConstructor
public class NewsVO {

    private String operator;
    private Set<String[]> strings;
}
