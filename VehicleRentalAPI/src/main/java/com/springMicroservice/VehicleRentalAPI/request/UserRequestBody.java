package com.springMicroservice.VehicleRentalAPI.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PropertySource(name = "customer_default_filedValues",value = "customer_default_filedValues.properties")
public class UserRequestBody {

    @JsonProperty
    @NotEmpty(message = "User Name Can't Empty")
    @NotBlank(message = "User Name Can't Blank")
    @NotNull(message = "User Name Can't Null")
    private String userName;

    @JsonProperty
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String first_name;

    @JsonProperty
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String last_name;

    @JsonProperty
    @NotEmpty
    @NotBlank
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    private String contact_number;

    @JsonIgnore
    private String userFullName;

    @JsonProperty
    @NotNull
    @Value("${customer.address}")
    private String address;

    @JsonProperty
    @Value("${customer.street}")
    private String street;

    @JsonProperty
    @Value("${customer.city}")
    private String city;

    @JsonProperty
    @Value("${customer.zipCode}")
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
