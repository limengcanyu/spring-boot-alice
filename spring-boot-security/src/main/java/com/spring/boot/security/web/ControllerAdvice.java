package com.spring.boot.security.web;//package com.spring.boot.security.web;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class ControllerAdvice {
//    @ExceptionHandler(Exception.class)
//    public String exceptionHandler(Exception ex){
//        if (ex instanceof AccessDeniedException) {
//            return "角色未授权，不允许访问";
//        }
//        ex.printStackTrace();
//        return "统一异常处理";
//    }
//}
