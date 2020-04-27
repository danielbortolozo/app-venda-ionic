package br.com.sisdb.vendas.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.domains.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		
		sendMail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Pedido: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
	   return templateEngine.process("email/confirmacaoPedido", context);	
		
	}
	
	@Override
	public  void sendOrderConfirmationHtmlEmail(Pedido obj) {
		
		try {
          MimeMessage mm = prepareMimeMailMessageFromPedido(obj);
		
		  sendHtmlEmail(mm);
	 	}catch (MessagingException e){
	       sendOrderConfirmationEmail(obj);		
		}
	}
	
	protected MimeMessage prepareMimeMailMessageFromPedido(Pedido obj) throws MessagingException {
	   MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	   MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
	   mmh.setTo(obj.getCliente().getEmail());
	   mmh.setFrom(sender);
	   mmh.setSubject("Pedido confirmado! Código: "+obj.getId());
	   mmh.setSentDate(new Date());
	   mmh.setText(htmlFromTemplatePedido(obj),true);
	   
	   return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendMail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
	
	
}

