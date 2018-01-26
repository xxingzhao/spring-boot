package com.xiaoxz.bean;

import com.xiaoxz.anno.Id;
import com.xiaoxz.anno.Table;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
@Table(name = "t_user")
public class User {

    @Id
    private Integer id;
    private String userName;
    private String passWord;
    private String nickName;
    private String phone;
    private String ddddd;



    public User() {
    }

    public String getDdddd() {
        return ddddd;
    }

    public void setDdddd(String ddddd) {
        this.ddddd = ddddd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
