package com.hsp.pandora.web;

import com.hsp.pandora.web.interceptor.WebApiResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author heshipeng
 * 拦截器配置类
 */
@Configuration
public class WebMvcConfigExpansion extends WebMvcConfigurationSupport
{
    @Autowired
    private WebApiResponseInterceptor webApiResponseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(webApiResponseInterceptor);
        super.addInterceptors(registry);
    }
}
