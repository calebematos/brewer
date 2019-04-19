package br.com.calebematos.brewer.service.exception;

public class UsuarioExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioExistenteException(String message) {
		super(message);
	}

}
