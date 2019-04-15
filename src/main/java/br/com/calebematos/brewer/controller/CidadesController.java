package br.com.calebematos.brewer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.calebematos.brewer.model.Cidade;
import br.com.calebematos.brewer.repository.CidadeRepository;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/novo")
	public String novo() {
		return "cidade/CadastroCidade";
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cidade> pesquisarProCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		return cidadeRepository.findByEstadoCodigo(codigoEstado);
	}
}
