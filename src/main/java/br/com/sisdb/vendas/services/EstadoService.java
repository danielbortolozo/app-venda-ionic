package br.com.sisdb.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sisdb.vendas.domains.Estado;
import br.com.sisdb.vendas.dto.EstadoDTO;
import br.com.sisdb.vendas.repositories.EstadoRepository;

@Service
public class EstadoService {

	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Transactional
	public Estado salvar(Estado estado) {		
		estado.setId(null);	
		return estadoRepo.save(estado);
	}
	
	public List<Estado> findAll(){		
		return estadoRepo.findAllByOrderByNome();
	}
	
	 
	public Estado fromDTO(EstadoDTO dto) {
		Estado estado = new Estado(null, dto.getNome());		
		return estado;
	}
	
}
