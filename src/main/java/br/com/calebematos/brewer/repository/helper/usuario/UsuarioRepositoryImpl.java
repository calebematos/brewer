package br.com.calebematos.brewer.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.calebematos.brewer.model.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {

		return manager.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class).setParameter("email", email)
				.getResultList().stream().findFirst();
	}

}
