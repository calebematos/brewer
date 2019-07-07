package br.com.calebematos.brewer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.model.StatusVenda;
import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.CervejaRepository;
import br.com.calebematos.brewer.repository.VendaRepository;
import br.com.calebematos.brewer.repository.filter.VendaFilter;
import br.com.calebematos.brewer.session.TabelasItensSession;

@Service
public class VendaService {

	@Autowired
	private CervejaRepository cervejaRepository;

	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private VendaRepository vendaRepository;

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
	
	public List<ItemVenda> getItens(String uuid) {
		return tabelaItens.getItens(uuid);
	}

	@Transactional
	public void salvar(Venda venda) {

		if(venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		}
		
		if(venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega(), venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}
		
		vendaRepository.save(venda);
	}

	@Transactional
	public void emitir(Venda venda) {
		venda.setStatus(StatusVenda.EMITIDA);
		salvar(venda);
	}

	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable) {
		return vendaRepository.filtrar(filtro, pageable);
	}

}
