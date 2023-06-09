package com.logAPI.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Receiver {
	@NotBlank
	@Column(name="receiver_name")
	private String name;
	
	@NotBlank
	@Column(name="receiver_place")
	private String publicPlace;
	
	@NotBlank
	@Column(name="receiver_number")
	private String number;
	
	@NotBlank
	@Column(name="receiver_complement")
	private String complement;
	
	@NotBlank
	@Column(name="receiver_neighborhood")
	private String neighborhood;
}
