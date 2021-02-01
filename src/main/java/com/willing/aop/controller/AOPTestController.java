package com.willing.aop.controller;

import com.willing.aop.aspectj.annotation.Resubmit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AOPTestController {
    @Resubmit
    @RequestMapping("hello")
    public void hello(String s){
        //业务逻辑
    }
}
