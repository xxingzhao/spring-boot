package com.xiaoxz.qixin.api.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class ProxyFactory {

    public ProxyFactory() {}

    public static <T> T create(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvokerProxy());
    }

    static class InvokerProxy implements InvocationHandler{

        InvokerProxy(){}

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            int pc = method.getParameterTypes().length;
            return pc == 1 ? RemoteInvoker.invoke(method.getGenericReturnType(), methodName, args[0]) :
                    RemoteInvoker.invoke(method.getGenericReturnType(), methodName, args);
        }
    }
}
