package br.com.calebematos.brewer.dto;

import java.math.BigDecimal;

public class ValorItensEstoque {

	private BigDecimal valor;
	private Long totalItens;

	public ValorItensEstoque() {

	}

	public ValorItensEstoque(BigDecimal valor, Long totalItens) {
		this.valor = valor;
		this.totalItens = totalItens;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(Long totalItens) {
		this.totalItens = totalItens;
	}

}
