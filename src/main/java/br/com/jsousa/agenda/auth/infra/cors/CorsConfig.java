package br.com.jsousa.agenda.auth.infra.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CorsConfig.java
 * <p>
 * Descrição da classe: [adicione aqui uma breve descrição]
 *
 * @author jovan
 * @version 1.0
 * @since 9/21/2025
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

     @Value("${FRONT_URL}")
    private String FRONT_URL;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(FRONT_URL)
                .allowedMethods("GET", "POST", "OPTIONS");
    }
}
