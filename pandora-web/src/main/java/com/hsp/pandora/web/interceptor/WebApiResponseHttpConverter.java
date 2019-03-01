package com.hsp.pandora.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hsp.pandora.web.beans.ApiResult;
import com.hsp.pandora.web.beans.BaseApiCode;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: heshipeng
 * @Date: 2019/3/1
 * @Time: 15:33
 * @Description: json消息转换类
 */
public class WebApiResponseHttpConverter extends FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        OutputStream out = outputMessage.getBody();

        String text = null;
        if (obj instanceof ApiResult) {
            text = JSON.toJSONString(obj);

        } else {
            text = JSON.toJSONString(ApiResult.of(BaseApiCode.SUCCESS, obj));
        }

        byte[] bytes = text.getBytes(getCharset());
        out.write(bytes);
    }
}