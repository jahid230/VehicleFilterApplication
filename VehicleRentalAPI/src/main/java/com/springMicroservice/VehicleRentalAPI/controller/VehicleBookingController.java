package com.springMicroservice.VehicleRentalAPI.controller;


import com.springMicroservice.VehicleRentalAPI.Exception.VehicleException;
import com.springMicroservice.VehicleRentalAPI.model.VehicleRentEntity;
import com.springMicroservice.VehicleRentalAPI.request.RentingServiceRequest;
import com.springMicroservice.VehicleRentalAPI.service.VehicleRentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("/book")
public class VehicleBookingController {

    @Autowired
    VehicleRentingService vehicleRentingService;

    @GetMapping
    public List<VehicleRentEntity> getAllBookingInfo(){

        return vehicleRentingService.listofBooking();

    }

    @PostMapping
    public VehicleRentEntity createBooking(@RequestBody @Valid RentingServiceRequest rentingServiceRequest) throws VehicleException {

       return vehicleRentingService.createVehicleBooking(rentingServiceRequest);
    }



}
