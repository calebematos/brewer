package br.com.calebematos.brewer.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import br.com.calebematos.brewer.model.Estilo;

@Component
public class EstiloConverter implements Converter<String, Estilo> {

	@Override
	public Estilo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Estilo estilo = new Estilo();
			estilo.setCodigo(Long.valueOf(codigo));
			return estilo;
		}
		return null;
	}

}
