package com.logAPI.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientIdInput {
	@NotNull
	private Long id;
}
