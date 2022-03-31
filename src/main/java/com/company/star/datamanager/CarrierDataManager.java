package com.company.star.datamanager;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.company.star.db.model.Carrier;
import com.company.star.db.model.CarrierStatus;
import com.company.star.db.model.Shipment;
import com.company.star.db.repository.CarrierRepo;

import com.company.star.db.repository.CarrierStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarrierDataManager extends DataManager {


	@Autowired
	CarrierRepo repository;

	@Autowired
	CarrierStatusRepo carrierStatusRepo;
	public Carrier saveCarrier(Carrier newCarrier) {

		return repository.save(newCarrier);
	}

	public Carrier getCarrierById(int id) {
		try {
			Optional<Carrier> optional = repository.findById(id);

			if (optional.isPresent()) {
				return optional.get();
			}

			throw new EntityNotFoundException("Carrier with id: " + id + " wasn't found");
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("Carrier with id: " + id + " wasn't found");
		}
	}
	public Carrier getShipmentById(int id) {
		try {
			Optional<Carrier> optional = repository.findById(id);

			if (optional.isPresent()) {
				return optional.get();
			}

			throw new EntityNotFoundException("Fedex Shipment with id: " + id + " wasn't found");
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("Fedex Shipment with id: " + id + " wasn't found");
		}
	}

	public int getUndeliveredShipmentsForFedex(int carrierId) {
		//assume fedex carrierType = 1
		List<Carrier> records = repository.findUndeliveredByCarrierIdAndCarrierType(carrierId, 1);
		if (records == null || records.isEmpty()) {
			return 0;
		} else {
			return records.size();
		}
	}

	public Carrier getRecordForCarrierWeight(int weight) {
		return repository.getCarrierForWeight(weight);
	}
		public List<Carrier> getShipmentFedexByCarrierServiceID(String carrierServiceID) {

		List <Carrier> records =  repository.findByCarrierServiceID("fedexAIR");
		if (records == null || records.isEmpty()) {
			throw new EntityNotFoundException("Carrier with id: " + carrierServiceID + " wasn't found");
		} else {
			return records;
		}
	}

	public boolean updateRecordCarrier(Carrier record) {
		String carrier = record.getCarrier();
		int carrierId = record.getCarrierId(1);
		int carrierType = record.getCarrierType(2);
		int updated = repository.updateRecordCarrier(carrier, carrierId, carrierType);
		if (updated != 0) {
		return true;
		} else {
			throw new EntityNotFoundException(
					"Couldn't find matching record with carrierId " + carrierId + " and carrierType " + carrierType);
		}
	}

	public boolean updateOrderIsConfirmed(Carrier record) {
		boolean isConfirmed = record.getIsConfirmed();
		String carrierId = String.valueOf(record.getCarrierId(1));
		int carrierType = record.getCarrierType(2);
		int updated = repository.updateOrderIsConfirmed(isConfirmed, carrierId, carrierType,record.getWeight());
		if (updated != 0) {
			return true;
		} else {
			throw new EntityNotFoundException("Couldn't find matching record");
		}
	}

	public CarrierStatus saveCarrierStatus(CarrierStatus entity) {
		return carrierStatusRepo.save(entity);
	}

	public List<Carrier> getRecordByWeight(BigDecimal weight) {
		List<Carrier> records = repository.getRecordByWeight(weight);
		if (records == null || records.isEmpty()||(records.size()==0)) {

			return null;
		} else {
//			throw new EntityNotFoundException("Couldn't find matching imei in the system");
			return records;
		}
	}

	public List<Carrier> getConfirmedOrder(int carrierId, int carrierType) {
		return repository.getRecordByIdsAndConfirmed(carrierId, carrierType);
	}

}
