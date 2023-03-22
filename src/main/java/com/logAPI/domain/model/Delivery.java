package com.logAPI.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.logAPI.domain.exception.TradeException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Delivery {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;

	private BigDecimal rate;
	
	//evitar que o consumidor da API envie esses dados que são processados dentro da aplicação
	//@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusDelivery status;
	
	private OffsetDateTime dateRequest;
	
	private OffsetDateTime dateFinalization;

	@Embedded //abstrair os dados de outra tabela/classe
	private Receiver receiver;
	
	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
	private List<Occurrence> occurrences = new ArrayList<>();

	public Occurrence occurrenceAdd(String description) {
		// TODO Auto-generated method stub
		Occurrence occurrence = new Occurrence();
		occurrence.setDescription(description);
		occurrence.setRegisterDate(OffsetDateTime.now());
		occurrence.setDelivery(this);
		this.getOccurrences().add(occurrence);
		return occurrence;
	}
	
	public void finalize() {
		if(!StatusDelivery.PENDING.equals(getStatus())) {			
			throw new TradeException("Entrega não pode ser finalizada.");
		}
		setStatus(StatusDelivery.FINALIZED);
		setDateFinalization(OffsetDateTime.now());
	}
}
