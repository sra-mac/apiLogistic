package com.logAPI.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.logAPI.api.model.DeliveryModel;
import com.logAPI.api.model.input.DeliveryInput;
import com.logAPI.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryConverter {
	private ModelMapper modelMapper;
	
	public DeliveryModel toModel(Delivery delivery) {
		return modelMapper.map(delivery, DeliveryModel.class);
	}
	
	public List<DeliveryModel> toCollectionModel(List<Delivery> deliveries){
		return deliveries.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Delivery toEntity(DeliveryInput deliveryInput) {
		return modelMapper.map(deliveryInput, Delivery.class);
	}
}
