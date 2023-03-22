package com.logAPI.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logAPI.domain.model.Client;
import com.logAPI.domain.model.Delivery;
import com.logAPI.domain.model.StatusDelivery;
import com.logAPI.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliveryRequestService {
	private DeliveryRepository deliveryRepository;
	private ClientsRegisterService clientsRegisterService;

	@Transactional
	public Delivery request(Delivery delivery) {
		Client client = clientsRegisterService.search(delivery.getClient().getId());
		delivery.setClient(client);
		delivery.setStatus(StatusDelivery.PENDING);
		delivery.setDateRequest(OffsetDateTime.now());
		return deliveryRepository.save(delivery);
	}
}
