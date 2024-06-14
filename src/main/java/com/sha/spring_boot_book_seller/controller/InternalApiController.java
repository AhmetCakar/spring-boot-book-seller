package com.sha.spring_boot_book_seller.controller;

import com.sha.spring_boot_book_seller.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/internal")
public class InternalApiController {

    @Autowired
    private IUserService userService;


    @PutMapping("make-admin/{username}")
    public ResponseEntity<?> makeAdmin(@PathVariable String username){

        userService.makeadmin(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
