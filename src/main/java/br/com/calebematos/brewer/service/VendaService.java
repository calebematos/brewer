package br.com.calebematos.brewer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.dto.VendaMes;
import br.com.calebematos.brewer.dto.VendaOrigem;
import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.model.StatusVenda;
import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.CervejaRepository;
import br.com.calebematos.brewer.repository.VendaRepository;
import br.com.calebematos.brewer.repository.filter.VendaFilter;
import br.com.calebematos.brewer.service.event.venda.VendaEvent;
import br.com.calebematos.brewer.session.TabelasItensSession;

@Service
public class VendaService {

	@Autowired
	private CervejaRepository cervejaRepository;

	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	public List<ItemVenda> adicionarItem(String uuid, Long codigoCerveja) {
		Optional<Cerveja> cervejaOpt = cervejaRepository.findById(codigoCerveja);
		tabelaItens.adicionarItem(uuid, cervejaOpt.get(), 1);
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
	public Venda salvar(Venda venda) {
		if(venda.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar uma venda cancelada");
		}
		if(venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		}else {
			Optional<Venda> vendaExistente = vendaRepository.findById(venda.getCodigo());
			venda.setDataCriacao(vendaExistente.get().getDataCriacao());
		}
		
		if(venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega(), venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}
		
		return vendaRepository.saveAndFlush(venda);
	}

	@Transactional
	public void emitir(Venda venda) {
		venda.setStatus(StatusVenda.EMITIDA);
		salvar(venda);
		
		publisher.publishEvent(new VendaEvent(venda));
	}

	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable) {
		return vendaRepository.filtrar(filtro, pageable);
	}

	public Venda buscarComItens(Long codigo) {
		Venda venda = vendaRepository.buscarComItens(codigo);
		venda.setUuid(UUID.randomUUID().toString());
		for(ItemVenda iten : venda.getItens()) {
			tabelaItens.adicionarItem(venda.getUuid(), iten.getCerveja(), iten.getQuantidade());
		}
		
		return venda;
	}

	@Transactional
	public void cancelar(Venda venda) {
		Optional<Venda> vendaExistenteOpt = vendaRepository.findById(venda.getCodigo());
		if(vendaExistenteOpt.isPresent()) {
			Venda vendaExistente = vendaExistenteOpt.get();
			vendaExistente.setStatus(StatusVenda.CANCELADA);
			vendaRepository.save(vendaExistente);
		}
		
	}

	public List<VendaMes> listarTotalVendaPorMes() {
		return vendaRepository.totalPorMes();
	}

	public List<VendaOrigem> listarTotalVendaPorOrigem() {
		return vendaRepository.totalPorOrigem();
	}

}
