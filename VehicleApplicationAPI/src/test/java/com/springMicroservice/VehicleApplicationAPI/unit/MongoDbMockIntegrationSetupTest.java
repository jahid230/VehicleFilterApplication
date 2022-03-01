package com.springMicroservice.VehicleApplicationAPI.unit;


import com.springMicroservice.VehicleApplicationAPI.PropData;
import com.springMicroservice.VehicleApplicationAPI.VehicleEntity;
import com.springMicroservice.VehicleApplicationAPI.VehicleRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@DataMongoTest
public class MongoDbMockIntegrationSetupTest {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Test
    public void VehicleTest(){
        List<VehicleEntity> vehicleEntities = new ArrayList<VehicleEntity>();
        List<PropData> propList=new ArrayList<PropData>();
        PropData p1=new PropData("Color","B");
        PropData p2=new PropData("Size","Big");
        propList.add(p1); propList.add(p2);
        vehicleEntities.add(new VehicleEntity(new ObjectId(),"BN1244","adsad","sdsds2444",propList.subList(0,2)));
        vehicleRepo.save(vehicleEntities.get(0));
    }


}
