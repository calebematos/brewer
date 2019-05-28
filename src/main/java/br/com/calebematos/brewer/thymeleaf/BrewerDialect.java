package br.com.calebematos.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.calebematos.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.calebematos.brewer.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.calebematos.brewer.thymeleaf.processor.MessageElementTagProcessor;
import br.com.calebematos.brewer.thymeleaf.processor.OrderElementTagProcessor;
import br.com.calebematos.brewer.thymeleaf.processor.PaginationElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect {

	public BrewerDialect() {
		super("Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		Set<IProcessor> processadores = new HashSet<>();

		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));

		return processadores;
	}

}
