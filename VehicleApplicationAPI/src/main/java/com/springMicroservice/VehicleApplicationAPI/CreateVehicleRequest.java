package com.springMicroservice.VehicleApplicationAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CreateVehicleRequest {

    @JsonProperty
    private ObjectId _id;
    @JsonProperty
    @NotBlank(message = "VIN is Required")
    @UniqueElements(message = "VIN is not Unique")
    private String VIN;

    @JsonProperty
    @NotBlank(message = "Name is Required")
    private String Name;
    @JsonProperty
    @NotBlank(message = "Licence Number is Required")
    private String licencePlateNumber;
    @JsonProperty
    private List<PropData> prop;

}
