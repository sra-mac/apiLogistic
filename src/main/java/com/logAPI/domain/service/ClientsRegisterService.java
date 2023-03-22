package com.logAPI.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logAPI.domain.exception.TradeException;
import com.logAPI.domain.model.Client;
import com.logAPI.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

//Classe responsável pelas regras de negócio
@AllArgsConstructor
@Service
public class ClientsRegisterService {
	private ClientRepository clientRepository;
	
	public Client search(Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(()-> new TradeException("Cliente não encontrado"));
	}
	
	@Transactional
	public Client saveClient(Client client) {
		boolean emailUsed = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(clientExist->!clientExist.equals(client));
		
		if(emailUsed) {
			throw new TradeException("Já existe um cliente cadastrado com este e-mail.");
		}
		return clientRepository.save(client);
	}
	@Transactional
	public void deleteClient(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}
