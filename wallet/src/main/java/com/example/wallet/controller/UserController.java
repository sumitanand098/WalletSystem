package com.example.wallet.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/user" )
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class UserController {


}
