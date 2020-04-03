package br.com.sisdb.vendas;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.domains.Cidade;
import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.domains.Endereco;
import br.com.sisdb.vendas.domains.Estado;
import br.com.sisdb.vendas.domains.ItemPedido;
import br.com.sisdb.vendas.domains.Pagamento;
import br.com.sisdb.vendas.domains.PagamentoBoleto;
import br.com.sisdb.vendas.domains.PagamentoCartao;
import br.com.sisdb.vendas.domains.Pedido;
import br.com.sisdb.vendas.domains.Produto;
import br.com.sisdb.vendas.domains.enums.EstadoPagamento;
import br.com.sisdb.vendas.domains.enums.TipoCliente;
import br.com.sisdb.vendas.repositories.CategoriaRepository;
import br.com.sisdb.vendas.repositories.CidadeRepository;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.repositories.EnderecoRepository;
import br.com.sisdb.vendas.repositories.EstadoRepository;
import br.com.sisdb.vendas.repositories.ItemPedidoRepository;
import br.com.sisdb.vendas.repositories.PagamentoRepository;
import br.com.sisdb.vendas.repositories.PedidoRepository;
import br.com.sisdb.vendas.repositories.ProdutoRepository;

@SpringBootApplication
public class AppVendasIonicApplication  implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepos;
	
	@Autowired
	private ProdutoRepository prodRepos;
	
	@Autowired
	private CidadeRepository cidadeRepos;
	
	@Autowired
	private EstadoRepository estadoRepos;
	
	@Autowired
	private ClienteRepository cliRepos;
	
	@Autowired
	private EnderecoRepository endRepos;
	
	@Autowired
	private PedidoRepository pedRepos;
	
	@Autowired
	private PagamentoRepository pagRepos;
	
	@Autowired
	private ItemPedidoRepository itemPedRepos;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppVendasIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "escritório");
		Categoria c3 = new Categoria(null, "Cama e banho");
		Categoria c4 = new Categoria(null, "Cozinha");
		Categoria c5 = new Categoria(null, "Contrucao");
		Categoria c6 = new Categoria(null, "Jardinagem");
		Categoria c7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impresora", 420.00);
		Produto p3 = new Produto(null, "Mouse", 24.83);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		c2.getProdutos().addAll(Arrays.asList(p2, p4));
		c3.getProdutos().addAll(Arrays.asList(p5, p6));
		c4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		c5.getProdutos().addAll(Arrays.asList(p8));
		c6.getProdutos().addAll(Arrays.asList(p9, p10));
		c7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(c1, c4));
		p2.getCategorias().addAll(Arrays.asList(c1, c2, c4));
		p3.getCategorias().addAll(Arrays.asList(c1, c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		p11.getCategorias().addAll(Arrays.asList(c7));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		catRepos.saveAll(Arrays.asList(c1,c2, c3, c4, c5, c6, c7));
		
		prodRepos.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade cid1 = new Cidade(null, "Fernandopolis", est2);
		Cidade cid2 = new Cidade(null, "Iturama", est1);
		Cidade cid3 = new Cidade(null, "uberlandia", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepos.saveAll(Arrays.asList(est1, est2));
		
		cidadeRepos.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria silva", "danielborto@fef", "123123123-09", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("17-997841731", "17 99721 5020"));
		
		Endereco end1 = new Endereco(null, "R. Flores", "123", "Apt 12", "Jardins", "15600.900", cli1, cid1);
		Endereco end2 = new Endereco(null, "R. Sao Domigues", "1900", "casa", "Jardins", "19600.900", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));	
		
		
		cliRepos.saveAll(Arrays.asList(cli1));		
		endRepos.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("31/03/2020 10:32"), cli1, end1 );
		Pedido ped2 = new Pedido(null, sdf.parse("01/04/2020 01:23"), cli1, end1);
		
		Pagamento pagt1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		ped1.setPagamento(pagt1);
		
		Pagamento pagt2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/04/2020 00:00"), null);
		ped2.setPagamento(pagt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedRepos.saveAll(Arrays.asList(ped1, ped2));
		pagRepos.saveAll(Arrays.asList(pagt1, pagt2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1.00, 2000.00);	
		ItemPedido ip2 = new ItemPedido(ped2, p3, 0.00, 10.00, 2500.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 5.00, 500.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
	    itemPedRepos.saveAll(Arrays.asList(ip1,ip2,ip3));	
	}

}
