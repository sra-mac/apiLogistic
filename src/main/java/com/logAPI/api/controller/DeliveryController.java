package com.logAPI.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logAPI.api.converter.DeliveryConverter;
import com.logAPI.api.model.DeliveryModel;
import com.logAPI.api.model.input.DeliveryInput;
import com.logAPI.domain.model.Delivery;
import com.logAPI.domain.repository.DeliveryRepository;
import com.logAPI.domain.service.DeliveryFinalizationService;
import com.logAPI.domain.service.DeliveryRequestService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	private DeliveryRepository deliveryRepository;
	private DeliveryRequestService deliveryRequestService;
	private DeliveryFinalizationService deliveryFinalizationService;
	private DeliveryConverter deliveryConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryModel request(@Valid @RequestBody DeliveryInput delivery) {
		Delivery deliveryNew = deliveryConverter.toEntity(delivery);
		Delivery requestDelivery = deliveryRequestService.request(deliveryNew);
		return deliveryConverter.toModel(requestDelivery);
	}
	
	@PutMapping("/{deliveryId}/finalization")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalize(@PathVariable Long deliveryId) {
		deliveryFinalizationService.finalize(deliveryId);
	}
	
	@GetMapping
	List<DeliveryModel> listDelivery(){
		return deliveryConverter.toCollectionModel(deliveryRepository.findAll());
	}

	@GetMapping("/{deliveryId}")
	ResponseEntity<DeliveryModel> search(@PathVariable Long deliveryId){
		return deliveryRepository.findById(deliveryId)
				.map( delivery -> ResponseEntity.ok(deliveryConverter.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
}
