package com.demoautomobilefactory.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ControllerFabrica {

	
    @GetMapping("test")
    public ResponseEntity<?> Test() {

            return new ResponseEntity<>("paso", HttpStatus.OK);
    }	
	
	
}
