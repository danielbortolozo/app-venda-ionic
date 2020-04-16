package br.com.sisdb.vendas.services;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private ProdutoService produtoService; 
	
	@Autowired 
	private BoletoService boletoService;
	
	@Autowired
	private ItemPedidoRepository itemPedRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	
	public Pedido find(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjctNotFoundException(
				"Pedido não encontrado Id: "+id + ", Tipos: "+ Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
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
        	ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
        	ip.setPedido(obj);
        }
		itemPedRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return repository.save(obj);
	}
	
	
}









