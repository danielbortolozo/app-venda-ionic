package br.com.sisdb.vendas.services.exception;

public class HttpMessageNotReadableException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	
	public HttpMessageNotReadableException(String mensage) {
		super(mensage);
		
	}
	
	public HttpMessageNotReadableException(String mensage, Throwable cause) {
		super(mensage, cause);
		
	}
	

}
