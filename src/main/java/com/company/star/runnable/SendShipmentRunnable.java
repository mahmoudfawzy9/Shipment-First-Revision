package com.company.star.runnable;

import java.util.List;
import java.util.Map;

import com.company.star.datamanager.ShipmentDataManager;
import com.company.star.db.model.Shipment;
import com.company.star.db.model.ShipmentStatus;
import com.company.star.db.model.Carrier;
import com.company.star.form.ShipmentForm;
import com.company.star.model.ShipmentSender;

public class SendShipmentRunnable implements Runnable {
	private ShipmentDataManager shipmentDataManager;
	private ShipmentForm form;
	private List<Carrier> carriers;
	private Map<String, String> data;

	@Override
	public void run() {
		sendShipment();
	}

	private void sendShipment() {
		Shipment shipment = new Shipment(form);
		shipment = shipmentDataManager.saveShipment(shipment);

		for (Carrier carrierObj : carriers) {
			String carrier = carrierObj.getCarrier();

			ShipmentStatus shipmentStatus = new ShipmentStatus();
			shipmentStatus.setShipmentId(shipment.getId());
			shipmentStatus.setCarrierId(carrierObj.getId());
			int shipmentType = carrierObj.getCarrierType();

			ShipmentSender sender = ShipmentSender.getInstance(shipmentType);
			sender.setShipmentDataManager(shipmentDataManager);
			sender.sendShipment(shipment, shipmentStatus,carrier, data);
		}
	}

	public ShipmentDataManager getShipmentDataManager() {
		return shipmentDataManager;
	}

	public void setShipmentDataManager(ShipmentDataManager shipmentDataManager) {
		this.shipmentDataManager = shipmentDataManager;
	}

	public ShipmentForm getForm() {
		return form;
	}

	public void setForm(ShipmentForm form) {
		this.form = form;
	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<Carrier> carriers) {
		this.carriers = carriers;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
}
