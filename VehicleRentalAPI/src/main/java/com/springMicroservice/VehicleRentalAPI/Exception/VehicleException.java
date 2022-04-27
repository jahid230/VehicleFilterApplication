package com.springMicroservice.VehicleRentalAPI.Exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class VehicleException {
    private  ZonedDateTime timestamp;
    private  String message;
    private  Throwable throwable;
    private HttpStatus httpStatus;
    private Map<String,String> error_arr;

    public VehicleException(ZonedDateTime timestamp, String message, Throwable throwable, HttpStatus httpStatus) {
        this.timestamp = timestamp;
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public VehicleException(ZonedDateTime timestamp, String message, HttpStatus httpStatus, Map<String, String> error_arr) {
        this.timestamp = timestamp;
        this.message = message;
       this.httpStatus = httpStatus;
        this.error_arr = error_arr;
    }
}
