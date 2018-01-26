package com.xiaoxz.controller;

import com.xiaoxz.bean.User;
import com.xiaoxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("create")
    @ResponseBody
    public User creteUser(@RequestParam(name= "userName")String userName,
                            @RequestParam(name = "passWord")String passWord,
                            @RequestParam(name = "phone")String phone) {
        User u = new User();
        u.setUserName(userName);
        u.setPassWord(passWord);
        u.setPhone(phone);
        return u;
    }

    @RequestMapping("/list")
    public String listUser() {
        return "user/user_list";
    }

    @RequestMapping("/list/json")
    @ResponseBody
    public List<User> listUserJson() {
        List<User> list = userService.list();
        return list;
    }
}
