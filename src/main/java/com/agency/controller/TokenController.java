package com.agency.controller;


import com.agency.entitty.AppUserRole;
import org.apache.commons.lang3.StringUtils;
import com.agency.service.IAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/agency")
@RestController
public class TokenController {

    @Autowired
    IAgencyService agencyService;


    @PostMapping("/token")
    public String getToken(@RequestParam("username") String username, @RequestParam("password")  String password,@RequestParam("role") String role){
        return agencyService.login(username,password,AppUserRole.valueOf(role) );
    }

}
