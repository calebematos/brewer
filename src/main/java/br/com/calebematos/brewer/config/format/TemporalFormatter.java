package br.com.calebematos.brewer.config.format;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;

import org.springframework.format.Formatter;

public abstract class TemporalFormatter<T extends Temporal> implements Formatter<T> {

	@Override
	public String print(T object, Locale locale) {
		DateTimeFormatter formatter = getDateFormatter(locale);
		return formatter.format(object);
	}

	@Override
	public T parse(String text, Locale locale) throws ParseException {
		DateTimeFormatter formatter = getDateFormatter(locale);
		return parse(text, formatter);
	}

	private DateTimeFormatter getDateFormatter(Locale locale) {
		return DateTimeFormatter.ofPattern(pattern(locale));
	}
	
	public abstract String pattern(Locale locale);

	public abstract T parse(String text, DateTimeFormatter formatter);


}
