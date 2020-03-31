package br.com.sisdb.vendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.domains.Produto;
import br.com.sisdb.vendas.repositories.CategoriaRepository;
import br.com.sisdb.vendas.repositories.ProdutoRepository;

@SpringBootApplication
public class AppVendasIonicApplication  implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepos;
	
	@Autowired
	private ProdutoRepository prodRepos;
	
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
	}

}
