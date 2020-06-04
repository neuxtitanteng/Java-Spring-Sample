package com.neux.spring.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created with IntelliJ IDEA.
 * User: Titan
 * Date: 2019/4/14
 * Time: 下午 6:40
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        config.addAllowedOrigin("*");
        config.addAllowedOrigin("ionic://localhost");
        config.addAllowedOrigin("http://localhost");
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("http://localhost:9999");
        config.addAllowedOrigin("http://192.168.0.162:8082");

        config.addAllowedMethod("*");
        config.addAllowedHeader("*");


        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
