package com.example.rainbow.bean;

public class UploadPictureResponse {


    /**
     * code : 0
     * msg : success
     * data : images\5342c8ea3af74f82b0c69a76576e5cc2.png
     */

    private int code;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
