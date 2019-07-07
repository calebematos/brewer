package br.com.calebematos.brewer.repository.helper.venda;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.calebematos.brewer.model.TipoPessoa;
import br.com.calebematos.brewer.model.Venda;
import br.com.calebematos.brewer.repository.filter.VendaFilter;
import br.com.calebematos.brewer.repository.paginacao.PaginacaoUtil;

public class VendaRepositoryImpl implements VendaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);

		paginacaoUtil.preparar(pageable, criteria);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(VendaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(VendaFilter filtro, Criteria criteria) {
		criteria.createAlias("cliente", "c");

		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			if (filtro.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filtro.getStatus()));
			}
			if (filtro.getCriacaoDe() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getCriacaoDe(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("dataCriacao", desde));
			}
			if (filtro.getCriacaoAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getCriacaoAte(), LocalTime.of(0, 0));
				criteria.add(Restrictions.le("dataCriacao", ate));
			}
			if(filtro.getValorMinimo() != null) {
				criteria.add(Restrictions.ge("valorTotal", filtro.getValorMinimo()));
			}
			if(filtro.getValorMaximo() != null) {
				criteria.add(Restrictions.le("valorTotal", filtro.getValorMaximo()));
			}
			if(!StringUtils.isEmpty(filtro.getNomeCliente())) {
				criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(filtro.getCpfOuCnpjCliente())) {
				criteria.add(Restrictions.ilike("c.cpfOuCnpj", TipoPessoa.removerFormatacao(filtro.getCpfOuCnpjCliente())));
			}
		}
	}

}
