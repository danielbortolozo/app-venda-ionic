package br.com.sisdb.vendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sisdb.vendas.domains.Categoria;
import br.com.sisdb.vendas.repositories.CategoriaRepository;

@SpringBootApplication
public class AppVendasIonicApplication  implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepos;
	
	public static void main(String[] args) {
		SpringApplication.run(AppVendasIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "escritório");
		
		catRepos.saveAll(Arrays.asList(c1,c2));
	}

}
