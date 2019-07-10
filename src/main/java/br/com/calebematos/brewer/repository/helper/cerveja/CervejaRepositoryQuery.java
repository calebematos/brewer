package br.com.calebematos.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.dto.CervejaDTO;
import br.com.calebematos.brewer.dto.ValorItensEstoque;
import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.repository.filter.CervejaFilter;

public interface CervejaRepositoryQuery {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> pesquisarPorNomeOuSku(String nomeOuSku);
	
	public ValorItensEstoque valorItensEstoque();

}
