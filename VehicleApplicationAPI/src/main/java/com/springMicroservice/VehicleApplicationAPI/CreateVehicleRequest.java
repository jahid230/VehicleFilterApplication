package com.springMicroservice.VehicleApplicationAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data

public class CreateVehicleRequest {

    @JsonProperty
    private String VIN;

    @JsonProperty
    private String Name;
    @JsonProperty
    private String licencePlateNumber;
    @JsonProperty
    private List<PropData> prop;

}
