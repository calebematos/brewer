package br.com.calebematos.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calebematos.brewer.model.Cliente;
import br.com.calebematos.brewer.repository.ClienteRepository;
import br.com.calebematos.brewer.repository.filter.ClienteFilter;
import br.com.calebematos.brewer.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		Optional<Cliente> clienteExistente = clienteRepository.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		
		if(clienteExistente.isPresent()) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		
		
		return clienteRepository.save(cliente);
	}

	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		return clienteRepository.filtrar(clienteFilter, pageable);
	}

	public List<Cliente> pesquisarPorNome(String nome) {
		return clienteRepository.findByNomeStartingWithIgnoreCase(nome);
	}
}
