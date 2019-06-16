package br.com.calebematos.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
