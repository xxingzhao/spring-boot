package com.xiaoxz.dao.impl;

import com.xiaoxz.dao.BaseDao;
import com.xiaoxz.dict.SqlOperateEnum;
import com.xiaoxz.util.Parse;
import com.xiaoxz.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
public class BaseDaoImpl<T> implements BaseDao<T> {



    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Class<T> clazz;

    public BaseDaoImpl() {
        clazz = (Class<T>)((ParameterizedType)(getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    /**
     * 保存对象
     * @param t
     */
    @Override
    public void save(T t) {
        String sql  = Parse.createInsert(t);
        Object[] args = Parse.getArgs(t, SqlOperateEnum.SQL_INSERT);
        jdbcTemplate.update(sql, args);
    }

    /**
     * 根据主键删除对象
     * @param id
     */
    @Override
    public void delete(Object id) {
        String sql = "delete from " + ReflectionUtil.getTableName(clazz) + " where id=?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    /**
     * 根据主键更新对象
     * @param t
     */
    @Override
    public void update(T t) {
        String sql = Parse.createUpdate(t);
        Object[] args = Parse.getArgs(t, SqlOperateEnum.SQL_UPDATE);
        jdbcTemplate.update(sql, args);
    }

    /**
     * 根据主键取得对象
     * @param id
     * @return
     */
    @Override
    public T getById(Object id) {
        String sql = "select *from " + ReflectionUtil.getTableName(clazz) +" where id=?";
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
        List<T> list = jdbcTemplate.query(sql, new Object[]{id}, rowMapper);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 全表查询
     * @return
     */
    @Override
    public List<T> list() {
        String sql = Parse.createSelect(clazz);
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
        return jdbcTemplate.query(sql, rowMapper);
    }



}
