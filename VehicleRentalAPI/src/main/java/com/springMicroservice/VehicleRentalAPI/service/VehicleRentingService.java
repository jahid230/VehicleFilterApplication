package com.springMicroservice.VehicleRentalAPI.service;


import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.model.VehicleRentEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleRentingService {

/*
* private Object _id;

    private String userName;

    private String first_name;

    private String last_name;

    private String contact_number;

    private String userFullName;

    private String address;

    private String street;

    private String city;

    private String zipCode;
* */

    public List<UserEntity> getAllUsers(){
        List<UserEntity> userList=new ArrayList<UserEntity>();
        List<VehicleRentEntity> rentEntityList= new ArrayList<VehicleRentEntity>();
        UserEntity user1= new UserEntity(new ObjectId(),"jahid230","Md Jahidul","Haque","012511230","Md Jahidul Haque","Josef-kindshoven-str. 5","Josef-kindshoven-str.","Bamberg","96052",rentEntityList);
        return user1;
    }


}
