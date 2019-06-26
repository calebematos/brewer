package br.com.calebematos.brewer.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.repository.CervejaRepository;
import br.com.calebematos.brewer.session.TabelasItensSession;

@Service
public class VendaService {

	@Autowired
	private CervejaRepository cervejaRepository;

	@Autowired
	private TabelasItensSession tabelaItens;

	public List<ItemVenda> adicionarItem(String uuid, Long codigoCerveja) {
		Cerveja cerveja = cervejaRepository.findOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid, cerveja, 1);
		return tabelaItens.getItens(uuid);
	}

	public List<ItemVenda> alterarQuantidade(String uuid, Cerveja cerveja, Integer quantidade) {
		tabelaItens.alterarQuantidadeItem(uuid, cerveja, quantidade);
		return tabelaItens.getItens(uuid);
	}

	public List<ItemVenda> excluirItem(String uuid, Cerveja cerveja) {
		tabelaItens.excluirItem(uuid, cerveja);
		return tabelaItens.getItens(uuid);
	}

	public BigDecimal obterValorTotal(String uuid) {
		return tabelaItens.getValorTotal(uuid);
	}

}
