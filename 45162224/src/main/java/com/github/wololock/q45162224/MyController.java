package com.github.wololock.q45162224;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class MyController {

    @Value("${rest.response}")
    private String restResponse;

    @GetMapping("/foo")
    public ResponseEntity<String> register() {
        return new ResponseEntity<String>(restResponse, HttpStatus.OK);
    }
}
