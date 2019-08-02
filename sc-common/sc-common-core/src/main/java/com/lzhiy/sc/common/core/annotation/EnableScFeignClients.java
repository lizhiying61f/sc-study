package com.lzhiy.sc.common.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***********************************************************************
 * @website ：
 * @creator : zhiyingli
 * @create-time : 2019-08-02
 * @email : lizhiying61f@gmail.com
 * @description :
 ***********************************************************************/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableScFeignClients
public @interface EnableScFeignClients {
    String[] value() default {};


    String[] basePackages() default {"com.lzhiy.sc"};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
