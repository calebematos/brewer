package br.com.calebematos.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
