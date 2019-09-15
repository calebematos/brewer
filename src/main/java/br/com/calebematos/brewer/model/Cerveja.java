package br.com.calebematos.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import br.com.calebematos.brewer.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@SKU
	@NotBlank
	private String sku;

	@NotBlank
	private String nome;

	@NotBlank
	@Size(min = 1, max = 50, message = "O tamanho da descrição deve ser entre 1 e 50")
	private String descricao;

	@NotNull
	@DecimalMin("0.01")
	@DecimalMax(value = "999999.99", message = "{cerveja.valor.maximo}")
	private BigDecimal valor;

	@NotNull
	@DecimalMax(value = "100", message = "{cerveja.teor-alcoolico.maximo}")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;

	@NotNull
	@DecimalMax(value = "100", message = "{cerveja.comissao.maximo}")
	private BigDecimal comissao;

	@NotNull
	@Max(value = 9999, message = "{cerveja.quantidade-estoque.maximo}")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sabor sabor;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Origem origem;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;

	private String foto;

	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teoAlcoolico) {
		this.teorAlcoolico = teoAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quatidadeEstoque) {
		this.quantidadeEstoque = quatidadeEstoque;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Transient
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
	}
	
	public boolean temFoto() {		
		return !StringUtils.isEmpty(foto);
	}
	
	public boolean isNova() {
		return this.codigo == null;
	}

	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
