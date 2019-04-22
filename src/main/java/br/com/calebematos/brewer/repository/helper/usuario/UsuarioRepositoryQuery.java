package br.com.calebematos.brewer.repository.helper.usuario;

import java.util.Optional;

import br.com.calebematos.brewer.model.Usuario;

public interface UsuarioRepositoryQuery {
	
	public Optional<Usuario> porEmailEAtivo(String email);
	
}
