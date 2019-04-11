package br.com.calebematos.brewer.repository.paginacao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil {

	public void preparar(Pageable pageable, Criteria criteria) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

		criteria.setMaxResults(totalRegistrosPorPagina);
		criteria.setFirstResult(primeiroRegistro);

		Sort sort = pageable.getSort();
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String propriedade = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(propriedade) : Order.desc(propriedade));
		}
	}
}
