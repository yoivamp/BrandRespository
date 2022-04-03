package com.practie.utils;

import com.practie.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 喵vamp
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception){
        exception.printStackTrace();
        return new Result("系统错误");
    }
}
