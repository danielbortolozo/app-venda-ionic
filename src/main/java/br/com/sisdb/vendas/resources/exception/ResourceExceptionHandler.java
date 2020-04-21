package br.com.sisdb.vendas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sisdb.vendas.services.exception.AuthorizationException;
import br.com.sisdb.vendas.services.exception.DataIntegrityException;
import br.com.sisdb.vendas.services.exception.HttpMessageNotReadableException;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(ObjctNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjctNotFoundException e, HttpServletRequest request){
		
		StandardError error = new  StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> messageNotReadab(HttpMessageNotReadableException e, HttpServletRequest request){
		
		StandardError error = new  StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError error = new  StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError error = new  ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação!", System.currentTimeMillis());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		
		StandardError error = new  StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	
}
