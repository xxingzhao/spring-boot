package com.xiao;

import com.alibaba.fastjson.JSON;
import com.xiaoxz.bean.User;
import com.xiaoxz.mail.MailService;
import com.xiaoxz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {com.xiaoxz.SpringBootMain.class})
public class UserTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("练嫦");
        user.setPassWord("aaaaaa");
        user.setPhone("18307668283");
        user.setNickName("lhc");
        userService.save(user);
    }

    @Test
    public void listUser() {
        List<User> list = new ArrayList<>();
       /* jdbcTemplate.query("select *from t_user", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassWord(resultSet.getString("pass_word"));
                user.setNickName(resultSet.getString("nick_name"));
                user.setPhone(resultSet.getString("phone"));
                list.add(user);
            }
        });*/
       User u = new User();
       list = userService.list();
       System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void listUser1() {
        String sql = "select id as id,user_name as userName,pass_word as passWord,nick_name as nickName,phone as phone from t_user";
        RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
        List<User> list = jdbcTemplate.query(sql, rowMapper);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setPhone("18307668283");
        user.setUserName("慧嫦");
        user.setId(4);
        userService.update(user);
    }

    @Test
    public void delete() {
        userService.delete(3);
    }

    @Test
    public void getById() {
        User u = userService.getById(4);
        System.out.println(JSON.toJSONString(u));
    }

    @Test
    public void sendMail() {
        mailService.sendAttachmentsMail("575503075@qq.com", "测试", "邮件发送测试内容", "C:\\Users\\Administrator\\Desktop\\合同管理.xlsx");
    }
}
