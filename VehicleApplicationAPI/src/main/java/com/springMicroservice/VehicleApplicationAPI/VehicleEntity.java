package com.springMicroservice.VehicleApplicationAPI;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "VehicleEntity")
public class VehicleEntity {

    @Id
    private ObjectId _id;
    private String VIN;
    private String Name;
    private String licencePlateNumber;
    private List<PropData> prop;



}
