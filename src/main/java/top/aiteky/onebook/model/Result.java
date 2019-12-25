package top.aiteky.onebook.model;

import java.util.HashMap;

public class Result {
    private int code = 0;
    private String msg;
    private HashMap data = null;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

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

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
}
