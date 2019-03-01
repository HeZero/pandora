package com.hsp.pandora.web.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: heshipeng
 * @Date: 2019/3/1
 * @Time: 12:07
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthContext
{
    /**
     * 默认没有权限
     * @return
     */
    AuthEnum value() default AuthEnum.NO;
}