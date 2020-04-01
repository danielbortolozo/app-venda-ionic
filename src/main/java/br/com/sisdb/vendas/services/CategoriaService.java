package br.com.sisdb.vendas.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.repositories.CategoriaRepository;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Categoria não encontrado Id: "+id + ", Tipos: "+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
	   return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	
}
