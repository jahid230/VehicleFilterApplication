package com.springMicroservice.VehicleApplicationAPI;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehicleRepo extends MongoRepository<VehicleEntity,Long>{

    public VehicleEntity findByVIN(String VIN);
    public List<VehicleEntity> findByName(String Name);

    public List<VehicleEntity> findByprop(PropData prop);

    public List<VehicleEntity> findBylicencePlateNumber(String licencePlateNumber);
}
