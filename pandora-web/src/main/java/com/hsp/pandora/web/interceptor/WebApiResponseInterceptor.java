package com.hsp.pandora.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.hsp.pandora.web.beans.ApiResult;
import com.hsp.pandora.web.beans.BaseApiCode;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WebApiResponseInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (!response.isCommitted()) {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                if (handlerMethod.getMethod().getReturnType().isAssignableFrom(void.class)) {
                    String text = JSON.toJSONString(ApiResult.of(BaseApiCode.SUCCESS, null));
                    response.setHeader("Content-type", "application/json;charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(text);
                }
            }
        }

        super.afterCompletion(request, response, handler, ex);
    }
}
