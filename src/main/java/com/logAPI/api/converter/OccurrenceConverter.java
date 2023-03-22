package com.logAPI.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.logAPI.api.model.OccurrenceModel;
import com.logAPI.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OccurrenceConverter {
	private ModelMapper modelMapper;
	
	public OccurrenceModel toModel(Occurrence occurrence) {
		return modelMapper.map(occurrence, OccurrenceModel.class);
	}
	

	public List<OccurrenceModel> toCollectionModel(List<Occurrence> occurrences){
		return occurrences.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
}
