package viv.hostel;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import viv.hostel.service.UserService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecureConfig {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests().antMatchers("/login", "/logout").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"api/depart/save").permitAll();
        http.authorizeHttpRequests().antMatchers("/api/depart/all").authenticated()
                .and()
                .formLogin().disable()
                .csrf().disable();
        return http.build();
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider()  {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}
