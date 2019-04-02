package br.com.calebematos.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.repository.CervejaRepository;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejaRepository.save(cerveja);
	}
}
