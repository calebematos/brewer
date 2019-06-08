package br.com.calebematos.brewer.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;

public class TabelaItensVenda {

	private List<ItemVenda> itens = new ArrayList<>();
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicioarItem(Cerveja cerveja, Integer quantidade) {
		ItemVenda item = new ItemVenda(cerveja, quantidade);
		
		itens.add(item);	
		
	}
}
