package com.somosmas.app.controller;

import com.somosmas.app.service.AmazonServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aws")
public class AmazonController {

    @Autowired
    private AmazonServiceImpl amazonService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(amazonService.uploadFile(file), HttpStatus.OK);
    }
}
