package br.com.calebematos.brewer.repository.helper.venda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.filter.VendaFilter;

public interface VendaRepositoryQuery {

	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);
	
	Venda buscarComItens(Long codigo);
}
