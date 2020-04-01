package br.com.sisdb.vendas.services.exception;

public class DataIntegrityException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	

	public DataIntegrityException(String mensage) {
		super(mensage);
		
	}
	
	public DataIntegrityException(String mensage, Throwable cause) {
		super(mensage, cause);
		
	}
	
	

}
