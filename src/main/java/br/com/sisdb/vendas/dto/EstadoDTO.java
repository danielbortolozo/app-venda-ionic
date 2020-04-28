package br.com.sisdb.vendas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.sisdb.vendas.domains.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	
	
	

	public EstadoDTO() {
		super();
	}

	public EstadoDTO(Estado obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();		
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
