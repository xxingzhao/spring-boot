package com.xiaoxz.anno;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/2/2
 * @Modified by :
 **/
public enum GeneratorType {

    /**
     * 字段判断  字符类型，则为UUID方式
     *          整型类型，则为INCREMENT 自增长方式
     */
    AUTO,
    /**
     * UUID方式
     */
    UUID,
    /**
     * 自增长方式
     */
    INCREMENT,
    /**
     * 自行分配方式
     */
    ASSIGNED;
}
