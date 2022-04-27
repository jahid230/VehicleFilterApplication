package com.springMicroservice.VehicleRentalAPI.repository;

import com.springMicroservice.VehicleRentalAPI.model.VehicleRentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRentRepo extends MongoRepository<VehicleRentEntity, ObjectId> {

}
