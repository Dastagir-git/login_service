package com.geaa.bank.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileDetailsController {

    @GetMapping("/getProfileDetails")
    public String getProfileDetails(){
        return "Found user : Test";
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
       return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
