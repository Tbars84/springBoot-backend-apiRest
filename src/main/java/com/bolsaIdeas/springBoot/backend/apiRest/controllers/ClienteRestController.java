package com.bolsaIdeas.springBoot.backend.apiRest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsaIdeas.springBoot.backend.apiRest.models.entity.Cliente;
import com.bolsaIdeas.springBoot.backend.apiRest.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
	@GetMapping("/cliente/{id}")
	public Cliente show(@PathVariable Long id){
		return clienteService.findById(id) ;
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
	}
	
	@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente ,@PathVariable Long id){
		//Set cliente actual en una variable
		Cliente clienteActual = clienteService.findById(id);
		// seteo propiedades de cliente actual
		clienteActual.setApellido(cliente.getApellido());		
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setEmail(cliente.getEmail());

		//Return cliente actual con las actualizaciones correspondientes pero el mismo id
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
}
