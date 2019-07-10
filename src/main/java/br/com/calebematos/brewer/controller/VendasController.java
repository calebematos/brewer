package br.com.calebematos.brewer.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.calebematos.brewer.controller.page.PageWrapper;
import br.com.calebematos.brewer.controller.validator.VendaValidator;
import br.com.calebematos.brewer.dto.VendaMes;
import br.com.calebematos.brewer.dto.VendaOrigem;
import br.com.calebematos.brewer.mail.Mailer;
import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.ItemVenda;
import br.com.calebematos.brewer.model.StatusVenda;
import br.com.calebematos.brewer.model.TipoPessoa;
import br.com.calebematos.brewer.model.Usuario;
import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.filter.VendaFilter;
import br.com.calebematos.brewer.service.VendaService;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private VendaService vendaService;

	@Autowired
	private VendaValidator vendaValidator;

	@Autowired
	private Mailer mailer;

	@InitBinder("venda")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(vendaValidator);
	}

	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		if (StringUtils.isEmpty(venda.getUuid()))
			venda.setUuid(UUID.randomUUID().toString());

		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotalItens", vendaService.obterValorTotal(venda.getUuid()));

		return mv;
	}

	@GetMapping
	public ModelAndView pesquisar(VendaFilter filtro, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("venda/PesquisaVendas");
		mv.addObject("todosStatus", StatusVenda.values());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		PageWrapper<Venda> pagina = new PageWrapper<>(vendaService.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", pagina);

		return mv;
	}

	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		venda.setUsuario(usuario);

		vendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso");
		return new ModelAndView("redirect:/vendas/nova");
	}

	@PostMapping(value = "/nova", params = "emitir")
	public ModelAndView emitir(Venda venda, BindingResult result, RedirectAttributes attributes) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		venda.setUsuario(usuario);

		vendaService.emitir(venda);
		attributes.addFlashAttribute("mensagem", "Venda emitida com sucesso");
		return new ModelAndView("redirect:/vendas/nova");
	}

	@PostMapping(value = "/nova", params = "enviarEmail")
	public ModelAndView enviarEmail(Venda venda, BindingResult result, RedirectAttributes attributes) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		venda.setUsuario(usuario);

		venda = vendaService.salvar(venda);
		mailer.enviar(venda);

		attributes.addFlashAttribute("mensagem", String.format("Venda nº%d salva e e-mail enviado", venda.getCodigo()));
		return new ModelAndView("redirect:/vendas/nova");
	}

	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		List<ItemVenda> itens = vendaService.adicionarItem(uuid, codigoCerveja);
		return mvTabelaItensVenda(uuid, itens);
	}

	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Cerveja cerveja, Integer quantidade,
			String uuid) {
		List<ItemVenda> itens = vendaService.alterarQuantidade(uuid, cerveja, quantidade);
		return mvTabelaItensVenda(uuid, itens);
	}

	@DeleteMapping("/item/{uuid}/{codigoCerveja}")
	public ModelAndView excluirItem(@PathVariable String uuid, @PathVariable("codigoCerveja") Cerveja cerveja) {
		List<ItemVenda> itens = vendaService.excluirItem(uuid, cerveja);
		return mvTabelaItensVenda(uuid, itens);
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Venda venda = vendaService.buscarComItens(codigo);
		ModelAndView mv = nova(venda);
		mv.addObject(venda);
		return mv;
	}

	@PostMapping(value = "/nova", params = "cancelar")
	public ModelAndView cancelar(Venda venda, BindingResult result, RedirectAttributes attributes) {
		vendaService.cancelar(venda);

		attributes.addFlashAttribute("mensagem", "Venda cancelada com sucesso");
		ModelAndView mv = new ModelAndView("redirect:/vendas/" + venda.getCodigo());
		return mv;
	}
	
	@GetMapping("/totalPorMes")
	public @ResponseBody List<VendaMes> listarTotalVendaPorMes(){
		return vendaService.listarTotalVendaPorMes();
	}
	
	@GetMapping("/porOrigem")
	public @ResponseBody List<VendaOrigem> listarTotalVendaPorOrigem(){
		return vendaService.listarTotalVendaPorOrigem();
	}

	private void validarVenda(Venda venda, BindingResult result) {
		venda.adicionarItens(vendaService.getItens(venda.getUuid()));
		venda.calcularValorTotal();

		vendaValidator.validate(venda, result);
	}

	private ModelAndView mvTabelaItensVenda(String uuid, List<ItemVenda> itens) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", itens);
		mv.addObject("valorTotal", vendaService.obterValorTotal(uuid));
		return mv;
	}
}
