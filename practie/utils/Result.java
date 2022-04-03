package com.practie.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 喵vamp
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private int status;
    private Object data;
    private String message;
    private long total;

    public Result(String message) {
        this.message = message;
    }

    public Result(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(int status, Object data, long total) {
        this.status = status;
        this.data = data;
        this.total = total;
    }
}
