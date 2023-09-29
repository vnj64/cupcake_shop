package com.example.steamkey.controllers;

import com.example.steamkey.common.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    private static final String ERROR_MAPPING = "/error";

    @RequestMapping(ERROR_MAPPING)
    public ResponseEntity<ErrorResponse> handleError() {
        ErrorResponse errorResponse = new ErrorResponse("Invalid input. Price must be a non-negative value.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

//    @Override
    public String getErrorPath() {
        return ERROR_MAPPING;
    }
}