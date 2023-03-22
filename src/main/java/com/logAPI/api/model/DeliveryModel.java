package com.logAPI.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.logAPI.domain.model.StatusDelivery;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeliveryModel {
	private Long id;
	private String clientName;
	private ReceiverModel receiver;
	private BigDecimal rate;
	private StatusDelivery status;
	private OffsetDateTime dateRequest;
	private OffsetDateTime dateFinalization;
}
