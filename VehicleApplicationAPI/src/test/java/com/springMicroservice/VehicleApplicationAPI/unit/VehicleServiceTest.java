package com.springMicroservice.VehicleApplicationAPI.unit;

import com.springMicroservice.VehicleApplicationAPI.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {


    Logger logger= LoggerFactory.getLogger(VehicleServiceTest.class);

    @InjectMocks
    private VehicheService vehicheService;

    @Mock
    private VehicleRepo vehicleRepo;

    private List<VehicleEntity> vehicleEntities = new ArrayList<VehicleEntity>();
    @BeforeEach
    public void intializeSeedData(){

        List<PropData> propList=new ArrayList<PropData>();
        PropData p1=new PropData("Color","B");
        PropData p2=new PropData("Size","Big");
        PropData p3=new PropData("Color","fg");
        PropData p4=new PropData("Size","small");
        propList.add(p1); propList.add(p2);propList.add(p3);propList.add(p4);
        List<PropData> props;
        props=propList;
        vehicleEntities.add(new VehicleEntity(new ObjectId(),"BN1244","adsad","sdsds2444",props.subList(0,2)));
        //vehicleEntities.add(new VehicleEntity(new ObjectId(),"DG1445","Tripura","Kls4567",props.subList(2,4)));
    }


    @Test
    public void test_GetAllVehicleMethod() throws VehicleException {

        Mockito.when(vehicleRepo.findAll()).thenReturn(vehicleEntities);
        List<VehicleEntity> listVehicle=vehicheService.getAllVehicle();
        assertEquals(1,vehicleEntities.size());
        verify(vehicleRepo,times(1)).findAll();
    }

    @Test
    public void test_CreateNewVehicle() throws VehicleException {
        //Mockito.when(vehicleRepo.save(any(VehicleEntity.class))).thenReturn(vehicleEntities.get(0));
        VehicleEntity vehicleEntity_Saved=vehicheService.createNewVehicle(
                new CreateVehicleRequest(vehicleEntities.get(0).get_id(),
                        vehicleEntities.get(0).getVIN(),
                        vehicleEntities.get(0).getName(),
                        vehicleEntities.get(0).getLicencePlateNumber(),
                        vehicleEntities.get(0).getProp()));
        assertEquals(vehicleEntity_Saved.get_id(),vehicleEntities.get(0).get_id());
    }

    @Test
    public void test_UpdateVehicle() throws  VehicleException{

       Mockito.when(vehicleRepo.findByVIN(vehicleEntities.get(0).getVIN())).thenReturn(vehicleEntities.get(0));

        VehicleEntity updated_value=vehicheService.updateVehicle(
                new updateVehicleRequest(vehicleEntities.get(0).getVIN(),
                        vehicleEntities.get(0).getName(),
                        vehicleEntities.get(0).getLicencePlateNumber(),
                        vehicleEntities.get(0).getProp()));
        assertEquals(updated_value.get_id(),vehicleEntities.get(0).get_id());

    }

    @Test
    public void test_deleteVehicleData(){
        Mockito.when(vehicleRepo.findByVIN(vehicleEntities.get(0).getVIN())).thenReturn(vehicleEntities.get(0));
        Boolean fg= vehicheService.deleteVehicleData(vehicleEntities.get(0).getVIN());
        assertEquals(fg.booleanValue(),true);
    }



}
