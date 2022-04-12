package com.springMicroservice.VehicleRentalAPI.service;


import com.springMicroservice.VehicleRentalAPI.Exception.VehicleException;
import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.model.VehicleRentEntity;
import com.springMicroservice.VehicleRentalAPI.repository.UserEntityRepo;
import com.springMicroservice.VehicleRentalAPI.repository.VehicleRentRepo;
import com.springMicroservice.VehicleRentalAPI.request.RentingServiceRequest;
import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Autowired
    VehicleRentRepo vehicleRentRepo;

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
    public UserEntity createNewUser(UserRequestBody useRequestBody) throws VehicleException {


        try{
            UserEntity userObject=new UserEntity(useRequestBody);
            userObject.setRented_vehicles(new ArrayList<VehicleRentEntity>());
            userObject=userEntityRepo.save(userObject);
            return userObject;
        }
        catch(Exception e){
            String error_msg= e.getMessage();
            throw new VehicleException(new Date(),"Server Error",error_msg);
        }
    }


    public List<VehicleRentEntity> listofBooking(){

        return vehicleRentRepo.findAll();
    }

    public VehicleRentEntity createVehicleBooking(RentingServiceRequest request) throws VehicleException{
        UserEntity userEntity=userEntityRepo.findById(request.getCustomer_id());
        VehicleRentEntity vehicleRentEntity=new VehicleRentEntity(request);
        if(!userEntity.toString().isEmpty() && userEntity.toString()!=null){
            vehicleRentEntity.setUser(userEntity);
            vehicleRentEntity=vehicleRentRepo.save(vehicleRentEntity);
            if(userEntity.getRented_vehicles()!=null && !userEntity.getRented_vehicles().isEmpty()){
              userEntity.getRented_vehicles().add(vehicleRentEntity);
              userEntityRepo.save(userEntity);
            }
            else{
                List<VehicleRentEntity> vehicleRentEntities=new ArrayList<>();
                vehicleRentEntities.add(vehicleRentEntity);
                userEntity.setRented_vehicles(vehicleRentEntities);
                userEntityRepo.save(userEntity);
            }
            return vehicleRentEntity;
        }
        else{
            UserEntity newCustomer=createCustomerOnVehicleBooking(request);
            vehicleRentEntity.setUser(newCustomer);
            vehicleRentEntity=vehicleRentRepo.save(vehicleRentEntity);
            List<VehicleRentEntity> vehicleRentEntities=new ArrayList<>();
            vehicleRentEntities.add(vehicleRentEntity);
            userEntity.setRented_vehicles(vehicleRentEntities);
            userEntityRepo.save(userEntity);
            return vehicleRentEntity;
        }
    }

    public UserEntity createCustomerOnVehicleBooking(RentingServiceRequest request){
        UserRequestBody userRequestBody=new UserRequestBody();
        int random_int=(int)Math.random();
        String UserName=request.getVehicle_VIN();
        String first_name=String.format("Non-Registered-user-%s",UserName);
        String last_name=" ";
        String contact_number="";
        String userFullName="";
        String address="";
        String street="";
        String city="";
        String zipCode="";
        List<VehicleRentEntity> vehicleRentEntities=new ArrayList<>();
        vehicleRentEntities.add(new VehicleRentEntity(request));

        UserEntity userEntity=new UserEntity(new ObjectId(),UserName,first_name,last_name,contact_number,userFullName
        ,address,street,city,zipCode,vehicleRentEntities);
        return userEntity;

    }

}
