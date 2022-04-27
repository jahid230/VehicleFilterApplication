package com.springMicroservice.VehicleRentalAPI.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RentingServiceRequest {

    @JsonProperty
    @NotBlank(message = "Customer Identification is required")
    @NotEmpty(message = "Customer Identification can't be an empty String")
    private ObjectId customer_id;

    @JsonProperty
    @NotBlank(message = "Vehicle Identification is required")
    @NotEmpty(message = "Vehicle Identification can't be an empty String")
    private String Vehicle_VIN;

    @JsonProperty
    @NotBlank(message = "Vehicle Identification is required")
    @NotEmpty(message = "Vehicle Identification can't be an empty String")
    @FutureOrPresent
    private Date booking_date;

    @FutureOrPresent(message = "Start date can't be in past")
    private Date start_date;

    @FutureOrPresent(message = "End date cant be before today")
    private Date end_date;

    @Positive
    @DecimalMin("1.00")
    private Double rent_per_day;

    public ObjectId getCustomer_id(){
        return this.customer_id;
    }
}
