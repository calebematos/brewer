package br.com.calebematos.brewer.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.dto.CervejaDTO;
import br.com.calebematos.brewer.model.Cerveja;
import br.com.calebematos.brewer.repository.CervejaRepository;
import br.com.calebematos.brewer.repository.filter.CervejaFilter;
import br.com.calebematos.brewer.service.event.cerveja.CervejaSalvaEvent;
import br.com.calebematos.brewer.service.exception.ImpossivelEcluirEntidadeException;
import br.com.calebematos.brewer.storage.FotoStorage;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;

	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejaRepository.save(cerveja);

		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}

	public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {

		return cervejaRepository.filtrar(cervejaFilter, pageable);
	}

	public List<CervejaDTO> pesquisar(String skuOuNome) {
		return cervejaRepository.pesquisarPorNomeOuSku(skuOuNome);
	}

	@Transactional
	public void excluir(Cerveja cerveja) {
		try {
			String foto = cerveja.getFoto();
			cervejaRepository.delete(cerveja);
			cervejaRepository.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelEcluirEntidadeException("Impossível apagar cerveja. Já foi usada em alguma venda.");
		}
	}
}
