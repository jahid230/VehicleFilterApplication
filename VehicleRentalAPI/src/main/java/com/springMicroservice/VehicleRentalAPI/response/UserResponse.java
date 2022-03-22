package com.springMicroservice.VehicleRentalAPI.response;

import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {

    private Object id;

    private String userName;

    private String first_name;

    private String last_name;

    private String contact_number;

    private String userFullName;

    private String address;

    private String street;

    private String city;

    private String zipCode;

    public UserResponse(UserEntity user){
        this.id=user.get_id();
        this.first_name=user.getFirst_name();
        this.last_name=user.getLast_name();
        this.userName=user.getUserName();
        this.userFullName=user.getUserFullName();
        this.address=user.getAddress();
        this.city=user.getCity();
        this.zipCode=user.getZipCode();
    }


}
