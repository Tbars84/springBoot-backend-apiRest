package com.bolsaIdeas.springBoot.backend.apiRest.models.services;
import java.util.List;
import com.bolsaIdeas.springBoot.backend.apiRest.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
}
