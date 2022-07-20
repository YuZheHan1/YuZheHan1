package com.yzh.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})//拦截加了@RestController和@Controller的类
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//@ExceptionHandler注解我们一般是用来自定义异常的,可以认为它是一个异常拦截器（处理器）。
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
           String[] split= ex.getMessage().split(" ");
           String msg=split[2]+"已存在";
          return R.error(msg);
        }
        return R.error("未知错误");
    }
    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(CustomException.class)//@ExceptionHandler注解我们一般是用来自定义异常的,可以认为它是一个异常拦截器（处理器）。
    public R<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());

        return R.error(ex.getMessage());
    }
}
