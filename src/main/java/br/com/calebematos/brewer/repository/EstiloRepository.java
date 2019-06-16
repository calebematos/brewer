package br.com.calebematos.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Estilo;
import br.com.calebematos.brewer.repository.helper.estilo.EstiloRepositoryQuery;

public interface EstiloRepository extends JpaRepository<Estilo, Long>, EstiloRepositoryQuery{

	Optional<Estilo> findByNomeIgnoreCase(String nome);
	
}
