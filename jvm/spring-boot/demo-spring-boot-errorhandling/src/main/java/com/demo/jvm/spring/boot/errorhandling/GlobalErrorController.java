package com.demo.jvm.spring.boot.errorhandling;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalErrorController {

  @RequestMapping("/error/{status}")
  public ResponseEntity<String> handleError(@PathVariable int status) {
    String body = "Error " + status;
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.valueOf(status));
  }
}
