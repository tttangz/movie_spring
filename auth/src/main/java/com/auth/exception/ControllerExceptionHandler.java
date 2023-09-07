package com.auth.exception;

import com.ruoyi.common.core.exception.user.UserException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 用户异常
     */
    @ExceptionHandler(UserException.class)
    public AjaxResult userException(UserException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }
}
