package com.xiaoxz.dao;

import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
public interface BaseDao<T> {

    /**
     * /保存对象
     * @param t
     */
    void save(T t);

    /**
     * /删除对象
     * @param id
     */
    void delete(Object id);

    /**
     * 更新对象
     * @param t
     */
    void update(T t);

    /**
     * 获取对象
     * @param id
     */
    T getById(Object id);

    /**
     * 对象集合
     * @return
     */
    List<T> list();


}
