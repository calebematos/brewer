package br.com.calebematos.brewer.model;

public enum Sabor {

	ADOCICADA("sabor.adocicada"),
	AMARGA("sabor.amarga"),
	FORTE("sabor.forte"),
	FRUTADA("sabor.frutada"),
	SUAVE("sabor.suave");

	private String descricao;

	private Sabor(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
