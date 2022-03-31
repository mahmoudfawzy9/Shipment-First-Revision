package com.company.star.datamanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.company.star.db.model.Shipment;
import com.company.star.db.model.ShipmentStatus;
import com.company.star.db.repository.CarrierRepo;
import com.company.star.db.repository.ShipmentRepo;
import com.company.star.db.repository.ShipmentStatusRepo;
import com.company.star.exception.FcmException;
import com.company.star.model.FedexShipmentSender;
import com.company.star.model.ShipmentSender;
import com.company.star.utils.Constants;
import com.company.star.utils.GsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import com.company.star.db.model.FCMNotificationMessageObject;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;

@Component
@Transactional
public class ShipmentDataManager extends DataManager {
	private static final Logger l = LoggerFactory.getLogger(ShipmentDataManager.class);
	@Autowired
	ShipmentRepo shipmentRepo;
	@Autowired
	ShipmentStatusRepo shipmentStatusRepo;
	@Autowired
	CarrierRepo carrierRepo;

	@Autowired
	FirebaseMessaging messages;

	public ResponseEntity<String> sendShipment(Shipment shipment, ShipmentStatus shipmentStatus, String carrier,
											   Map<String, String> data) {

		ShipmentSender sender = new FedexShipmentSender(shipment, shipmentStatus, carrier, data);

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(getAccessToken());
		headers.set("Content-Type", Constants.CONTENT_TYPE);

		String reqBody = GsonProvider.getGsonSnakeCase().toJson(sender);
		l.debug("notification: " + reqBody);

		HttpEntity<String> entity = new HttpEntity<>(reqBody, headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(Constants.FCM_URL, HttpMethod.POST, entity, String.class);

			l.debug("respones from fcm");
			l.debug(response.getBody());
			return response;
		} catch (HttpStatusCodeException e) {
			l.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
		}
	}

	public Shipment getShipmentById(int id) {
		try {
			Optional<Shipment> optional = shipmentRepo.findById(id);

			if (optional.isPresent()) {
				return optional.get();
			}

			throw new EntityNotFoundException("UPS Shipment with id: " + id + " wasn't found");
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("UPS Shipment with id: " + id + " wasn't found");
		}
	}
	public List<Shipment> getRecordByWeight(BigDecimal weight) {
		List<Shipment> records = shipmentRepo.getRecordByWeight(weight);
		if (records == null || records.isEmpty()||(records.size()==0)) {

			return null;
		} else {
			//throw new EntityNotFoundException("Couldn't find matching imei in the system");
			return records;
		}
	}

//	public boolean checkOrderIsConfirmed(int id) {
//		boolean isConfirmed = record.getIsConfirmed();
//		int carrierId = record.getCarrierId(1);
//		int carrierType = record.getCarrierType(2);
//		int updated = shipmentRepo.checkOrderIsConfirmed(isConfirmed, carrierId, carrierType,record.getWeight());
//		if (updated != 0) {
//			return true;
//		} else {
//			throw new EntityNotFoundException("Couldn't find matching record");
//		}
//	}


	//get fedex shipment by carrierId and carrierType
	public List<Shipment> getShipmentForFedex(int carrierId) {

		List<Shipment> records = shipmentRepo.findByCarrierIdAndCarrierType(carrierId, 1);
		if (records == null || records.isEmpty()) {
			throw new EntityNotFoundException("Shipment for carrier with id: " + carrierId + " wasn't found");
		} else {
			return records;
		}
	}

	public List<Shipment> getShipmentUPSByShipmentServiceID(String shipmentServiceID) {
		List<Shipment> records = shipmentRepo.findByShipmentServiceID("UPSExpress");
		if (records == null || records.isEmpty()) {
			throw new EntityNotFoundException("Shipment with id: " + shipmentServiceID + " wasn't found");
		} else {
			return records;
		}
	}

	public int getDeliveredShipmentUPS(int shipmentServiceID){
		List<Shipment> records = shipmentRepo.findDeliveredByCarrierIdAndCarrierType(shipmentServiceID, 3);
		if (records == null || records.isEmpty()) {
			throw new EntityNotFoundException("Shipments for UPS with id: " + shipmentServiceID + " wasn't found");
		} else {
			return records.size();
		}
	}


	public List<Shipment> getShipmentForUPS(int shipmentServiceId) {
		//assume ups carrierType = 3
		List<Shipment> records = shipmentRepo.findByCarrierIdAndCarrierType(shipmentServiceId, 3);
		if (records == null || records.isEmpty()) {
			throw new EntityNotFoundException("Shipments for UPS with id: " + shipmentServiceId + " wasn't found");
		} else {
			return records;
		}

	}

//	public int getUndeliveredShipmentsForFedex(int carrierId) {
//		//assume fedex carrierType = 1
//		List<Shipment> records = shipmentRepo.findUndeliveredByCarrierIdAndCarrierType(carrierId, 1);
//		if (records == null || records.isEmpty()) {
//			return 0;
//		} else {
//			return records.size();
//		}
//	}

	public int getUndeliveredShipmentForUPS(int shipmentId) {
		//assume ups carrierType = 3
		List<Shipment> records = shipmentRepo.findUndeliveredByCarrierIdAndCarrierType(shipmentId, 3);
		if (records == null || records.isEmpty()) {
			return 0;
		} else {
			return records.size();
		}

	}

	public Shipment saveShipment(Shipment entity) {
		return shipmentRepo.save(entity);
	}

	public ShipmentStatus saveShipmentStatus(ShipmentStatus entity) {
		return shipmentStatusRepo.save(entity);
	}

//	public ShipmentStatus getDeliveredShipments(int shipmentId, int carrierId, boolean isDelivered, int carrierType, BigDecimal weight){
//		List<Shipment> records = shipmentRepo.findUndeliveredByCarrierIdAndCarrierType(shipmentId, 3);
//		List<Carrier> carriers = carrierRepo.findUndeliveredByCarrierIdAndCarrierType(carrierId, 1);
//		if (records == null && carriers ==null){
//			return null;
//		}else if(!records.isEmpty() || !carriers.isEmpty() ){
//			return shipmentStatusRepo.updateShipmentIsDelivered(isDelivered, carrierId, carrierType, weight);
//		}
//		return null;
//	}

	// -----------------------------Helper
	// Methods---------------------------------//
	private String getAccessToken() {
		try {

			GoogleCredentials googleCredential = GoogleCredentials
					.fromStream(new FileInputStream("src/main/resources/serviceAccountKey.json"))
					.createScoped(Arrays.asList(Constants.SCOPES));
			googleCredential.refresh();// use this not refreshAccessToken
			String accessToken = googleCredential.getAccessToken().getTokenValue();
			l.debug("token: " + accessToken);
			return accessToken;
		} catch (IOException e) {
			// throw new IOException("Working");
			throw new FcmException(null, "error reading fcm configuration ", e.getCause(), null);
		}

	}
}
