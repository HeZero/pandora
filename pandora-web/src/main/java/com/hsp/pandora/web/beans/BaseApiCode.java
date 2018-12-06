package com.hsp.pandora.web.beans;

/**
 * 系统基础业务返回码
 * Created by heshipeng on 2018/8/29.
 */
public enum BaseApiCode
{
    SUCCESS(100200, "成功"),
    FAIL(100201, "失败"),
    NEED_LOGIN(100202, "当前账号需要登陆"),
    SERVER_ERROR(100500, "服务错误"),
    UNKOWN_ERROR(100505, "未知的错误"),

    FIELD_ERROR(100501, "传入字段校验错误"),
    FIELD_TYPE_ERROR(100502, "参数类型不匹配"),
    FIELD_MISSING(100503, "缺少请求参数"),
    INVALID_REQUEST(100504, "请求方法不存在"),

    DB_OP_ERROR(100506, "数据库操作失败");

    private int code;

    private String message;

    BaseApiCode(int code, String mesage)
    {
        this.code = code;
        this.message = mesage;
    }

    public static BaseApiCode ofCode(int code)
    {
        BaseApiCode[] codes = BaseApiCode.values();
        for(BaseApiCode c: codes)
        {
            if(c.getCode() == code)
            {
                return c;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
