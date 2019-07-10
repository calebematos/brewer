package br.com.calebematos.brewer.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.repository.CervejaRepository;

@Component
public class VendaListener {

	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for(ItemVenda item: vendaEvent.getVenda().getItens()) {
			Cerveja cerveja = cervejaRepository.findOne(item.getCerveja().getCodigo());
			cerveja.setQuantidadeEstoque(cerveja.getQuantidadeEstoque() - item.getQuantidade());
			cervejaRepository.save(cerveja);
		}
	}
}
