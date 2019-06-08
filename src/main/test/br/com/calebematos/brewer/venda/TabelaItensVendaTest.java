package br.com.calebematos.brewer.venda;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.calebematos.brewer.model.Cerveja;

public class TabelaItensVendaTest {

	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		tabelaItensVenda = new TabelaItensVenda();
	}
	
	@Test
	public void deveCalcularValorTotoalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal valor = new BigDecimal("8.6"); 
		cerveja.setValor(valor);
		
		tabelaItensVenda.adicioarItem(cerveja, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItem() throws Exception {
		Cerveja c1 = new Cerveja();
		BigDecimal v1 = new BigDecimal("8.6"); 
		c1.setValor(v1);
		
		Cerveja c2 = new Cerveja();
		BigDecimal v2 = new BigDecimal("4.77"); 
		c2.setValor(v2);
		
		tabelaItensVenda.adicioarItem(c1, 1);
		tabelaItensVenda.adicioarItem(c2, 2);
		
		assertEquals(new BigDecimal("18.14"), tabelaItensVenda.getValorTotal());
	}
}
