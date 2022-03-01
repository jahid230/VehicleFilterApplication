package com.springMicroservice.VehicleApplicationAPI.unit;

import com.springMicroservice.VehicleApplicationAPI.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicheService vehicheService;

    @Mock
    private VehicleRepo vehicleRepo;

    @Test
    public void test_FindAllMethod() throws VehicleException {

        List<VehicleEntity> vehicleEntities = new ArrayList<VehicleEntity>();
        List<PropData> propList=new ArrayList<PropData>();
        PropData p1=new PropData("Color","B");
        PropData p2=new PropData("Size","Big");
        propList.add(p1); propList.add(p2);
        vehicleEntities.add(new VehicleEntity(new ObjectId(),"BN1244","adsad","sdsds2444",propList.subList(0,2)));
        Mockito.when(vehicleRepo.findAll()).thenReturn(vehicleEntities);

        List<VehicleEntity> listVehicle=vehicheService.getAllVehicle();
        assertEquals(1,vehicleEntities.size());
        verify(vehicleRepo,times(1)).findAll();
    }
}
