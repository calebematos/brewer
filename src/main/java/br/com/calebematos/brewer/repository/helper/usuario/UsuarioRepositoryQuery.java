package br.com.calebematos.brewer.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.calebematos.brewer.model.Usuario;
import br.com.calebematos.brewer.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {
	
	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);

	public Usuario buscarComGrupos(Long codigo);
}
