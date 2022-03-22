package com.springMicroservice.VehicleRentalAPI.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="UserTable")
public class UserEntity {

    @Id
    private Object _id;

    private String userName;

    private String first_name;

    private String last_name;

    private String contact_number;

    private String userFullName;

    private String address;

    private String street;

    private String city;

    private String zipCode;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{'VehicleRentEntity': ?#{self._id}}")
    private List<VehicleRentEntity> rented_vehicles;


}
