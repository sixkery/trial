package com.sixkery.exception;

import com.sixkery.util.ResponseModel;
import com.sixkery.util.ResponseModels;
import com.sixkery.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author sixkery
 * @date 2020/6/14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 捕获shiro异常
     * @param e 异常
     * @return 错误信息
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResponseModel<String> handler(ShiroException e) {
        log.error("shiro 登录权限的异常!");
        return ResponseModels.failed(ResultCode.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 捕获运行时异常
     * @param e 异常
     * @return 错误信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseModel<String> handler(RuntimeException e) {
        log.error("运行时产生的异常!");
        return ResponseModels.failed(e.getMessage());
    }

    /**
     * 捕获 from 属性验证异常
     * @param e 异常
     * @return 错误信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseModel<String> handler(MethodArgumentNotValidException e) {
        log.error("实体校验的异常!");
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return ResponseModels.failed(objectError.getDefaultMessage());
    }

    /**
     * 捕获 Assert 的异常
     * @param e 异常
     * @return 错误信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseModel<String> handler(IllegalArgumentException e) {
        log.error("Assert的异常!");
        return ResponseModels.failed(e.getMessage());
    }

}
