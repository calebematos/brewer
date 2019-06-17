package br.com.calebematos.brewer.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;

@SessionScope
@Component
public class TabelasItensSession {

	Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Cerveja cerveja, int quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(cerveja, quantidade);
		tabelas.add(tabela);
	}

	

	public void alterarQuantidadeItem(String uuid, Cerveja cerveja, Integer quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItem(cerveja, quantidade);
	}

	public void excluirItem(String uuid, Cerveja cerveja) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(cerveja);
	}

	public List<ItemVenda> getItens(String uuid) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		return tabela.getItens();
	}
	private TabelaItensVenda buscarTabelaPorUuid(String uuid) {
		return tabelas.stream()
			.filter(t -> t.getUuid().equals(uuid))
			.findAny().orElse(new TabelaItensVenda(uuid));
	}
	
}
