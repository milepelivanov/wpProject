package mk.ukim.finki.dosie.config;

import mk.ukim.finki.dosie.security.UsernameAndPasswordAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UsernameAndPasswordAuthProvider usernameAndPasswordAuthProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, UsernameAndPasswordAuthProvider usernameAndPasswordAuthProvider) {
        this.passwordEncoder = passwordEncoder;
        this.usernameAndPasswordAuthProvider = usernameAndPasswordAuthProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/reports","/showReports").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll();
        return http.build();


    }
}
