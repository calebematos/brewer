package br.com.calebematos.brewer.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.model.Cidade;
import br.com.calebematos.brewer.repository.filter.CidadeFilter;

public interface CidadeRepositoryQuery {

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
}
