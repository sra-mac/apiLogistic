package com.logAPI.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logAPI.domain.model.Delivery;
import com.logAPI.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliveryFinalizationService {
	
	private DeliveryRepository deliveryRepository;
	private DeliverySearchService deliverySearchService;
	
	@Transactional
	public void finalize(Long deliveryId) {
		Delivery delivery = deliverySearchService.search(deliveryId);
		
		delivery.finalize();
		
		deliveryRepository.save(delivery);
	}
}
