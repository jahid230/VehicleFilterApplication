package com.springMicroservice.VehicleRentalAPI.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
    public UserNotFoundException(String message){
        super(message);
    }
}
