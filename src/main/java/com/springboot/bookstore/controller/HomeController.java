package com.springboot.bookstore.controller;

import com.springboot.bookstore.entity.LoginRequest;
import com.springboot.bookstore.service.JwtService;
import com.springboot.bookstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore")
public class HomeController {
    @Autowired
    private UsersService service;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home(){
        return "Online Bookstore...please login";
    }

    @PostMapping("/login") //generate token
    public String authentication(@RequestBody LoginRequest loginRequest){
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
