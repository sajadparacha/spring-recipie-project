package com.spring5.springrecepie.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    //**Below resposne code will be sent back to client after handeling this request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)

    public ModelAndView handleNumberFormat(Exception e){
        log.error("Handeling Number Format Exception");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("badRequestError");
        modelAndView.addObject("exception",e);
        return modelAndView;
    }
}
