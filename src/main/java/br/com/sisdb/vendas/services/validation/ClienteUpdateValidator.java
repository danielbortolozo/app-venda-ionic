package br.com.sisdb.vendas.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.sisdb.vendas.domains.Cliente;
import br.com.sisdb.vendas.domains.enums.TipoCliente;
import br.com.sisdb.vendas.dto.ClienteDTO;
import br.com.sisdb.vendas.dto.ClienteNewDTO;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.resources.exception.FieldMessage;
import br.com.sisdb.vendas.services.validation.util.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
		
	@Override	
	public void initialize(ClienteUpdate ann) {
	}
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	   
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Long uriId = Long.parseLong(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();
	    
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já em uso."));
		}
		
	   for (FieldMessage e : list) {
	      context.disableDefaultConstraintViolation();
	      context.buildConstraintViolationWithTemplate(e.getMessage())
	      .addPropertyNode(e.getFieldName()).addConstraintViolation();
	   }	   
	   return list.isEmpty();
	}	
}
