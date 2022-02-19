package com.springMicroservice.VehicleApplicationAPI;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
public class VehicleException extends Throwable {

    private HttpStatus status;
    private Date time_of_error;
    private String message;
    private List<String> error_list;

    public VehicleException( HttpStatus status, Date date, String message, List<String> errors){
        super();
        this.status=status;
        this.time_of_error=date;
        this.message=message;
        this.error_list= errors;
    }


}
