package com.springMicroservice.VehicleRentalAPI.service;


import com.springMicroservice.VehicleRentalAPI.Exception.VehicleRuntimeException;
import com.springMicroservice.VehicleRentalAPI.model.UserEntity;
import com.springMicroservice.VehicleRentalAPI.model.VehicleRentEntity;
import com.springMicroservice.VehicleRentalAPI.repository.UserEntityRepo;
import com.springMicroservice.VehicleRentalAPI.repository.VehicleRentRepo;
import com.springMicroservice.VehicleRentalAPI.request.RentingServiceRequest;
import com.springMicroservice.VehicleRentalAPI.request.UserRequestBody;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VehicleRentingService {

    Logger logger= LoggerFactory.getLogger(VehicleRentingService.class);
    @Autowired
    UserEntityRepo userEntityRepo;
    @Autowired
    VehicleRentRepo vehicleRentRepo;



    public List<UserEntity> getAllUsers() throws VehicleRuntimeException{
        List<UserEntity> users=userEntityRepo.findAll();
        logger.info(users.toString());
       return userEntityRepo.findAll();
    }
    public UserEntity createNewUser(UserRequestBody useRequestBody) throws VehicleRuntimeException {

            UserEntity userObject=new UserEntity(useRequestBody);
            userObject.setRented_vehicles(new ArrayList<VehicleRentEntity>());
            userObject=userEntityRepo.save(userObject);
            return userObject;

    }


    public List<VehicleRentEntity> listofBooking(){

        return vehicleRentRepo.findAll();
    }

    public VehicleRentEntity createVehicleBooking(RentingServiceRequest request) throws VehicleRuntimeException{
        UserEntity userEntity=userEntityRepo.findById(request.getCustomer_id()).get();
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
