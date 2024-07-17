package com.example.classhub;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        // 로그 출력 (선택 사항)
        ex.printStackTrace();

        // 500 에러인 경우 로그인 페이지로 리디렉션
        if (ex instanceof RuntimeException) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("errorpage");
            modelAndView.addObject("message", ex.getMessage());
        }

        return modelAndView;
    }

}
