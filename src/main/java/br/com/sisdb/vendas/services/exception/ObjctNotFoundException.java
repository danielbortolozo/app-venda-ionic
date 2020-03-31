package br.com.sisdb.vendas.services.exception;

public class ObjctNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	

	public ObjctNotFoundException(String mensage) {
		super(mensage);
		
	}
	
	public ObjctNotFoundException(String mensage, Throwable cause) {
		super(mensage, cause);
		
	}
	
	

}
