package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(req -> {
                            req.requestMatchers("/css/**", "/js/**", "/assets/**", "/", "/index", "/home").permitAll();
                            req.anyRequest().authenticated();})
                    .formLogin(form -> form.loginPage("/login")
                            .defaultSuccessUrl("/")
                            .permitAll())
                    .logout(logout -> logout.logoutSuccessUrl("/login?logout")
                            .permitAll())
                    .rememberMe(rememberMe -> rememberMe.key("renanELegal")
                            .alwaysRemember(true))
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
