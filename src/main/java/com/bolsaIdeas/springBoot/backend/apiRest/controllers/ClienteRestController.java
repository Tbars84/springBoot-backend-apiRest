package com.bolsaIdeas.springBoot.backend.apiRest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public 	ResponseEntity<?> show(@PathVariable Long id){

		Cliente clienteResp  = null;
		Map<String , Object> response = new HashMap<>();

		try {
			clienteResp = clienteService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje" , "Error al realizar la consulta a la base de datos");
			response.put("error " , e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String , Object>>(response , HttpStatus.NOT_FOUND);
		}
		
		if(clienteResp == null) {
			response.put("mensaje" , "El cliente con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String , Object>>(response , HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(clienteResp , HttpStatus.OK);
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
	}
	
	@PutMapping("/cliente/{id}")
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
