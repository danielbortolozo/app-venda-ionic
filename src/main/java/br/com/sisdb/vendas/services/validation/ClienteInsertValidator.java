package br.com.sisdb.vendas.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sisdb.vendas.domains.enums.TipoCliente;
import br.com.sisdb.vendas.dto.ClienteNewDTO;
import br.com.sisdb.vendas.repositories.ClienteRepository;
import br.com.sisdb.vendas.resources.exception.FieldMessage;
import br.com.sisdb.vendas.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	
	@Autowired
	private  ClienteRepository repo;
	
	@Override	
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	   
		List<FieldMessage> list = new ArrayList<>();
	    
		if (objDto.getTipoCli().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		}
		if (objDto.getTipoCli().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}
		
	   for (FieldMessage e : list) {
	      context.disableDefaultConstraintViolation();
	      context.buildConstraintViolationWithTemplate(e.getMessage())
	      .addPropertyNode(e.getFieldName()).addConstraintViolation();
	   }
	   
	   
	   return list.isEmpty();
	}

	
	
	
}
