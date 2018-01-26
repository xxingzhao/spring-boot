package com.xiaoxz.service.impl;

import com.xiaoxz.bean.Staff;
import com.xiaoxz.dao.BaseDao;
import com.xiaoxz.dao.StaffDao;
import com.xiaoxz.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService{

    @Resource
    private StaffDao staffDao;

    @Override
    @Resource(name = "staffDao")
    public void setBaseDao(BaseDao<Staff> baseDao) {
        super.setBaseDao(baseDao);
    }
}
