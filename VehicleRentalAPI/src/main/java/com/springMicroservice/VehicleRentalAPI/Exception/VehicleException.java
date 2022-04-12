package com.springMicroservice.VehicleRentalAPI.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class VehicleException extends Throwable {


    private Date timestamp;
    private String message;
    private String Details;


    public VehicleException(Date timestamp, String message, String details){
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.Details = details;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", Details='" + Details + '\'' +
                '}';
    }


}
