package com.bolsaIdeas.springBoot.backend.apiRest.models.services;
import java.util.List;
import com.bolsaIdeas.springBoot.backend.apiRest.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
}
