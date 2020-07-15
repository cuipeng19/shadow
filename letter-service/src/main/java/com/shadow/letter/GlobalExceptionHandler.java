package com.shadow.letter;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.exception.ShadowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

/**
 * 全局异常处理
 * @author cuipeng 2020/7/14 11:37
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(Throwable throwable) {
        log.error("全局异常:", throwable);

        return ResultDTO.error();
    }


    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(ShadowException e) {
        log.error(e.getMessage(), Optional.ofNullable(e.getSrcException()).orElseGet(() -> e));

        return new ResultDTO(e.getState(), e.getMessage());
    }

}
