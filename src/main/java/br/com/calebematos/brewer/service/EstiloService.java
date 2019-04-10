package br.com.calebematos.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.model.Estilo;
import br.com.calebematos.brewer.repository.EstiloRepository;
import br.com.calebematos.brewer.repository.filter.EstiloFilter;
import br.com.calebematos.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class EstiloService {

	@Autowired
	private EstiloRepository estiloRepository;

	@Transactional
	public Estilo salvar(Estilo estilo) {
		Optional<Estilo> nomeEstilo = estiloRepository.findByNomeIgnoreCase(estilo.getNome());
		if(nomeEstilo.isPresent()) {
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
		
		return estiloRepository.saveAndFlush(estilo);
	}
	
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable){
		return estiloRepository.filtrar(filtro, pageable);
	}

}
