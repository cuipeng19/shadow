package com.shadow.creepin;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.exception.ShadowException;
import com.shadow.common.exception.ShadowStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @author cuipeng 2020/7/14 11:37
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(Throwable throwable) {
        log.error("全局异常:", throwable);

        return ResultDTO.error();
    }


    /**
     * 业务异常
     */
    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(ShadowException e) {
        log.error(e.getMessage(), Optional.ofNullable(e.getSrcException()).orElseGet(() -> e));

        return new ResultDTO(e.getState(), e.getMessage());
    }


    /**
     * {@code @RequestBody}请求参数校验异常
     *
     * 日志记录：
     * 捕捉异常直接记录日志,因请求未到达controller,{@link com.shadow.creepin.aspect.LogAspect}aop无法记录日志。
     */
    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        ResultDTO dto = new ResultDTO(ShadowStatus.ERROR_VALIDATED, message);

        log.info("Request:{},url:{}", request.getMethod(), request.getRequestURL().toString());
        log.info("Response:{}", dto.toString());

        return dto;
    }


    /**
     * {@code @RequestParam}请求参数校验异常
     *
     * 日志记录：
     * 捕捉异常直接记录日志,因请求未到达controller,{@link com.shadow.creepin.aspect.LogAspect}aop无法记录日志。
     */
    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(ConstraintViolationException e, HttpServletRequest request) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        ResultDTO dto = new ResultDTO(ShadowStatus.ERROR_VALIDATED, message);

        log.info("Request:{},url:{}", request.getMethod(), request.getRequestURL().toString());
        log.info("Response:{}", dto.toString());

        return dto;
    }

    /**
     * {@code @RequestParam}请求参数校验异常
     *
     * 日志记录：
     * 捕捉异常直接记录日志,因请求未到达controller,{@link com.shadow.creepin.aspect.LogAspect}aop无法记录日志。
     */
    @ExceptionHandler
    @ResponseBody
    ResultDTO exceptionHandler(MissingServletRequestParameterException e, HttpServletRequest request) {
        String message = e.getMessage();
        ResultDTO dto = new ResultDTO(ShadowStatus.ERROR_VALIDATED, message);

        log.info("Request:{},url:{}", request.getMethod(), request.getRequestURL().toString());
        log.info("Response:{}", dto.toString());

        return dto;
    }

}
