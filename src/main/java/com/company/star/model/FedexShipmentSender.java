package com.company.star.model;

import java.util.Map;

import com.company.star.db.model.Shipment;
import com.company.star.db.model.ShipmentStatus;
import com.company.star.exception.FcmException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FedexShipmentSender extends ShipmentSender {

	private static final Logger l = LoggerFactory.getLogger(FedexShipmentSender.class);

	//	@Override
	public void sendShipment(Shipment shipment, ShipmentStatus shipmentStatus, String carrier,
							 Map<String, String> data) {
		// TODO Auto-generated method stub
		ResponseEntity<String> response = getShipmentDataManager().sendShipment(shipment,
				 shipmentStatus, carrier, data);

		if (response.getStatusCode() != HttpStatus.OK) {
			JSONObject json = new JSONObject(response.getBody());
			String status = json.getJSONObject("error").getString("status");
			String message = json.getJSONObject("error").getString("message");

			l.debug("Shipment Status: " + status);

			shipmentStatus.setStatus(status);

			getShipmentDataManager().saveShipment(shipment);

			throw new FcmException(response.getStatusCode(), message, null, shipmentStatus.getCarrierId());
		} else {
			// shipment is sent successfully
			shipmentStatus.setStatus("success");

			getShipmentDataManager().saveShipmentStatus(shipmentStatus);
		}

		l.debug("Done sending shipment");
	}
	public FedexShipmentSender(Shipment shipment, ShipmentStatus shipmentStatus, String carrier, Map<String, String> data) {
		super();
	}
	public FedexShipmentSender() {

	}
////	@Override
//	public void sendShipment(Shipment shipment, ShipmentStatus shipmentStatus, String carrier, Map<String, String> data) {
//
//	}
}
