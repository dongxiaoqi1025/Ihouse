package edu.nuc.ihouse_01.model.dto;

public enum  ResponseCode {
    SUCCESS(200,"Success"),
    REQUESTBAD(400,"Bad Request"),
    UNAUTHORIZED(401,"Unauthorized"),
    FORBIDDEN(403,"Forbidden"),
    NOTFOUND(404,"Not Found"),
    SERVERERROR(500,"Server Internal Error"),
    CUSTOMEERROR(700,"Custom Error");



    private final int code;
    private final String desc;
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

