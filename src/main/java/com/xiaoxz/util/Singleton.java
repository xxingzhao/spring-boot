package com.xiaoxz.util;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/25
 * @Modified by :
 **/
public class Singleton {

    private static Singleton singleton = null;
    private Singleton() {
    }

    public Singleton getInstance() {
        if(singleton == null) {
            synchronized(Singleton.class) {
                if(singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


}
