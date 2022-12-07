package com.nevergiveup.datn.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null){
            Integer statusCode = Integer.parseInt(status.toString());
            switch (statusCode){
                case 401 :
                    return "401";
                case 403 :
                    return "403";
                case 500 :
                    return "500";
            }
        }
        return "users/subpage/404";
    }
}
