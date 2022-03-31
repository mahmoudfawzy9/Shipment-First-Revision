package com.company.star.model;

import com.company.star.datamanager.CarrierDataManager;
import com.company.star.datamanager.ShipmentDataManager;
import com.company.star.db.model.Shipment;
import com.company.star.db.model.ShipmentStatus;
import com.company.star.utils.Constants;

import java.util.Map;

public abstract class ShipmentSender {
    private ShipmentDataManager shipmentDataManager;

    private CarrierDataManager carrierDataManager;

    public static ShipmentSender getInstance(int shipmentType) {
        if (shipmentType == Constants.SHIPMENT_TYPE_FEDEX) {
            return new FedexShipmentSender();
        } else if (shipmentType == Constants.SHIPMENT_TYPE_UPS){
            return new UPSShipmentSender();
        }
       return null;
    }

    public abstract void sendShipment(Shipment shipment, ShipmentStatus shipmentStatus,
                                      String carrier, Map<String, String> data);


    public ShipmentDataManager getShipmentDataManager() {
        return shipmentDataManager;
    }

    public void setShipmentDataManager(ShipmentDataManager shipmentDataManager) {
        this.shipmentDataManager = shipmentDataManager;
    }

    public CarrierDataManager getCarrierDataManager() {
        return carrierDataManager;
    }

    public void setCarrierDataManager(CarrierDataManager carrierDataManager) {
        this.carrierDataManager = carrierDataManager;
    }
}
