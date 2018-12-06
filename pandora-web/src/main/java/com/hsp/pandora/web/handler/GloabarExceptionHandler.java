package com.hsp.pandora.web.handler;


import com.google.common.collect.ImmutableMap;
import com.hsp.pandora.web.beans.ApiResult;
import com.hsp.pandora.web.beans.BaseApiCode;
import com.hsp.pandora.web.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * Created by heshipeng on 2018/8/29.
 */
@Slf4j
@ControllerAdvice
public class GloabarExceptionHandler
{

    /**
     * 定义异常映射表
     */
    private static final ImmutableMap<Class<? extends Throwable>, Integer> EXCEPTION_MAPPINGS;

    static
    {
        final ImmutableMap.Builder<Class<? extends Throwable>, Integer> builder = ImmutableMap.builder();

        builder.put(UnsatisfiedServletRequestParameterException.class, BaseApiCode.FIELD_ERROR.getCode());

        builder.put(NoHandlerFoundException.class, BaseApiCode.INVALID_REQUEST.getCode());
        builder.put(MethodArgumentNotValidException.class, BaseApiCode.FIELD_TYPE_ERROR.getCode());
        builder.put(MissingServletRequestParameterException.class, BaseApiCode.FIELD_MISSING.getCode());
        builder.put(MissingPathVariableException.class, BaseApiCode.FIELD_MISSING.getCode());

        builder.put(Exception.class, BaseApiCode.UNKOWN_ERROR.getCode());

        EXCEPTION_MAPPINGS = builder.build();
    }


    @ExceptionHandler(AppException.class)
    @ResponseBody
    public Object exp(HttpServletRequest request, AppException ex)
    {
        return ApiResult.of(BaseApiCode.ofCode(ex.getCode()), null);
    }

    /**
     * 未知异常的处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object noHandleerExp(HttpServletRequest request, Exception ex)
    {
        return ApiResult.of(BaseApiCode.UNKOWN_ERROR, null);
    }
}
