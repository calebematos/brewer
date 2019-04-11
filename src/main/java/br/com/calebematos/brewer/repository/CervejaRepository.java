package br.com.calebematos.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.repository.helper.cerveja.CervejaRepositoryQuery;

public interface CervejaRepository extends JpaRepository<Cerveja, Long>, CervejaRepositoryQuery{

}
