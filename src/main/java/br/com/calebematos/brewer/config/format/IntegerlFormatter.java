package br.com.calebematos.brewer.config.format;

import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class IntegerlFormatter extends NumberFormatter<Integer> {

	@Override
	public String pattern(Locale locale) {
		return "#,##0";
	}

}
