package com.springMicroservice.VehicleRentalAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleRentAppBaseController {

    @GetMapping("/User/getAll")
    public ResponseEntity<Object> getAllusers(){

        return new ResponseEntity<>("I am Here", HttpStatus.OK);
    }
}
