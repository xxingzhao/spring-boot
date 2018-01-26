package com.xiaoxz.controller;

import com.xiaoxz.bean.User;
import com.xiaoxz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
@Controller
@RequestMapping("/app")
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
      System.out.println("login...............");
      return "/login";
    }

    @PostMapping("/to_login")
    public User toLogin(@RequestParam(name="userName")String userName,
                        @RequestParam(name= "passWord")String passWord) {
        User user = new User();
        user.setPassWord(passWord);
        user.setUserName(userName);
        return null;
    }

}
