package br.com.calebematos.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calebematos.brewer.model.Estado;
import br.com.calebematos.brewer.repository.EstadoRepository;

@Service
public class ClienteService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> buscarTodosEstados(){
		return estadoRepository.findAll();
	}
}
