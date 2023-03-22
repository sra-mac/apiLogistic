package com.logAPI.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logAPI.domain.model.Delivery;
import com.logAPI.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OccurrenceRegisterService {
	private DeliverySearchService deliverySearchService;
	
	@Transactional
	public Occurrence register(Long deliveryId, String description) {
		Delivery delivery = deliverySearchService.search(deliveryId);
		
		return delivery.occurrenceAdd(description);
		
	}
}
