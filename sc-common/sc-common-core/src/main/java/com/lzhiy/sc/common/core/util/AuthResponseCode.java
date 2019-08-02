package com.lzhiy.sc.common.core.util;

/***********************************************************************
 * @website ：
 * @creator : zhiyingli
 * @create-time : 2019/4/8
 * @email : lizhiying61f@gmail.com
 * @description :
 ***********************************************************************/
public enum AuthResponseCode {
    INVALID_CLIENT(401, "无效的客户端"),
    INVALID_AUTHORIZATION_CODE(402,"无效的授权校验码"),
    UNAUTHORIZED_CLIENT(403, "未被授权的客户端"),
    INVALID_GRANT(404, "无效的授权"),
    INVALID_SCOPE(405, "无效的范围"),
    INVALID_TOKEN(406, "无效的Token"),
    INVALID_REQUEST(407, "无效的请求"),
    REDIRECT_URI_MISMATCH(408, "错误的重定向链接"),
    UNSUPPORTED_GRANT_TYPE(409, "不支持的授权类型"),
    UNSUPPORTED_RESPONSE_TYPE(410, "不支持的response类型"),
    ACCESS_DENIED(400, "访问被拒绝");
    private final int code;
    private final String desc;


    AuthResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
