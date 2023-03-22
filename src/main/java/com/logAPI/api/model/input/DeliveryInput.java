package com.logAPI.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.logAPI.domain.ValidationGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryInput {
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
	private ClientIdInput client;
	@Valid
	@NotNull
	private ReceiverInput receiver;
	@NotNull
	private BigDecimal rate;
}
