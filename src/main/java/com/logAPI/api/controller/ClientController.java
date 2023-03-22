package com.logAPI.api.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logAPI.domain.service.ClientsRegisterService;
import com.logAPI.domain.model.Client;
import com.logAPI.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

//construtor feito pelo lombok
@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	private ClientRepository clientRepository;
	private ClientsRegisterService clientsRegisterService;
	
	@GetMapping
	public List<Client> listar(){
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> find(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		/* OR
		Optional<Client> client = clientRepository.findById(clientId);
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		return ResponseEntity.notFound().build();
		*/
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client add(@Valid @RequestBody Client client) {
		return clientsRegisterService.saveClient(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@PathVariable Long clientId, @RequestBody Client client){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		client.setId(clientId);
		client = clientsRegisterService.saveClient(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		clientsRegisterService.deleteClient(clientId);
		return ResponseEntity.noContent().build();
	}
}
