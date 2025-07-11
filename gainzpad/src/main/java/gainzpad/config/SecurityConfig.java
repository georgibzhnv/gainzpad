package gainzpad.config;

import gainzpad.model.users.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl gainzpadUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsServiceImpl gainzpadUserDetailsService, PasswordEncoder passwordEncoder) {
        this.gainzpadUserDetailsService = gainzpadUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(gainzpadUserDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/img/**", "/css/**", "/js/**", "/webjars/**").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/login**", "/register**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form->
                        form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .loginProcessingUrl("/login/authenticate")
                                .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/home",true)
                )
                .logout(logout->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID"));
        return http.build();
    }
}
