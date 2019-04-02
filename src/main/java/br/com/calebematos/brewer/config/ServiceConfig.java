package br.com.calebematos.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.calebematos.brewer.service.CervejaService;
import br.com.calebematos.brewer.storage.FotoStorage;
import br.com.calebematos.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = { CervejaService.class })
public class ServiceConfig {

	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
}
