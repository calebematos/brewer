package br.com.calebematos.brewer.service.event.venda;

import br.com.calebematos.brewer.model.Venda;

public class VendaEvent {

	private Venda venda;

	public VendaEvent(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}
	
}
