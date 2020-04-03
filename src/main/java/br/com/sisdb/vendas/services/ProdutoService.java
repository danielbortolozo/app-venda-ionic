package br.com.sisdb.vendas.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.domains.Produto;
import br.com.sisdb.vendas.repositories.CategoriaRepository;
import br.com.sisdb.vendas.repositories.ProdutoRepository;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository catRepository;
	
	public Produto find(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Produto não encontrado Id: "+id + ", Tipos: "+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String descricao, List<Long> ids, Integer page, Integer linesPerPages, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPages,Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = catRepository.findAllById(ids);
		
		return repository.findDistinctByDescricaoContainingAndCategoriasIn(descricao, categorias, pageRequest);
	}
	
}
