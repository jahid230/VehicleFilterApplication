package com.springMicroservice.VehicleRentalAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {VehicleRuntimeException.class})
    public ResponseEntity<Object>  HandleDefaultCustomizedException(VehicleRuntimeException vehicleException){

        VehicleException exp=new VehicleException(
                ZonedDateTime.now(),
                vehicleException.getMessage(),
                vehicleException,
                HttpStatus.BAD_REQUEST
        );
    return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value={UserNotFoundException.class})
    public ResponseEntity<Object> UserEntityNotFoundExceptError(UserNotFoundException vehicleException){
        VehicleException exp=new VehicleException(
                ZonedDateTime.now(),
                vehicleException.getMessage(),
                vehicleException,
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value={VehichleRentEntityNotFoundException.class})
    public ResponseEntity<Object> VehichleRentEntityNotFoundError(VehichleRentEntityNotFoundException vehicleException){
        VehicleException exp=new VehicleException(
                ZonedDateTime.now(),
                vehicleException.getMessage(),
                vehicleException,
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid1(MethodArgumentNotValidException ex) {
        Map<String,String> errorMap=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldValue=((FieldError)objectError).getField();
            String error_msg=objectError.getDefaultMessage();
            errorMap.put(fieldValue,error_msg);
        });
        VehicleException exp=new VehicleException(ZonedDateTime.now(),"List of Errors are as Follows:",HttpStatus.BAD_REQUEST,errorMap);

        return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
    }












}
