package com.stxb.database;

import java.lang.annotation.*;

/**
 * 切换数据源注解类
 * @author akku
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default DataSource.GOODRABBIT;
    
    public static final String GOODRABBIT = "server";
 
    public static final String LOCAL = "local";
}
