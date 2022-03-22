package com.springMicroservice.VehicleRentalAPI.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestBody {

    @JsonProperty
    private String userName;

    @JsonProperty
    private String first_name;

    @JsonProperty
    private String last_name;

    @JsonProperty
    private String contact_number;

    @JsonIgnore
    private String userFullName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String street;

    @JsonProperty
    private String city;

    @JsonProperty
    private String zipCode;


    public UserRequestBody(String userName,String first_name, String last_name,String contact_number,String address,String street,String city,String zipCode){
        this.userName=userName;
        this.first_name=first_name;
        this.last_name=last_name;
        this.contact_number=contact_number;
        this.userName=first_name+" "+last_name;
        this.address=address;
        this.street=street;
        this.city=city;
        this.zipCode=zipCode;
    }
}
