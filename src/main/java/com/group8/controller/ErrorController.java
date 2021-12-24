package com.group8.controller;

import com.group8.entity.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @RequestMapping("/401")
    public ResponseEntity<String> error401(){
        return new ResponseEntity<>(401,"认证失败！请重新登录",null);
    }
}
