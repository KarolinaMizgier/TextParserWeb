package com.karolinamizgier.textparserweb.api;

import com.karolinamizgier.textparserweb.service.TextParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TextParserWebApi {
    @Autowired
    private TextParserService textParserService;

    @GetMapping(value = "/parse")
    public ResponseEntity<List<String>> parseText() {
        List<String> body = textParserService.convertText();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
