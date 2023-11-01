package ecommerce.com.pswproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private UserDetailsServiceImp uImp;

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(uImp);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(
            authorize ->    
                            
                            authorize.requestMatchers("/image/**").permitAll()
                            .requestMatchers("/style.css").permitAll()
                            .requestMatchers("/carrelloAPI/**").hasAuthority("GUEST")
                            .requestMatchers("/ordineAPI/**").hasAuthority("GUEST")    
                            .requestMatchers("/customerAPI/**").permitAll()
                            .requestMatchers("/homepage/**").permitAll()
                            .anyRequest().authenticated()

            ).formLogin(
                form ->  form.successHandler(new CustomRedirect()).permitAll()
            ).logout(
                logout -> logout.logoutUrl("/carrelloAPI/logout").logoutSuccessUrl("/homepage").permitAll()
            ).build();
    }
}
