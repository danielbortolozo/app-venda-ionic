package br.com.sisdb.vendas.dto;

import java.io.Serializable;

import br.com.sisdb.vendas.domains.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private Double preco;
	public ProdutoDTO() {
		
	}
	public ProdutoDTO(Produto prod) {		
		this.id = prod.getId();
		this.descricao = prod.getDescricao();
		this.preco = prod.getPreco();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
//	private List<Categoria> categorias = new ArrayList<>();
	
	
	
}
