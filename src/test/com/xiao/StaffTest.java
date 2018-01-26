package com.xiao;

import com.alibaba.fastjson.JSON;
import com.xiaoxz.bean.Staff;
import com.xiaoxz.mail.MailService;
import com.xiaoxz.service.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {com.xiaoxz.SpringBootMain.class})
public class StaffTest {

    @Autowired
    private StaffService staffService;
    @Autowired
    private MailService mailService;

    @Test
    public void save() {
        Staff staff = new Staff();
        staffService.save(staff);
    }

    @Test
    public void getById() {
        Staff staff = staffService.getById(1);
        System.out.println(JSON.toJSONString(staff));
    }

    @Test
    public void sendMail() {
        mailService.sendSimpleMail("2992658760@qq.com", "测试", "邮件发送测试内容");
    }
}
