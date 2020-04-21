package br.com.sisdb.vendas.services.exception;

public class AuthorizationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	

	public AuthorizationException(String mensage) {
		super(mensage);
		
	}
	
	public AuthorizationException(String mensage, Throwable cause) {
		super(mensage, cause);
		
	}
	
	

}
