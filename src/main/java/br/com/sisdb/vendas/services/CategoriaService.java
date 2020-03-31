package br.com.sisdb.vendas.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	
}
