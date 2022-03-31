package com.company.star.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.company.star.form.*;
import com.company.star.model.Language;
import com.company.star.runnable.SendEmailRunnable;
import com.company.star.runnable.SendHtmlEmailRunnable;
import com.company.star.utils.ApiResponse;
import com.company.star.utils.GsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.company.star.datamanager.LanguageDataManager;
import com.company.star.datamanager.ShipmentDataManager;
import com.company.star.datamanager.UserDataManager;
import com.company.star.datamanager.CarrierDataManager;
import com.company.star.db.model.Shipment;
import com.company.star.db.model.Carrier;
import com.company.star.utils.MessageUtils;

@Service
@Transactional
public class ShipmentService {
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private GeneralValidation validation;
	@Autowired
	private ShipmentDataManager shipmentDataManager;
	@Autowired
	private CarrierDataManager carrierDataManager;
	@Autowired
	private UserDataManager userDataManager;
	@Autowired
	private LanguageDataManager languageDataManager;
	@Autowired
	private MessageUtils messageUtils;

	Object noData = new Object();
	Map<Carrier, ResponseEntity<String>> shipmentResponses = new HashMap<>();

	public String sendShipment(ShipmentForm form) {

		validation.validateData(form);

		String code = "en";
		if (form.getCarrierType() == 3) {
			int languageId = userDataManager.getUserLanguage(form.getCarrierId());
			Language language = languageDataManager.getLanguageById(languageId);
			code = language.getCode();
		}

		BigDecimal width = form.getWidth();
		BigDecimal length = form.getLength();
		ShipmentForm updatedObj = setPackageDetails("UPSExpress", form);
		List<Shipment> shipments = shipmentDataManager.getRecordByWeight(updatedObj.getWeight());
		Shipment entity = new Shipment(updatedObj);
		Map<Integer, Integer> ids = new HashMap<>();

		if (shipments == null) {
			Shipment persistedObj = shipmentDataManager.saveShipment(entity);
			return constructResponse(persistedObj, "new shipment is added successfully");
		} else {
			for (Shipment record : shipments) {
				ids.put(record.getCarrierId(), record.getCarrierType(1));

				if (record.getCarrierId() == updatedObj.getCarrierId() && record.getCarrierType(1) == updatedObj.getCarrierType(1)) {
					if (record.getIsConfirmed()) {
						record.setCarrier(updatedObj.getshipmentServiceID());
						Shipment persistedObj = shipmentDataManager.saveShipment(record);
						return constructResponse(persistedObj,
								"shipment is updated successfully for package with weight: " + updatedObj.getWeight());
					} else {// IsConfirmed is = false then make it true
						record.setIsConfirmed(true);
						record.setCarrier(updatedObj.getshipmentServiceID());
						Shipment persistedObj = shipmentDataManager.saveShipment(record);
						return constructResponse(persistedObj,
								"shipment updated successfully for package with weight: " + updatedObj.getWeight());
					}

				} else { // package is not confirmed
					record.setIsConfirmed(false);
//					shipmentDataManager.checkOrderIsConfirmed(record);
					Shipment persistedObj = shipmentDataManager.saveShipment(entity);
					return constructResponse(persistedObj,
							"another record added for weight " + updatedObj.getWeight() + " to a new package");
				}
			}
			Shipment persistedObj = shipmentDataManager.saveShipment(entity);
			return constructResponse(persistedObj, "new shipment added successfully to the system");
		}
	}


	public String sendEmail(EmailForm form) {
		validation.validateData(form);

		SendEmailRunnable runnable = new SendEmailRunnable();
		runnable.setForm(form);

		new Thread(runnable).start();
		return constructResponse(noData, "success");

	}

//	public String confirmed(ShipmentForm form) {
//		validation.validateData(form);
//		List<Shipment> shipments = shipmentDataManager.getRecordByWeight(form.getWeight());
//
//		if (shipments == null) {
//			throw new EntityNotFoundException("Couldn't find matching weight in the system");
//
//		} else {
//			for (Shipment shipment : shipments) {
//
//				shipments.setIsConfirmed(false);
//				carrierDataManager.updateOrderIsConfirmed(shipments);
//
//			}
//			return constructResponse(noData, "All records for this imei are set to False");
//		}
//	}

