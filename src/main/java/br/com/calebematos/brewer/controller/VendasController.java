package br.com.calebematos.brewer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.service.VendaService;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private VendaService vendaService;
	
	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		venda.setUuid(UUID.randomUUID().toString());
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		List<ItemVenda> itens = vendaService.adicionarItem(uuid, codigoCerveja);
		return mvTabelaItensVenda(uuid,itens);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Cerveja cerveja, Integer quantidade, String uuid) {
		List<ItemVenda> itens = vendaService.alterarQuantidade(uuid, cerveja, quantidade);
		return mvTabelaItensVenda(uuid,itens);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}")
	public ModelAndView excluirItem(@PathVariable String uuid, @PathVariable("codigoCerveja") Cerveja cerveja) {
		List<ItemVenda> itens = vendaService.excluirItem(uuid, cerveja);
		return mvTabelaItensVenda(uuid, itens);
	}
	
	private ModelAndView mvTabelaItensVenda(String uuid, List<ItemVenda> itens) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", itens);
		mv.addObject("valorTotal", vendaService.obterValorTotal(uuid));
		return mv;
	}
}
