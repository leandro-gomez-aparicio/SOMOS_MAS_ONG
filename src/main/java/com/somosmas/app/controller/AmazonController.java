package com.somosmas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.somosmas.app.service.AmazonClient;

@RestController
public class AmazonController {
	private AmazonClient amazonClient;

    @Autowired
    AmazonController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }


   

}
