package com.somosmas.app.controller;

import com.somosmas.app.config.AmazonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aws")
public class AmazonController {

    private AmazonConfig amazonClient;

    @Autowired
    AmazonController(AmazonConfig amazonClient) {
        this.amazonClient = amazonClient;
    }

}
