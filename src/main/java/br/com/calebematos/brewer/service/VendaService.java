package br.com.calebematos.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.repository.CervejaRepository;
import br.com.calebematos.brewer.session.TabelaItensVenda;

@Service
public class VendaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private TabelaItensVenda itensVenda;

	public List<ItemVenda> adicionarItem(Long codigoCerveja) {
		Cerveja cerveja = cervejaRepository.findOne(codigoCerveja);
		itensVenda.adicionarItem(cerveja, 1);
		return itensVenda.getItens();
	}

	public List<ItemVenda> alterarQuantidade(Long codigoCerveja, Integer quantidade) {
		Cerveja cerveja = cervejaRepository.findOne(codigoCerveja);
		itensVenda.alterarQuantidadeItem(cerveja, quantidade);
		return itensVenda.getItens();
	}
	
	
}
