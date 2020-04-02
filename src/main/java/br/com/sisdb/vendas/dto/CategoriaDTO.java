package br.com.sisdb.vendas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.sisdb.vendas.domains.Categoria;

public class CategoriaDTO implements Serializable{

    
	private static final long serialVersionUID = 1L;
	private Long id;	
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 40, message = "O nome tem que ser de 5 a 40 caracteres.")
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
}
