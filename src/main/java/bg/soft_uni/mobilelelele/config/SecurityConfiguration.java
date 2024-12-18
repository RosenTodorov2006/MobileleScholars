package bg.soft_uni.mobilelelele.config;

import bg.soft_uni.mobilelelele.repositories.UserRepository;
import bg.soft_uni.mobilelelele.services.impl.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                               authorizeRequests
                                       .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                       .requestMatchers("/", "/users/login", "/users/register","/error").permitAll()
                                       .anyRequest()
                                       .authenticated()
                )
                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/users/login")
                                        .usernameParameter("email")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/", true)
                )
                .logout(
                        logout->
                                logout.logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                ).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    @Bean
    public UserDetailsServiceImpl userDetailsService(UserRepository userRepository){
        return new UserDetailsServiceImpl(userRepository);
    }
}
