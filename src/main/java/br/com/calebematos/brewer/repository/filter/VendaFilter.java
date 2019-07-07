package br.com.calebematos.brewer.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.calebematos.brewer.model.StatusVenda;

public class VendaFilter {

	private Long codigo;
	private StatusVenda status;
	private LocalDate criacaoDe;
	private LocalDate criacaoAte;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private String nomeCliente;
	private String cpfOuCnpjCliente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

	public LocalDate getCriacaoDe() {
		return criacaoDe;
	}

	public void setCriacaoDe(LocalDate criacaoDe) {
		this.criacaoDe = criacaoDe;
	}

	public LocalDate getCriacaoAte() {
		return criacaoAte;
	}

	public void setCriacaoAte(LocalDate criacaoAte) {
		this.criacaoAte = criacaoAte;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfOuCnpjCliente() {
		return cpfOuCnpjCliente;
	}

	public void setCpfOuCnpjCliente(String cpfOuCnpjCliente) {
		this.cpfOuCnpjCliente = cpfOuCnpjCliente;
	}

}
