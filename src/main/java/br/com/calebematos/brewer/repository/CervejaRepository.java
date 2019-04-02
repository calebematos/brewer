package br.com.calebematos.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Cerveja;

public interface CervejaRepository extends JpaRepository<Cerveja, Long>{

}
