package com.hsp.pandora.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.hsp.pandora.web.interceptor.SecurityInterceptor;
import com.hsp.pandora.web.interceptor.WebApiResponseHttpConverter;
import com.hsp.pandora.web.interceptor.WebApiResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author heshipeng
 * 配置类
 */
@Configuration
public class WebMvcConfigExpansion extends WebMvcConfigurationSupport
{
    @Autowired
    private WebApiResponseInterceptor webApiResponseInterceptor;

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //设置json消息转换器
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        WebApiResponseHttpConverter converter2 = new WebApiResponseHttpConverter();
        List<MediaType> lists = new ArrayList<>();
        lists.add(MediaType.APPLICATION_JSON_UTF8);
        converter2.setSupportedMediaTypes(lists);


        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.PrettyFormat,SerializerFeature.WriteEnumUsingName,SerializerFeature.WriteBigDecimalAsPlain);
        fastJsonConfig.setWriteContentLength(true);
        converter2.setFastJsonConfig(fastJsonConfig);
        converters.add(converter);
        converters.add(converter2);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(webApiResponseInterceptor);
        registry.addInterceptor(securityInterceptor);
        super.addInterceptors(registry);
    }
}
