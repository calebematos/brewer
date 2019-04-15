package br.com.calebematos.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.calebematos.brewer.model.Cliente;
import br.com.calebematos.brewer.model.TipoPessoa;
import br.com.calebematos.brewer.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", clienteService.buscarTodosEstados());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		if(result.hasErrors()) {
			return novo(cliente);
		}
		
		return new ModelAndView("redirect:/clientes/novo");
		
	}
}
