package br.com.sisdb.vendas.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@Service
public class ClienteService {

	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Cliente não encontrado Id: "+id + ", Tipos: "+ Cliente.class.getName()));
	}
	
	
}
