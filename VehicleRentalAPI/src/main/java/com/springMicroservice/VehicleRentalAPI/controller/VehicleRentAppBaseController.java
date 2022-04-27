package com.springMicroservice.VehicleRentalAPI.controller;

import com.springMicroservice.VehicleRentalAPI.Exception.UserNotFoundException;
import com.springMicroservice.VehicleRentalAPI.Exception.VehicleException;
import com.springMicroservice.VehicleRentalAPI.Exception.VehicleRuntimeException;
import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import com.springMicroservice.VehicleRentalAPI.response.UserResponse;
import com.springMicroservice.VehicleRentalAPI.service.VehicleRentingService;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController("/customers")
public class VehicleRentAppBaseController {

    @Autowired
    VehicleRentingService vehicleRentingService;

    @GetMapping("/getAll")
    public List<UserResponse> getAllusers(){
        List<UserEntity> users=vehicleRentingService.getAllUsers();
        List<UserResponse> responses= new ArrayList<>();
        if(vehicleRentingService.getAllUsers().isEmpty()|| vehicleRentingService.getAllUsers()==null){
            throw new UserNotFoundException("No Customer in the DB");
        }
        else{
            users.stream().forEach(userEntity -> {
                responses.add(new UserResponse(userEntity));
            });
           }
        return responses;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody UserRequestBody userRequestBody) throws VehicleRuntimeException{

        try{

        }catch (VehicleRuntimeException e){


        }

        UserEntity savedUser= vehicleRentingService.createNewUser(userRequestBody);

        UserResponse userResponse=new UserResponse(savedUser);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);

    }
}
