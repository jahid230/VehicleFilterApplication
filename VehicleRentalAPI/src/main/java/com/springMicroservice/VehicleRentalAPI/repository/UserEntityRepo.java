package com.springMicroservice.VehicleRentalAPI.repository;

import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntityRepo extends MongoRepository<UserEntity,Object> {

}
