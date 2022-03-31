package com.company.star.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.star.utils.ApiResponse;
import com.company.star.utils.GsonProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.star.datamanager.CarrierDataManager;
import com.company.star.db.model.Carrier;
import com.company.star.form.CarrierForm;

import javax.transaction.Transactional;

/**
 * Refactor:- //change setDeviceType 2nd condidition to check in a list of
 * possible mobile models change setDeviceType 3rd condition to put hwaui os
 * type
 *
 */

@Service
@Transactional
public class CarrierService {

	@Autowired
	GeneralValidation validation;
	@Autowired
	CarrierDataManager dataManager;

	ModelMapper mapper = new ModelMapper();

	public String saveCarrier(CarrierForm obj) {

		validation.validateData(obj);
		BigDecimal width = obj.getWidth();
		BigDecimal length = obj.getLength();
		CarrierForm updatedObj = setPackageDetails("fedexAIR", obj);
		List<Carrier> carriers = dataManager.getRecordByWeight(updatedObj.getWeight());
		Carrier entity = new Carrier(updatedObj);
		Map<Integer, Integer> ids = new HashMap<>();

		if (carriers == null) {
			Carrier persistedObj = dataManager.saveCarrier(entity);
			return constructResponse(persistedObj, "new carrier added successfully");
		} else {
			for (Carrier record : carriers) {
				ids.put(record.getCarrierId(), record.getCarrierType(3));

				if (record.getCarrierId() == updatedObj.getCarrierId() && record.getCarrierType(3) == updatedObj.getCarrierType(3)) {
					if (record.getIsConfirmed()) {
						record.setCarrier(updatedObj.getCarrierServiceID());
						Carrier persistedObj = dataManager.saveCarrier(record);
						return constructResponse(persistedObj,
								"carrier updated successfully for device with weight: " + updatedObj.getWeight());
					} else {// IsConfirmed is = false
						record.setIsConfirmed(true);
						record.setCarrier(updatedObj.getCarrierServiceID());
						Carrier persistedObj = dataManager.saveCarrier(record);
						return constructResponse(persistedObj,
								"carrier updated successfully for device with weight: " + updatedObj.getWeight());
					}

				} else { // not confirmed package
					record.setIsConfirmed(false);
					dataManager.updateOrderIsConfirmed(record);
					Carrier persistedObj = dataManager.saveCarrier(entity);
					return constructResponse(persistedObj,
							"another record added for weight " + updatedObj.getWeight() + "to a new package");
				}
			}
			Carrier persistedObj = dataManager.saveCarrier(entity);
			return constructResponse(persistedObj, "new carrier added successfully to the system");
		}

	}
	// --------------------Helper Methods--------------------//
//	public Carrier getFedexShipment(int id) {
//		// where condition >carrierType=1
//		return dataManager.getCarrierById(id);
//	}
	public Carrier getShipmentById(int id) {
		return dataManager.getShipmentById(id);
	}

	public List<Carrier> getRecordByWeight(BigDecimal weight){
		return dataManager.getRecordByWeight(weight);
	}

	public List<Carrier> getFedexShipment(String carrierServiceID) {
		// where condition >careerServiceID="fedexAIR"
		return dataManager.getShipmentFedexByCarrierServiceID(carrierServiceID);
	}

	public int getFedexUndeliveredShipment(int carrierType){
		// where carrierType = 1
		return dataManager.getUndeliveredShipmentsForFedex(carrierType);
	}

	public boolean markShipmentAsDelivered(int id) {
		Carrier carrier = dataManager.getShipmentById(id);
		carrier.setIsDelivered(true);
		dataManager.saveCarrier(carrier);
		return true;
	}

	private CarrierForm setPackageDetails(String carrierServiceID, CarrierForm obj) {
		if (carrierServiceID.contains("fedexAIR") || carrierServiceID.contains("fedexGroud")) {
			obj.setCarrierServiceID();
			return obj;
		}
		else {
			return null;
		}
	}

	private String constructResponse(Object data, String message) {
		ApiResponse res = new ApiResponse();
		res.setData(data);
		res.setMessage(message);
		return GsonProvider.getGson().toJson(res);
	}

}
