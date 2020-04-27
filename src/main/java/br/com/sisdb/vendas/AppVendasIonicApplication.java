package br.com.sisdb.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sisdb.vendas.services.S3Service;

@SpringBootApplication
public class AppVendasIonicApplication  implements CommandLineRunner{

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(AppVendasIonicApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
        s3Service.uploadFile("/home/daniel/Imagens/banner.png");		
	}

}






