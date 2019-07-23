package com.bolsaIdeas.springBoot.backend.apiRest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsaIdeas.springBoot.backend.apiRest.models.entity.Cliente;
import com.bolsaIdeas.springBoot.backend.apiRest.models.services.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
}
