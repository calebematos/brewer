package br.com.calebematos.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.brewer.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{

}
