package br.com.sisdb.vendas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sisdb.vendas.services.exception.DataIntegrityException;
import br.com.sisdb.vendas.services.exception.ObjctNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(ObjctNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjctNotFoundException e, HttpServletRequest request){
		
		StandardError error = new  StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError error = new  StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
