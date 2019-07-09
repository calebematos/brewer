package br.com.calebematos.brewer.service.exception;

public class ImpossivelEcluirEntidadeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ImpossivelEcluirEntidadeException(String msg) {
		super(msg);
	}

}
