package br.com.calebematos.brewer.repository.helper.venda;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.dto.VendaMes;
import br.com.calebematos.brewer.dto.VendaOrigem;
import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.filter.VendaFilter;

public interface VendaRepositoryQuery {

	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);
	
	public Venda buscarComItens(Long codigo);
	
	public BigDecimal valorTotalNoAno();
	public BigDecimal valorTotalNoMes();
	public BigDecimal valorTicketMedioNoAno();
	
	public List<VendaMes> totalPorMes();
	public List<VendaOrigem> totalPorOrigem();
}
