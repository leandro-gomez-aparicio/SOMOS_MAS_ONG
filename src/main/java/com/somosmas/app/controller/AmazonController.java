package com.somosmas.app.controller;

import com.somosmas.app.config.AmazonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aws")
public class AmazonController {

    private AmazonConfiguration amazonClient;

    @Autowired
    AmazonController(AmazonConfiguration amazonClient) {
        this.amazonClient = amazonClient;
    }

}
