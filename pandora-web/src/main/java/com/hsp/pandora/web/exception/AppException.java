package com.hsp.pandora.web.exception;

import com.hsp.pandora.web.beans.BaseApiCode;
import lombok.Data;

/**
 * 业务异常
 * Created by heshipeng on 2018/8/29.
 */
@Data
public class AppException extends RuntimeException
{

    public static final Long serialVersionUID = 1L;

    private int code;

    private String message;

    private Object attach;

    private String[] format;

    public AppException()
    {
        super();
    }

    public AppException(BaseApiCode cause)
    {
        this.code = cause.getCode();
        this.message = cause.getMessage();
    }
}
