package com.springMicroservice.VehicleRentalAPI.model;

import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import lombok.*;
import org.bson.types.ObjectId;
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
    private ObjectId _id;

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

    public UserEntity(UserRequestBody requestBody){
        this.userName=requestBody.getUserName();
        this.first_name=requestBody.getFirst_name();
        this.last_name=requestBody.getLast_name();
        this.userFullName=requestBody.getFirst_name()+" "+requestBody.getLast_name();
        this.contact_number=requestBody.getContact_number();
        this.address=requestBody.getAddress();
        this.street=requestBody.getStreet();
        this.city=requestBody.getCity();
        this.zipCode=requestBody.getZipCode();

    }

}
