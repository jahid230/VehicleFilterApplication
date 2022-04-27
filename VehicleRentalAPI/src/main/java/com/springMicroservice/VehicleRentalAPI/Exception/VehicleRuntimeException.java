package com.springMicroservice.VehicleRentalAPI.Exception;

public class VehicleRuntimeException extends RuntimeException{
    public VehicleRuntimeException(String msg){
        super(msg);
    }
    public VehicleRuntimeException(String msg, Throwable exp_cause){
        super(msg,exp_cause);
    }
}
