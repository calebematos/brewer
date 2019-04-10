package br.com.calebematos.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.calebematos.brewer.controller.page.PageWrapper;
import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.model.Origem;
import br.com.calebematos.brewer.model.Sabor;
import br.com.calebematos.brewer.repository.EstiloRepository;
import br.com.calebematos.brewer.repository.filter.CervejaFilter;
import br.com.calebematos.brewer.service.CervejaService;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {

	@Autowired
	private EstiloRepository estiloRepository;

	@Autowired
	private CervejaService cervejaService;

	@GetMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estiloRepository.findAll());
		mv.addObject("origens", Origem.values());

		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cerveja);
		}

		cervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");

		return new ModelAndView("redirect:/cervejas/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estiloRepository.findAll());
		mv.addObject("origens", Origem.values());
		PageWrapper<Cerveja> pagina = new PageWrapper<>(cervejaService.filtrar(cervejaFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pagina);
		return mv;
	}

}
