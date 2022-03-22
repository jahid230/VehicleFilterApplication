package com.springMicroservice.VehicleRentalAPI.service;


import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.model.VehicleRentEntity;
import com.springMicroservice.VehicleRentalAPI.repository.UserEntityRepo;
import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserEntityRepo userEntityRepo;

    public List<UserEntity> getAllUsersStatic(){
        List<UserEntity> userList=new ArrayList<UserEntity>();
        List<VehicleRentEntity> rentEntityList= new ArrayList<VehicleRentEntity>();
        UserEntity user1= new UserEntity(new ObjectId(),"jahid230","Md Jahidul","Haque","012511230","Md Jahidul Haque","Josef-kindshoven-str. 5","Josef-kindshoven-str.","Bamberg","96052",rentEntityList);
        userList.add(user1);
        return userList;
    }

    public List<UserEntity> getAllUsers(){
        try{
            if(!userEntityRepo.findAll().isEmpty() && userEntityRepo.findAll()!=null){
                return userEntityRepo.findAll();
            }
            else{
                return null;
            }

        }catch(Exception e){
            throw e;
        }

    }
    public UserEntity createNewUser(UserRequestBody useRequestBody){

        try{
            if(useRequestBody!=null&& !useRequestBody.toString().isEmpty()){
                UserEntity userObject=new UserEntity(new ObjectId(),
                        useRequestBody.getUserName(), useRequestBody.getFirst_name(),
                        useRequestBody.getLast_name(), useRequestBody.getContact_number(),
                        useRequestBody.getUserFullName(), useRequestBody.getAddress(),
                        useRequestBody.getStreet(),useRequestBody.getCity(),
                        useRequestBody.getZipCode(),new ArrayList<VehicleRentEntity>()
                );
                userObject=userEntityRepo.save(userObject);
                return userObject;
            }
            else {
                return null;
            }
        }
        catch(Exception e){
            throw e;
        }
    }


}
