package ejerciciopractico2_josuegarcias.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Para que funcione directamente con el script proporcionado (passwords en texto plano)
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/registro", "/usuario/registro", "/usuario/registrar", "/css/**").permitAll()
                .requestMatchers("/usuario/**", "/rol/**").hasRole("ADMIN")
                .requestMatchers("/evento/**").hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/consulta/**").hasAnyRole("ADMIN", "ORGANIZADOR", "CLIENTE")
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler())
                .permitAll()
        );

        http.logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll());
        http.exceptionHandling(ex -> ex.accessDeniedPage("/acceso_denegado"));
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
