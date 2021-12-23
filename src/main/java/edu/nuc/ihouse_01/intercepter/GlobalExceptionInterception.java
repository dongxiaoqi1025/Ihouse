package edu.nuc.ihouse_01.intercepter;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterception {
    @ExceptionHandler(value = Exception.class)
    public String  exceptionHandler(HttpServletRequest request,Exception e){
        String failMsg = null;
        if (e instanceof MethodArgumentNotValidException){
            //拿到参数检验具体异常信息信息
            failMsg = ((MethodArgumentNotValidException)e).getBindingResult().getFieldError().getDefaultMessage();
        }
        return failMsg;
    }
}
