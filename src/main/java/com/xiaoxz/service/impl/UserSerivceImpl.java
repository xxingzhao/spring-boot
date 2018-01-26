package com.xiaoxz.service.impl;

import com.xiaoxz.bean.User;
import com.xiaoxz.dao.BaseDao;
import com.xiaoxz.dao.UserDao;
import com.xiaoxz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/

@Service
public class UserSerivceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    @Resource(name= "userDao")
    public void setBaseDao(BaseDao<User> baseDao) {
        super.setBaseDao(baseDao);
    }
}
