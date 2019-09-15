package br.com.calebematos.brewer.model;

public enum Origem {

	NACIONAL("origem.nacional"), 
	INTERNACIONAL("origem.internacional");

	private String descricao;

	private Origem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
