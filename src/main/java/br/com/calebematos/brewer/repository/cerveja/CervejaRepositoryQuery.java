package br.com.calebematos.brewer.repository.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.repository.filter.CervejaFilter;

public interface CervejaRepositoryQuery {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
}
