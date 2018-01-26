package com.xiaoxz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
@Controller
@RequestMapping("/app")
public class IndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}
