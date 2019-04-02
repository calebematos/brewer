package br.com.calebematos.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Estilo;

public interface EstiloRepository extends JpaRepository<Estilo, Long>{

	Optional<Estilo> findByNomeIgnoreCase(String nome);
	
}
