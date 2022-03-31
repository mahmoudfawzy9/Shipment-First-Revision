package com.company.star.service;

import com.company.star.form.EmailForm;
import com.company.star.form.PackageDetails;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.company.star.datamanager.ShipmentDataManager;
import com.company.star.db.model.Shipment;
import com.company.star.db.model.Carrier;
import com.company.star.form.ShipmentForm;



@ExtendWith(MockitoExtension.class)
public class ShipmentServiceTest {

    @Mock
    GeneralValidation validation;

    @Mock
	private ShipmentDataManager shipmentDataManager;
//	@Mock
//	private UserTokenDataManager tokenDataManager;
 
    @InjectMocks
    ShipmentService service;

    Shipment shipmentEntity;
    Carrier carrierEntity;
    ShipmentForm form;
    EmailForm email;
    PackageDetails packageDetails;
    List<Shipment> shipments;
    List<Carrier> carriers;
    JSONObject json;
    List<String> toList;
    List<String>ccList;

    public ShipmentServiceTest(){
        this.form = new ShipmentForm();
        form.setCarrierId(1);
        form.setCarrierType(2);
        form.setActionType(1);
        form.setActionValue("loginScreen");
       //------------------------------------
        this.shipmentEntity =new Shipment(this.form);
        this.shipments =new ArrayList<Shipment>();
        this.shipments.add(this.shipmentEntity);
        
        //---------------------------
        this.carrierEntity=new Carrier();
        carrierEntity.setWidth(BigDecimal.valueOf(100.0));
        carrierEntity.setLength(BigDecimal.valueOf(200.00));
        carrierEntity.setHeight(BigDecimal.valueOf(100.0));
        carrierEntity.setCarrier("Test-Carrier");
        carrierEntity.getCarrierId(1);
        carrierEntity.getCarrierType(2);
        this.carriers =new ArrayList<Carrier>();
        this.carriers.add(this.carrierEntity);
        //--------------------------------
        this.toList=new ArrayList<String>();
        this.ccList =new ArrayList<String>();
        toList.add("mahmoud@hotmail.com");
        ccList.add("mahmoud-fawzi@hotmail.com");
        this.email=new EmailForm();
        email.setBody("email body");
        email.setSubject("email subject");
        email.setTo(toList);
        email.setCc(ccList);
        //----------------------------
        this.packageDetails = new PackageDetails();
        packageDetails.setHeight(BigDecimal.valueOf(100.00));
        packageDetails.setLength(BigDecimal.valueOf(300.00));
        packageDetails.setWidth(BigDecimal.valueOf(200.00));
        
       

    }
  
    @Test
    void getUPSShipment(){

        Mockito.when(shipmentDataManager.getShipmentForUPS(form.getCarrierId(1))).thenReturn(shipments);
        List<Shipment> serviceRes=service.getUPSShipment(form.getCarrierId(1));
        Shipment element=serviceRes.get(0);
        assertThat(element.getName()).isEqualTo(this.shipmentEntity.getName());
        assertThat(element.getActionType()).isEqualTo(this.shipmentEntity.getActionType());
        verify(shipmentDataManager).getShipmentForUPS(form.getCarrierId(1));
        
    }

    @Test
    void getFedexShipment(){
        Mockito.when(shipmentDataManager.getShipmentForFedex(form.getCarrierId(1))).thenReturn(shipments);
        List<Shipment> serviceRes=service.getFedexShipment(form.getCarrierId(1));
        Shipment element=serviceRes.get(0);
        assertThat(element.getName()).isEqualTo(this.shipmentEntity.getName());
        assertThat(element.getCarrierId()).isEqualTo(this.shipmentEntity.getCarrierId());
        verify(shipmentDataManager).getShipmentForFedex(form.getCarrierId(1));
        
    }

    @Test
    void getShipmentById(){
        Mockito.when(shipmentDataManager.getShipmentById(1)).thenReturn(this.shipmentEntity);
        Shipment serviceRes=service.getShipmentById(1);
    
        assertThat(serviceRes.getName()).isEqualTo(this.shipmentEntity.getName());
        assertThat(serviceRes.getActionType()).isEqualTo(this.shipmentEntity.getActionType());
        verify(shipmentDataManager).getShipmentById(1);
    }

    //TODO I may add this new test of I've integrated this microservice with another mobile notification microservice
//    @Test
//    void logout(){
//        Mockito.when(tokenDataManager.getRecordByImei(logout.getImei())).thenReturn(tokens);
//        String serviceRes=service.logout(logout);
//        json = new JSONObject(serviceRes);
//        String message = json.get("message").toString();
//        assertThat(message).isEqualTo("All records for this imei are set to False");
//        verify(tokenDataManager).getRecordByImei(logout.getImei()) ;
//    }

//    @Test
//    void sendNotification(){
//        Mockito.when(tokenDataManager.getLoggedDevice(form.getUserId(),form.getUserType())).thenReturn(carriers);
//        String serviceRes=service.sendShipment(form);
//        json = new JSONObject(serviceRes);
//        String message = json.get("message").toString();
//        assertThat(message).isEqualTo("success");
//
//    }
    
    @Test
    void sendEmail(){
        String serviceRes=service.sendEmail(email);
        json = new JSONObject(serviceRes);
        String message = json.get("message").toString();
        assertThat(message).isEqualTo("success"); 
        
    }
}
