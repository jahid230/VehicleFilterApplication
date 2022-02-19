package com.springMicroservice.VehicleApplicationAPI;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse extends Throwable {

    private Date timestamp;
    private String message;
    private String Details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.Details = details;
    }

}
