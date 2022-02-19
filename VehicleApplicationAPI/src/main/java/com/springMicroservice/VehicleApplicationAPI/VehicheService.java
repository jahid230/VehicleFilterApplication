package com.springMicroservice.VehicleApplicationAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class VehicheService {

    Logger logger= LoggerFactory.getLogger(VehicheService.class);
    @Autowired
    VehicleRepo vehicleRepo;
    public List<VehicleEntity> getAllVehicle() throws VehicleException {

        List<VehicleEntity> vehicleEntityList= vehicleRepo.findAll();

        if(vehicleEntityList !=null){
            return vehicleEntityList;
        }
        else{
            throw new VehicleNotFoundException("No vehicle Found");
        }

    }

    public VehicleEntity createNewVehicle(CreateVehicleRequest createVehicleRequest) throws VehicleException {

        VehicleEntity vehicleEntity=new VehicleEntity();
        vehicleEntity.setVIN(createVehicleRequest.getVIN());
        vehicleEntity.setName(createVehicleRequest.getName());
        vehicleEntity.setLicencePlateNumber(createVehicleRequest.getLicencePlateNumber());
        vehicleEntity.setProp(createVehicleRequest.getProp());
        vehicleRepo.save(vehicleEntity);
        return vehicleEntity;


    }

    public VehicleEntity updateVehicle(updateVehicleRequest updateVehicleRequest)
    {


        VehicleEntity vehicleEntity=vehicleRepo.findByVIN(updateVehicleRequest.getVIN());

        String State = null;
        if(vehicleEntity!=null){
            if(updateVehicleRequest.getName()!=null && !updateVehicleRequest.getName().isEmpty()){
                //update.set("Name",updateVehicleRequest.getName());
                vehicleEntity.setName(updateVehicleRequest.getName());
                State=Strings.found.name();
            }
            if(updateVehicleRequest.getLicencePlateNumber()!=null && !updateVehicleRequest.getLicencePlateNumber().isEmpty() || State=="found"){
                //update.set("licence_Plate_number",updateVehicleRequest.getLicence_plate_number());
                vehicleEntity.setLicencePlateNumber(updateVehicleRequest.getLicencePlateNumber());
                State=Strings.cont.name();
            }
            if(updateVehicleRequest.getProp()!=null && ! updateVehicleRequest.getProp().isEmpty() || State=="cont" || State=="found"){
                //update.set("prop",updateVehicleRequest.getProp());
            vehicleEntity.setProp(updateVehicleRequest.getProp());
            }
            vehicleEntity.set_id(vehicleEntity.get_id());
            vehicleRepo.save(vehicleEntity);
        }
       return vehicleEntity;
    }
    public boolean deleteVehicleData(String VIN){
        VehicleEntity vehicleEntity=vehicleRepo.findByVIN(VIN);
        if(vehicleEntity!=null){
            vehicleRepo.delete(vehicleEntity);
            return true;
        }
        return false;
    }


    public List<VehicleEntity> getVehicleByProperty(String Name, String Value){

        PropData propData=new PropData(Name,Value);
        List<VehicleEntity> vehicleEntities= vehicleRepo.findByprop(propData);
        return vehicleEntities;
    }

    public List<VehicleEntity> getVehicleByLicence(String licence_PLate_number){

        List<VehicleEntity> vehicleEntities=vehicleRepo.findBylicencePlateNumber(licence_PLate_number);
        return vehicleEntities;
    }

}
