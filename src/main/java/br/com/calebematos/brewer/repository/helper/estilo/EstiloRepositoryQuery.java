package br.com.calebematos.brewer.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.model.Estilo;
import br.com.calebematos.brewer.repository.filter.EstiloFilter;

public interface EstiloRepositoryQuery {

	Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
}
