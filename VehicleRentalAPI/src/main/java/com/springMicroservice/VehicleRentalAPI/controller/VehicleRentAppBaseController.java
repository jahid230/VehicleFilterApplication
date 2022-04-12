package com.springMicroservice.VehicleRentalAPI.controller;

import com.springMicroservice.VehicleRentalAPI.Exception.VehicleException;
import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import com.springMicroservice.VehicleRentalAPI.response.UserResponse;
import com.springMicroservice.VehicleRentalAPI.service.VehicleRentingService;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController("/customers")
public class VehicleRentAppBaseController {

    @Autowired
    VehicleRentingService vehicleRentingService;

    @GetMapping("/getAll")
    public String getAllusers(){

        return "I am here";
       // return new ResponseEntity<>("I am Here", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createNewUser(@RequestBody @Valid UserRequestBody userRequestBody) throws VehicleException {

        UserEntity savedUser= vehicleRentingService.createNewUser(userRequestBody);

        UserResponse userResponse=new UserResponse(savedUser);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);

    }
}
