package com.springMicroservice.VehicleApplicationAPI;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

enum Strings{
        found,cont,br
}
@RestController
public class VehicleController {

        Logger logger= LoggerFactory.getLogger(VehicleController.class);

        @Autowired
        private VehicheService vehicheService;

        @Autowired
        private VehicleRepo vehicleRepo;


        @GetMapping(value = "/GetVehicle",produces = { "application/json", "application/xml" })
        public List<VehicleEntity> getAllVehicle(){
                try {
                        return vehicheService.getAllVehicle();
                } catch (VehicleException e) {
                        e.printStackTrace();
                }
                return null;
        }
        @PostMapping(value = "/CreateVehicle",produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
        @ResponseBody
        public ResponseEntity<Object> createVechicle (@RequestBody  CreateVehicleRequest vehicleRequest) throws VehicleException, Exception {
                try{
                        logger.info(vehicleRequest.toString());
                        VehicleEntity result=vehicheService.createNewVehicle(vehicleRequest);
                        logger.info(result.toString());
                        if ( !vehicleRequest.getName().isEmpty()
                                && !vehicleRequest.getVIN().isEmpty()
                                && !vehicleRequest.getLicencePlateNumber().isEmpty()
                                && !vehicleRequest.getProp().isEmpty()) {
                                if (vehicheService.createNewVehicle(vehicleRequest) != null) {
                                        return new ResponseEntity<>("New Vehicle Created Successfully", HttpStatus.CREATED);
                                } else
                                        logger.info(vehicleRequest.toString());
                                        return new ResponseEntity<>("Mongo DB could Not Add the db", HttpStatus.INTERNAL_SERVER_ERROR);
                        }

                        return new ResponseEntity<>("Unknown Error", HttpStatus.BAD_REQUEST);

                }catch (VehicleException e){
                    return new ResponseEntity<>(e.getMessage(), e.getStatus());

                }
        }

        @GetMapping(value="/vehicle/find/{VIN}",produces = { "application/json", "application/xml" })
        public EntityModel<VehicleEntity> specificVehicle(@PathVariable String VIN) throws VehicleException{
               VehicleEntity vehicle=vehicheService.getVehicleByVIN(VIN);
               logger.info(vehicle.toString());
               if(vehicle ==null){
                       throw new VehicleNotFoundException("VIN:-"+VIN+ "Not Found in the DB");
               }
                EntityModel<VehicleEntity> vehicleEntityEntityModel=EntityModel.of(vehicle);
                WebMvcLinkBuilder linkToVehicle=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).specificVehicle(VIN));
                vehicleEntityEntityModel.add(linkToVehicle.withRel("The link to get the Specific Vehicle"));
                return vehicleEntityEntityModel;

        }


        @PutMapping(value="/vehicle/update",produces = { "application/json", "application/xml" },consumes = {"application/json","application/xml"})
        public ResponseEntity<Object> updateVehicle(@RequestBody updateVehicleRequest updateVehicleRequest) throws VehicleException{
                VehicleEntity vehicleEntity=vehicheService.updateVehicle(updateVehicleRequest);
                if(vehicleEntity!=null){
                        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
                }
                else
                        return new ResponseEntity<>("Could not Update the request, DB Error", HttpStatus.NOT_FOUND);

        }




        @DeleteMapping(value="/vehicle/delete/{VIN}",produces = { "application/json", "application/xml" })
        public ResponseEntity<Object> deleteVehicle(@PathVariable String VIN){
                logger.info(VIN);
                Boolean status=vehicheService.deleteVehicleData(VIN);
                if(status){
                        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
                }
                else{
                        throw new VehicleNotFoundException("VIN:"+VIN+ " NOT found in DB");

                }

        }



        @GetMapping(value="/vehicle/filterVehicleByProperty/{propName}/{propValue}",produces = { "application/json", "application/xml" })
        public ResponseEntity<List<VehicleEntity>> filterVehicleByProperty(@PathVariable String propName,@PathVariable String propValue) throws ExceptionResponse {
                List<VehicleEntity> vehicleslist=vehicheService.getVehicleByProperty(propName,propValue);
                try{
                      if(vehicleslist!=null){
                              return new ResponseEntity<List<VehicleEntity>>(vehicleslist,HttpStatus.OK);
                      }
                      if(vehicleslist.isEmpty()){
                              List<VehicleEntity> emptyList=new ArrayList<VehicleEntity>();
                              return  new ResponseEntity<List<VehicleEntity>>(emptyList,HttpStatus.OK);
                      }

              }catch(Exception e){
                        throw new ExceptionResponse(new Date(),e.getMessage(),e.getCause().toString());
              }
        return null;

        }

        @GetMapping(value="/vehicle/filterVehicleByLicence_Plate_number/{licence_Plate_number}",produces = { "application/json", "application/xml" })
        public ResponseEntity<List<VehicleEntity>> filterVehicleByLicence_Plate_number(@PathVariable String licence_Plate_number) throws ExceptionResponse{
                List<VehicleEntity> vehicleslist=vehicheService.getVehicleByLicence(licence_Plate_number);
                try{
                        if(vehicleslist!=null){
                                return new ResponseEntity<List<VehicleEntity>>(vehicleslist,HttpStatus.OK);
                        }
                        if(vehicleslist.isEmpty()){
                                List<VehicleEntity> emptyList=new ArrayList<VehicleEntity>();
                                return  new ResponseEntity<List<VehicleEntity>>(emptyList,HttpStatus.OK);
                        }

                }catch(Exception e){
                        throw new ExceptionResponse(new Date(),e.getMessage(),e.getCause().toString());
                }
                return null;


        }
















}
