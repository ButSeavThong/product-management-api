package kh.coding.product.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Do this we will allow to use method @PreAuthorization() on any controller method easier manage request
public class SecurityConfig {

    // firewall web security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        // make it have security with jwt and we need to allow resource server can understand it by authorization server decoder oauth2
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(
                Customizer.withDefaults()
        ));


        // make is statless
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        // disable csrf ( prevent fobiden(403) when request )
        // http.csrf(jwt->jwt.disable());
        http.csrf(AbstractHttpConfigurer::disable);



        return http.build();
    }
}
