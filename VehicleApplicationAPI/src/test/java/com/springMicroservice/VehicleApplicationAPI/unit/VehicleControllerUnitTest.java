package com.springMicroservice.VehicleApplicationAPI.unit;

import com.springMicroservice.VehicleApplicationAPI.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest
public class VehicleControllerUnitTest {



    /* The Request Body of the Vehicle Application API
    {
        "_id": {
            "timestamp": 1644491307,
            "date": 1644491307000
        },
        "licencePlateNumber": null,
        "prop": [
            {
                "propName": "Color",
                "propValue": "White"
            }
        ],
        "name": "ABC",
        "vin": "BMW-New"
    }
     */


    Logger logger= LoggerFactory.getLogger(VehicleControllerUnitTest.class);

    @Autowired
    private MockMvc mockCon;
    @Autowired
    private VehicleController vehicleController;
    @MockBean
    private VehicheService vehicheServiceMock;
    @MockBean
    private VehicleRepo vehicleRepo;
    List<VehicleEntity> vehicleEntities = new ArrayList<VehicleEntity>();
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
    public void GetVehicleShouldReturnMessageFromService() throws ExceptionResponse, VehicleException, Exception {

        when(vehicheServiceMock.getAllVehicle()).thenReturn(vehicleEntities);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/GetVehicle")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result=this.mockCon.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        //logger.info(result.getResponse().getContentAsString());
        String expected="[{\"_id\":"+"{\"timestamp\":"+vehicleEntities.get(0).get_id().getTimestamp()+
                                ","+"\"date\":"+ vehicleEntities.get(0).get_id().getDate().getTime()+ "},"+
                "\"licencePlateNumber\":\""+vehicleEntities.get(0).getLicencePlateNumber()+"\","+
                "\"prop\":[{\"propName\":\""+vehicleEntities.get(0).getProp().get(0).getPropName()+"\","+
                "\"propValue\":\""+vehicleEntities.get(0).getProp().get(0).getPropValue()+"\"},"+
                "{\"propName\":\""+vehicleEntities.get(0).getProp().get(1).getPropName()+"\","+
                "\"propValue\":\""+vehicleEntities.get(0).getProp().get(1).getPropValue()+"\"}],"+
                "\"name\":\""+vehicleEntities.get(0).getName()+"\","+
                "\"vin\":\""+vehicleEntities.get(0).getVIN()+
                "\"}]";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }

    @Test
    public void CreateVechicleShouldCreateSuccessEntry_thenReturn_201() throws VehicleException, Exception {
          String requestBody="{\"_id\":\"" +vehicleEntities.get(0).get_id()+"\","+
                "\"VIN\":\""+vehicleEntities.get(0).getVIN()+"\","+
                "\"Name\":\""+vehicleEntities.get(0).getName()+"\","+
                "\"licencePlateNumber\":\""+vehicleEntities.get(0).getLicencePlateNumber()+"\","+
                "\"prop\":[{\"propName\":\""+vehicleEntities.get(0).getProp().get(0).getPropName()+"\","+
                "\"propValue\":\""+vehicleEntities.get(0).getProp().get(0).getPropValue()+"\"},"+
                "{\"propName\":\""+vehicleEntities.get(0).getProp().get(1).getPropName()+"\","+
                "\"propValue\":\""+vehicleEntities.get(0).getProp().get(1).getPropValue()+"\"}]"+
                "}";
        when(vehicheServiceMock.createNewVehicle(any(CreateVehicleRequest.class))).thenReturn(vehicleEntities.get(0));
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/CreateVehicle").content(requestBody).contentType(MediaType.APPLICATION_JSON);
      MvcResult result=this.mockCon.perform(requestBuilder).andExpect(status().isCreated()).andReturn();
       logger.info("responseBody :"+ result.getResponse().getContentAsString());

    }

    @Test
    public void specificVehicleByVIN_shouldReturnCorrectUserResult() throws Exception {
       when(vehicheServiceMock.getVehicleByVIN(vehicleEntities.get(0).getVIN())).thenReturn(vehicleEntities.get(0));
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/vehicle/find/"+vehicleEntities.get(0).getVIN()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=this.mockCon.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        logger.info("responseBody :"+ result.getResponse().getStatus());
    }

    @Test
    public void specificVehicleByProperty_shouldReturnCorrectUserResult() throws Exception {
        when(vehicheServiceMock.getVehicleByProperty(vehicleEntities.get(0).getProp().get(0).getPropName(),vehicleEntities.get(0).getProp().get(0).getPropValue()))
                .thenReturn(vehicleEntities);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/vehicle/filterVehicleByProperty/"+vehicleEntities.get(0).getProp().get(0).getPropName()
                +"/"+ vehicleEntities.get(0).getProp().get(0).getPropValue())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result=this.mockCon.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        logger.info("responseStatus :"+ result.getResponse().getStatus());
        logger.info("responseBody :"+ result.getResponse().getContentAsString());
    }

    @Test
    public void specificVehicleByLicencePlateNumber_shouldReturnCorrectUserResult() throws Exception {
        when(vehicheServiceMock.getVehicleByLicence(vehicleEntities.get(0).getLicencePlateNumber())).thenReturn(vehicleEntities);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/vehicle/filterVehicleByLicence_Plate_number/"+vehicleEntities.get(0).getLicencePlateNumber())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result=this.mockCon.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        logger.info("responseStatus :"+ result.getResponse().getStatus());
        logger.info("responseBody :"+ result.getResponse().getContentAsString());
        }

    @Test
    public void updateVehicle_shouldReturnCorrectInsertionResponse() throws ExceptionResponse, Exception, VehicleException {
        when(vehicheServiceMock.updateVehicle(any(updateVehicleRequest.class))).thenReturn(vehicleEntities.get(0));
        String requestBody="{\"VIN\":\""+vehicleEntities.get(0).getVIN()+"\","+
                "\"Name\":\""+vehicleEntities.get(0).getName()+"\","+
                "\"licencePlateNumber\":\""+vehicleEntities.get(0).getLicencePlateNumber()+"\","+
                "\"prop\":[{\"propName\":\""+vehicleEntities.get(0).getProp().get(0).getPropName()+"\","+
                "\"propValue\":\""+vehicleEntities.get(0).getProp().get(0).getPropValue()+"\"},"+
                "{\"propName\":\""+vehicleEntities.get(0).getProp().get(1).getPropName()+"\","+
                "\"propValue\":\""+vehicleEntities.get(0).getProp().get(1).getPropValue()+"\"}]"+
                "}";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.put("/vehicle/update").content(requestBody).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=this.mockCon.perform(requestBuilder).andReturn();
        logger.info("responseStatus :"+ result.getResponse().getStatus());
        logger.info("responseBody :"+ result.getResponse().getStatus());
    }

    @Test
    public void deleteVehicle_shouldReturnCorrectDeletionProcess() throws Exception {

        when(vehicheServiceMock.deleteVehicleData(vehicleEntities.get(0).getVIN())).thenReturn(true);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.delete("/vehicle/delete/"+vehicleEntities.get(0).getVIN()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=this.mockCon.perform(requestBuilder).andReturn();
        logger.info("responseStatus :"+ result.getResponse().getStatus());
        logger.info("responseBody :"+ result.getResponse().getStatus());

    }






}

