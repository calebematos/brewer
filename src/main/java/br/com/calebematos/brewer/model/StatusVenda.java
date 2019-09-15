package br.com.calebematos.brewer.model;

public enum StatusVenda {

	ORCAMENTO("status.orcamento"), 
	EMITIDA("status.emitida"), 
	CANCELADA("status.cancelada");

	
	private String descricao;

	private StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
