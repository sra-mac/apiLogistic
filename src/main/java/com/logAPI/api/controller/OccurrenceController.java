package com.logAPI.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logAPI.api.converter.OccurrenceConverter;
import com.logAPI.api.model.OccurrenceModel;
import com.logAPI.api.model.input.OccurrenceInput;
import com.logAPI.domain.model.Delivery;
import com.logAPI.domain.model.Occurrence;
import com.logAPI.domain.service.DeliverySearchService;
import com.logAPI.domain.service.OccurrenceRegisterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery/{deliveryId}/occurrences")
public class OccurrenceController {
	private OccurrenceRegisterService occurrenceRegisterService;
	private OccurrenceConverter occurrenceConverter;
	private DeliverySearchService deliverySearchService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceModel occurrence(@PathVariable Long deliveryId, @Valid @RequestBody OccurrenceInput occurrenceInput) {
		Occurrence occurrenceRegistred = occurrenceRegisterService.register(deliveryId, occurrenceInput.getDescription());
		return occurrenceConverter.toModel(occurrenceRegistred);
	}
	
	@GetMapping
	public List<OccurrenceModel> list(@PathVariable Long deliveryId){
		Delivery delivery = deliverySearchService.search(deliveryId);
		return occurrenceConverter.toCollectionModel(delivery.getOccurrences());
	}
	
}
