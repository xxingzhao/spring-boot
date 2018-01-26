package com.xiaoxz.service.impl;

import com.xiaoxz.dao.BaseDao;
import com.xiaoxz.service.BaseService;

import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
public class BaseServiceImpl<T> implements BaseService<T> {


    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public void delete(Integer id) {
        baseDao.delete(id);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public List<T> list() {
        return baseDao.list();
    }

    @Override
    public T getById(Integer id) {
        return baseDao.getById(id);
    }
}
