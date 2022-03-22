package com.springMicroservice.VehicleRentalAPI.model;


import lombok.*;
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
    private Object _id;

    @DocumentReference(lazy = true)
    private UserEntity User;

    private String Vehicle_VIN;

    private Date booking_date;

    private Date start_date;

    private Date end_date;

    private Double rent_per_day;


}
