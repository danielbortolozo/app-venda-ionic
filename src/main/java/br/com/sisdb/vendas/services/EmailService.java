package br.com.sisdb.vendas.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.sisdb.vendas.domains.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendMail(SimpleMailMessage msg);

}
