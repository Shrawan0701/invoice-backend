package in.shrawan.invoicegeneratorapi.config;

import in.shrawan.invoicegeneratorapi.security.ClerkJwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter; // Correct import
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter; // <--- NEW IMPORT
import org.springframework.http.HttpMethod; // Existing import

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Autowires ClerkJwtAuthFilter and CorsFilter
public class SecurityConfig {

    private final ClerkJwtAuthFilter jwtAuthFilter;
    private final CorsFilter corsFilter; // <--- NEW: Autowire the CorsFilter bean

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // Add CorsFilter at a very early stage in the filter chain
        http.addFilterBefore(corsFilter, BasicAuthenticationFilter.class) // <--- ADD THIS LINE
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/webhooks/**").permitAll()
                                // Permit OPTIONS requests globally before authentication
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // <--- Keep this line
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "https://quick-invoice-generator-seven.vercel.app" // Your Vercel frontend URL
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}