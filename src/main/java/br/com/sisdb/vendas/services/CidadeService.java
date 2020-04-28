package br.com.sisdb.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.Cidade;
import br.com.sisdb.vendas.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepo;
	
	public Cidade insert(Cidade cidade) {
		cidade.setId(null);
		cidade = cidadeRepo.save(cidade);
		return cidade;
	}
	
	public List<Cidade> findAll() {
		
		return cidadeRepo.findAll(); 
	}
	public List<Cidade> findByEstado(Long estadoId){
		return cidadeRepo.findCidades(estadoId);
	}
}
