package br.com.sisdb.vendas.services;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdb.vendas.domains.ItemPedido;
import br.com.sisdb.vendas.domains.PagamentoBoleto;
import br.com.sisdb.vendas.domains.Pedido;
import br.com.sisdb.vendas.domains.enums.EstadoPagamento;
import br.com.sisdb.vendas.repositories.ItemPedidoRepository;
import br.com.sisdb.vendas.repositories.PagamentoRepository;
import br.com.sisdb.vendas.repositories.PedidoRepository;
import br.com.sisdb.vendas.repositories.ProdutoRepository;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@Service
public class PedidoService {

	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@Autowired 
	private BoletoService boletoService;
	
	@Autowired
	private ItemPedidoRepository itemPedRepository;
	
	public Pedido find(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Pedido não encontrado Id: "+id + ", Tipos: "+ Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        
        //Se o pagamento for com boleto, o preenchimento do vencimento sera preenchido 
       //automatico acrescentando 7 dias.
        if (obj.getPagamento() instanceof PagamentoBoleto) {
        	PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();        	
        	boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
        }
       
        obj = repository.save(obj);  
        
    
        
        pagamentRepository.save(obj.getPagamento());
        
        for (ItemPedido ip : obj.getItens()) {
        	ip.setDesconto(2.00);
        	ip.setPreco(produtoRepository.getOne(obj.getId()).getPreco());
        	ip.setPedido(obj);
        }
		itemPedRepository.saveAll(obj.getItens());
		return repository.save(obj);
	}
	
	
}









