package c15.dev.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author Leopoldo Todisco, Carlo Venditto.
 * Creato il: 24/01/2023.
 * Classe di configurazione, viene richiamata da Spring automaticamente.
 */
@Configuration
@EnableWebSecurity
package c15.dev.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @RequiredArgsConstructor
    public static class SecurityFilterChainConfig {
        private final JwtAuthenticationFilter authFilter;
        private final AuthenticationProvider provider;

        @Bean
        public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
            http.cors().configurationSource(corsConfigurationSource()).and()
                    .csrf()
                    .disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/auth/login", "/auth/registrazione",
                            "/auth/getByEmail",
                            "/auth/getByCodice", "/ws/**",
                            "/comunicazione/invioNota",
                            "/comunicazione/invioNotifica")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(provider)
                    .addFilterBefore(authFilter,
                            UsernamePasswordAuthenticationFilter.class);
            http.headers().frameOptions().disable();
            http.headers().contentTypeOptions().disable();
            return http.build();
        }

        private CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }
}
        configuration.addExposedHeader("Authorization");
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration
                .setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}