package br.com.sisdb.vendas;

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
import br.com.sisdb.vendas.domains.Produto;
import br.com.sisdb.vendas.domains.enums.TipoCliente;
import br.com.sisdb.vendas.repositories.CategoriaRepository;
import br.com.sisdb.vendas.repositories.CidadeRepository;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.repositories.EnderecoRepository;
import br.com.sisdb.vendas.repositories.EstadoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppVendasIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impresora", 420.00);
		Produto p3 = new Produto(null, "Mouse", 24.83);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		catRepos.saveAll(Arrays.asList(c1,c2));
		prodRepos.saveAll(Arrays.asList(p1,p2,p3));
		
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
		
	}

}
