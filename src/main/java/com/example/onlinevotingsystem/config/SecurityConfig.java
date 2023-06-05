package com.example.onlinevotingsystem.config;


import com.example.onlinevotingsystem.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final  JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /**

        httpSecurity
                .authorizeHttpRequests((authorize) ->   authorize
                        .requestMatchers("/auth/authenticate","/elections/results").permitAll()
                        .anyRequest().authenticated())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/elections/deneme").hasAuthority(Role.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);;


        return httpSecurity.build();
    **/

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**","/elections/deneme")
                .permitAll()
                .requestMatchers("/elections/results","/notifications/**")
                .hasAuthority(Role.STUDENT.name())
                .requestMatchers("/department/**",
                        "/faculty/**",
                        "/elections/**",
                        "/notifications/**",
                        "/candidates/**",
                        "/voting/applicants/**",
                        "/applications/**"
                )
                .hasAuthority(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

}