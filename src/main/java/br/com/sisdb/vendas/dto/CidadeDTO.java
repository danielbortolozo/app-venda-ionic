package br.com.sisdb.vendas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.sisdb.vendas.domains.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	
	
		
	public CidadeDTO() {
		super();
	}
	public CidadeDTO(Cidade cid) {
		super();
		this.nome = cid.getNome();
		this.id = cid.getId();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
