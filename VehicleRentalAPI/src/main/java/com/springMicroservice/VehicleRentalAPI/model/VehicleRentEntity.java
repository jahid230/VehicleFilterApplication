package com.springMicroservice.VehicleRentalAPI.model;


import com.springMicroservice.VehicleRentalAPI.request.RentingServiceRequest;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="VehicleRentEntity")
public class VehicleRentEntity {

    @Id
    private ObjectId _id;

    @DocumentReference(lazy = true)
    private UserEntity User;

    private String Vehicle_VIN;

    private Date booking_date;

    private Date start_date;

    private Date end_date;

    private Double rent_per_day;

    public VehicleRentEntity(RentingServiceRequest rentingServiceRequest){
        this.Vehicle_VIN=rentingServiceRequest.getVehicle_VIN();
        this.booking_date=rentingServiceRequest.getBooking_date();
        this.start_date=rentingServiceRequest.getStart_date();
        this.end_date=rentingServiceRequest.getEnd_date();
        this.rent_per_day=rentingServiceRequest.getRent_per_day();
    }

}
