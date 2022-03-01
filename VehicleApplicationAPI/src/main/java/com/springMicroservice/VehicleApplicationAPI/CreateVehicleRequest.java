package com.springMicroservice.VehicleApplicationAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import org.bson.types.ObjectId;

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
    private String VIN;

    @JsonProperty
    private String Name;
    @JsonProperty
    private String licencePlateNumber;
    @JsonProperty
    private List<PropData> prop;

}
