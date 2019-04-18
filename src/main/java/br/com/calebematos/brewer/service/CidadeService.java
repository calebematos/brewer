package br.com.calebematos.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.model.Cidade;
import br.com.calebematos.brewer.repository.CidadeRepository;
import br.com.calebematos.brewer.repository.filter.CidadeFilter;
import br.com.calebematos.brewer.service.exception.CidadeJaCadastradaException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional
	public void salvar(Cidade cidade) {
		
		Optional<Cidade> cidadeExistente = cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if(cidadeExistente.isPresent()) {
			throw new CidadeJaCadastradaException("Cidade já cadastrada");
		}
		
		cidadeRepository.save(cidade);
	}

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable) {
		return cidadeRepository.filtrar(filtro, pageable);
	}

}
