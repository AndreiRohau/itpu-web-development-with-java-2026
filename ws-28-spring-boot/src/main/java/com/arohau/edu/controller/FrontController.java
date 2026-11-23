package com.arohau.edu.controller;

import com.arohau.edu.service.SomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FrontController {

    private final SomeService someService;

    @GetMapping("/upper/{text}")
    public ResponseEntity<String> getUppercase(@PathVariable String text) {
        return ResponseEntity.ok().body(someService.textToUppercase(text));
    }

    @GetMapping("/lower/{text}")
    public ResponseEntity<String> getLowercase(@PathVariable String text) {
        return ResponseEntity.ok().body(someService.textToLowercase(text));
    }
}
