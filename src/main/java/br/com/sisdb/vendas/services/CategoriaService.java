package br.com.sisdb.vendas.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.dto.CategoriaDTO;
import br.com.sisdb.vendas.repositories.CategoriaRepository;
import br.com.sisdb.vendas.services.exception.DataIntegrityException;
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

	public void delete(Long id) {		
		find(id);
		try {
		repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Está categoria está associada a um produto. Error: "
		              +e.getMessage());
		}
	}

	public List<Categoria> findAll() {
		
		return repository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPages, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, 
				Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	
	
	
	
	
	
	
}
