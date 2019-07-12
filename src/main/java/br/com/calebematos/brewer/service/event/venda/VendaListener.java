package br.com.calebematos.brewer.service.event.venda;

import java.util.Optional;

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
		for (ItemVenda item : vendaEvent.getVenda().getItens()) {
			Optional<Cerveja> cervejaOpt = cervejaRepository.findById(item.getCerveja().getCodigo());
			if (cervejaOpt.isPresent()) {
				Cerveja cerveja = cervejaOpt.get();
				cerveja.setQuantidadeEstoque(cerveja.getQuantidadeEstoque() - item.getQuantidade());
				cervejaRepository.save(cerveja);
			}
		}
	}
}
