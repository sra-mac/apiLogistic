package com.logAPI.domain.service;

import org.springframework.stereotype.Service;

import com.logAPI.domain.exception.NotFoundEntityException;
import com.logAPI.domain.model.Delivery;
import com.logAPI.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliverySearchService {
	private DeliveryRepository deliveryRepository;

	public Delivery search(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(()-> new NotFoundEntityException("Entrega não encontrada.") );
	}
}
