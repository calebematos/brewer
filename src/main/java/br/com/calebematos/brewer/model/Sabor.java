package br.com.calebematos.brewer.model;

public enum Sabor {

	ADOCICADA("Adocicada"),
	AMARGA("Armaga"),
	FORTE("Forte"),
	FRUTADA("Frutada"),
	SUAVE("Suave");

	private String descricao;

	private Sabor(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
