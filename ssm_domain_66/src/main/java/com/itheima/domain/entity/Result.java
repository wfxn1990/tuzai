package com.itheima.domain.entity;

import java.io.Serializable;

/**
 * 封装操作（新增、更新、删除）的结果信息
 */
public class Result implements Serializable {

    private boolean flag;   // 操作的结果
    private String message; // 操作的信息

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
