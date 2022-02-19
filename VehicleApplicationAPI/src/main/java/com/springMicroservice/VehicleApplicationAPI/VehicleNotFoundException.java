package com.springMicroservice.VehicleApplicationAPI;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(String error_msg){

        super(error_msg);
    }

}
