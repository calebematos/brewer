package br.com.calebematos.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.helper.venda.VendaRepositoryQuery;

public interface VendaRepository extends JpaRepository<Venda, Long>, VendaRepositoryQuery{

}
