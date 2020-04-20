package br.com.sisdb.vendas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO {
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento Obrigatório.")
	@Length(min=5, message = "O tamanho tem que ser de mínimo de 5 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Preenchimento Obrigatório.")
	@Email(message = "Email inválido.")
	private String email;
	
	private String senha;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente obj) {		
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
