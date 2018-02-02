package com.xiaoxz.anno;

import java.lang.annotation.*;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {

    String name() default "";
    GeneratorType generator() default GeneratorType.AUTO;
}
