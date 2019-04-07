package br.com.calebematos.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.repository.CervejaRepository;
import br.com.calebematos.brewer.service.event.cerveja.CervejaSalvaEvent;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejaRepository.save(cerveja);
		
		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}
  
	public List<Cerveja> pesquisar() {
		
		return cervejaRepository.findAll();
	}
}
