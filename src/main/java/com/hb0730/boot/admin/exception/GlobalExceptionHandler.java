package com.hb0730.boot.admin.exception;

import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.commons.web.response.CodeStatusEnum;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 全局异常处理
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * <p>
     * 用户未找到
     * </p>
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.result(CodeStatusEnum.USERNAME_NOTFOUND, e.getMessage());
    }

    /**
     * <p>
     * 密码不匹配
     * </p>
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result handlerBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.result(CodeStatusEnum.FORBIDDEN, "用户名或者密码错误");
    }

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public Result handlerBaseException(BaseException e) {
        return ResponseResult.resultFall(e.getMessage());
    }
}