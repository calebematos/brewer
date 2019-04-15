package br.com.calebematos.brewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	List<Cidade> findByEstadoCodigo(Long codigoEstado);
}
