package com.xiaoxz.service;


import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
public interface BaseService<T> {

    void save(T t);

    void delete(Integer id);

    void update(T t);

    List<T> list();

    T  getById(Integer id);
}
