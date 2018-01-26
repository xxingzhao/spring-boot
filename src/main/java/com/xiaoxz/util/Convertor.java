package com.xiaoxz.util;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/25
 * @Modified by :
 **/
public interface Convertor<T,E> {

    E doForward(T t);

    T backForward(E e);
}
