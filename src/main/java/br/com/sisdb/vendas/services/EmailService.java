package br.com.sisdb.vendas.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import br.com.sisdb.vendas.domains.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendMail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage mm);

}
