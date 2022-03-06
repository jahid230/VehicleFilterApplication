package com.springMicroservice.VehicleApplicationAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class updateVehicleRequest {

  @JsonProperty
  @NotNull(message = "VIN is required")
  @UniqueElements(message = "VIN is not Unique")
  private String VIN;
  @JsonProperty
  private String Name;
  @JsonProperty
  private  String licencePlateNumber;
  @JsonProperty
  private List<PropData> prop;
 }
