package com.springMicroservice.VehicleRentalAPI.controller;

import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import com.springMicroservice.VehicleRentalAPI.response.UserResponse;
import com.springMicroservice.VehicleRentalAPI.service.VehicleRentingService;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleRentAppBaseController {

    @Autowired
    VehicleRentingService vehicleRentingService;

    @GetMapping("/User/getAll")
    public ResponseEntity<Object> getAllusers(){

        return new ResponseEntity<>("I am Here", HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<UserResponse> createNewUser(@RequestBody UserRequestBody userRequestBody) throws Exception {
        if(userRequestBody!=null){
            UserEntity userEntity=vehicleRentingService.createNewUser(userRequestBody);
            if(userEntity!=null){
                UserResponse userResponse=new UserResponse(userEntity);
                return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
            }
            else{
                Exception exception=new Exception();
                throw exception;
            }

        }
        else{
            throw new NullValueInNestedPathException(UserRequestBody.class, "Bad Request");
        }

    }
}
