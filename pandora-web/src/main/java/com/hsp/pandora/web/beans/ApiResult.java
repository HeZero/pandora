package com.hsp.pandora.web.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 业务处理结果
 * Created by heshipeng on 2018/8/29.
 */
@Getter
@Setter
@ToString
public class ApiResult implements Serializable
{

    public int code;

    public String message;

    public Object data;


    public static ApiResult of(BaseApiCode apiCode, Object data)
    {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(apiCode.getCode());
        apiResult.setMessage(apiCode.getMessage());
        apiResult.setData(data);
        return apiResult;
    }
}
