package br.com.sisdb.vendas.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdb.vendas.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriResource {

	
	@GetMapping
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1L, "informatica" );
		Categoria c2 = new Categoria(2L, "Lazer");
		
		List<Categoria> list = new ArrayList<>();
		
		list.add(c1);
		list.add(c2);
		return list;
	}
	
}
