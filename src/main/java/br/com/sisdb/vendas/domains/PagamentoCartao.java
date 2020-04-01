package br.com.sisdb.vendas.domains;

import javax.persistence.Entity;

import br.com.sisdb.vendas.domains.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {

	
	
	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;

	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
