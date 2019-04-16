package br.com.calebematos.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.model.Cliente;
import br.com.calebematos.brewer.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
}
