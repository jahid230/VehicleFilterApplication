package com.springMicroservice.VehicleApplicationAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class updateVehicleRequest {
  @JsonProperty
  @NotNull(message = "VIN is required")
  private String VIN;
  @JsonProperty
  private String Name;
  @JsonProperty
  private  String licencePlateNumber;
  @JsonProperty
  private List<PropData> prop;
 }
