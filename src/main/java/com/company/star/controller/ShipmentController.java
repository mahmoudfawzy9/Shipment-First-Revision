package com.company.star.controller;

import java.math.BigDecimal;
import java.util.List;

import com.company.star.db.model.Carrier;
import com.company.star.db.model.Shipment;
import com.company.star.service.CarrierService;
import com.company.star.service.ShipmentService;
//import com.company.star.service.CarrierService;
import com.company.star.utils.ApiResponse;
import com.company.star.utils.GsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.star.form.ShipmentForm;
import com.company.star.form.CarrierForm;

@RestController
@RequestMapping(path = "/api/json/shipments")
public class ShipmentController {

	@Autowired
	CarrierService carrierService;
	@Autowired
    ShipmentService shipmentService;

	private static final String SUCCESS = "success";

	// --------------------------------GET----------------------------------------------
	@GetMapping(path = "/{carrierServiceID}", produces = "application/json")
	public String getFedexShipment(@PathVariable String carrierServiceID) {
		List<Carrier> fedexShipment = carrierService.getFedexShipment(carrierServiceID);
		return constructResponse(fedexShipment, SUCCESS);
	}

	@GetMapping(path = "/shipments/{shipmentServiceId}", produces = "application/json")
	public String getUPSShipment(@PathVariable String shipmentServiceId) {
		List<Shipment> upsShipment = shipmentService.getUPSShipmentByShipmentServiceID(shipmentServiceId);
		return constructResponse(upsShipment, SUCCESS);
	}

	@GetMapping(path = "/ups-shipment-object/{upsShipmentId}", produces = "application/json")
	public Shipment getUPSShipmentByID(@PathVariable int upsShipmentId){
		Shipment upsShipment = shipmentService.getShipmentById(upsShipmentId);
		return upsShipment;
	}

	@GetMapping(path = "/fedex-shipment-object/{fedexShipmentId}", produces = "application/json")
	public Carrier getFedexShipmentByID(@PathVariable int fedexShipmentId){
		Carrier fedex = carrierService.getShipmentById(fedexShipmentId);
		return fedex;
	}

	@GetMapping(path = "/shipments/{carrierServiceId}/count", produces = "application/json")
	public String getUPSUndeliveredShipmentCount(@PathVariable int carrierServiceId) {
		int count = shipmentService.geUPSShipmentUndeliveredCount(carrierServiceId);
		return constructResponse(count, SUCCESS);
	}

	@GetMapping(path = "/shipment-by-weight/{weight}", produces = "application/json")
	 String getUPSShipmentByWeight(@PathVariable BigDecimal weight){
		List<Shipment> ups = shipmentService.getRecordByWeight(weight);
		return constructResponse(ups, SUCCESS);
	}

	@GetMapping(path = "shipment/shipment-by-weight/{weight}", produces = "application/json")
	String getFedexShipmentByWeight(@PathVariable BigDecimal weight){
		List<Carrier> fedex = carrierService.getRecordByWeight(weight);
		return constructResponse(fedex, SUCCESS);
	}

	@GetMapping(path = "/{shipmentServiceId}/count", produces = "application/json")
	public String getFedexUndeliveredShipmentCount(@PathVariable int shipmentServiceId) {
		int count = carrierService.getFedexUndeliveredShipment(shipmentServiceId);
		return constructResponse(count, SUCCESS);
	}

//	@GetMapping(path = "/{shipmentId}", produces = "application/json")
//	public String getShipmentById(@PathVariable int shipmentId) {
//		Shipment shipment = shipmentService.getShipmentById(shipmentId);
//		return constructResponse(shipment, SUCCESS);
//	}

	// ------------------------POST--------------------------
	//ups shipment delivered
	@PostMapping(path = "/{shipmentId}/is-delivered", produces = "application/json")
	public String markUPSShipmentAsDelivered(@PathVariable int shipmentId) {
		boolean done = shipmentService.markShipmentAsDelivered(shipmentId);
		return constructResponse(done, SUCCESS);
	}

	//fedex shipment marked as delivered
	@PostMapping(path = "shipments/{carrierId}/is-delivered", produces = "application/json")
	public String markFedexShipmentAsDelivered(@PathVariable int carrierId) {
		boolean done = carrierService.markShipmentAsDelivered(carrierId);
		return constructResponse(done, SUCCESS);
	}

//FEDEX
	@PostMapping(path = "/carrier", produces = "application/json")
	public String getCarrier(@RequestBody CarrierForm newCarrier) {
		return carrierService.saveCarrier(newCarrier);
	}

//	@PostMapping(path = "/carrier", produces = "application/json")
//	public String getCarrier(@RequestBody ShipmentForm newCarrier) {
//		return shipmentService.sendShipment(newCarrier);
//	}

//UPS
	@PostMapping(produces = "application/json")
	public String confirmShipmentSent(@RequestBody ShipmentForm shipment) {
		return shipmentService.sendShipment(shipment);
	}

	@PostMapping(path = "/shipment",produces = "application/json")
	public String postShipmentUPSSent(@RequestBody ShipmentForm shipment) {
		return shipmentService.sendShipment(shipment);
	}

//	@GetMapping(path = "{shipmentId}/check-confirmed",produces = "application/json")
//	 boolean checkConfirmed(@RequestBody int shipmentId){
//		return shipmentService.checkShipmentIsConfirmed(shipmentId);
//	}
//	@PostMapping(path = "/logout", produces = "application/json")
//	public String logout(@RequestBody PackageDetails dto) {
//		return shipmentService.sendShipment((ShipmentForm) dto);
//	}

	// ---------------------------------------------------
	private String constructResponse(Object data, String message) {
		ApiResponse res = new ApiResponse();
		res.setData(data);
		res.setMessage(message);
		return GsonProvider.getGson().toJson(res);
	}
}