	public Shipment getShipmentById(int id) {
		return shipmentDataManager.getShipmentById(id);
	}

	public List<Shipment> getFedexShipment(int id) {
		// where condition >carrierType=1
		return shipmentDataManager.getShipmentForFedex(id);
	}

	public List<Shipment> getUPSShipmentByShipmentServiceID(String shipmentServiceID) {
		// where condition >shipmentServiceID="UPSExpress"
		return shipmentDataManager.getShipmentUPSByShipmentServiceID(shipmentServiceID);

	}

	public List<Shipment> getUPSShipment(int id) {
		// where condition >carrierType=3
		return shipmentDataManager.getShipmentForUPS(id);
	}

//	public int getCarrierShipmentUndeliveredCount(int id) {
//		// where condition >carrierType=1
//		return shipmentDataManager.getUndeliveredShipmentsForFedex(id);
//
//	}

	public int geUPSShipmentUndeliveredCount(int id) {
		// where condition >carrierType=3
		return shipmentDataManager.getUndeliveredShipmentForUPS(id);
	}

	public int getUPSShipmentDeliveredCount(int id){
		// where condition >carrierType=3
		return shipmentDataManager.getDeliveredShipmentUPS(id);
	}
	public boolean markShipmentAsDelivered(int id) {
		Shipment shipment = shipmentDataManager.getShipmentById(id);
		shipment.setIsDelivered(true);
		shipmentDataManager.saveShipment(shipment);
		return true;
	}

	public List<Shipment> getRecordByWeight(BigDecimal weight){
		return shipmentDataManager.getRecordByWeight(weight);
	}
	//TODO check confirmed shipment
//	public boolean checkShipmentIsConfirmed(int id){
//		return shipmentDataManager.checkOrderIsConfirmed(id);
//	}

	// -----------------------------------------------------------------------------
	private String constructResponse(Object data, String message) {
		ApiResponse res = new ApiResponse();
		res.setData(data);
		res.setMessage(message);
		return GsonProvider.getGson().toJson(res);
	}

	@SuppressWarnings("unchecked")
//	private Map<String, String> constructShipmentData(ShipmentForm form) {
//		Map<String, String> data = new HashMap<>();
//
//		ShipmentAction na = ShipmentAction.fromValue(form.getActionType());
//		LinkedHashMap<String, Object> nd = (LinkedHashMap<String, Object>) form.getActionValue();
//		if (na != null) {
//			JSONObject jo = new JSONObject();
//			switch (na) {
//			case NAVIGATE_TO_SCREEN:
//				ShipmentTrackingAction nna = new ShipmentTrackingAction(nd.get("screenName").toString());
//				nna.addData(nd);
//				jo = new JSONObject(GsonProvider.getGsonSnakeCase().toJson(nna));
//				break;
//			case CALL_NUMBER:
//				ShipmentToHomeAction spha = new ShipmentToHomeAction(nd.get("phone").toString());
//				jo = new JSONObject(GsonProvider.getGsonSnakeCase().toJson(spha));
//				break;
//			case OPEN_LINK:
//				ShipmentToOfficeAction nola = new ShipmentToOfficeAction(nd.get("linkUrl").toString());
//				jo = new JSONObject(GsonProvider.getGsonSnakeCase().toJson(nola));
//				break;
//			default:
//				break;
//			}
//			data = GsonProvider.getGsonSnakeCase().fromJson(jo.toString(), Map.class);
//		}
//
//		return data;
//	}

	private ShipmentForm setPackageDetails(String shipmentServiceID, ShipmentForm obj) {
		if (shipmentServiceID.contains("UPSExpress") || shipmentServiceID.contains("UPS2DAY")) {
			obj.setshipmentServiceID();
			return obj;
//		} else if (shipmentServiceID.contains("length") || carrierServiceID.toLowerCase().contains("")) {
//			obj.setDeviceType(2);
//			return obj;
//		} else if (shipmentServiceID.toLowerCase().contains("") || carrierServiceID.toLowerCase().contains("")) {
//			obj.setDeviceType(3);
//			return obj;
		}
		else {
			return null;
		}
	}

//	public String confirmed(PackageDetails dto) {
//	}
}