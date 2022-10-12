package com.jrv.springbootandjavaconcepts.spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.jrv.springbootandjavaconcepts.spring_security.ApplicationUserPermission.STUDENT_WRITE;
import static com.jrv.springbootandjavaconcepts.spring_security.ApplicationUserRole.*;

/**
 *
 * @deprecated Using this Deprecated method for learning purpose. Should need to go for a better one after
 * completion of the course.
 *
 */
@Configuration
@EnableWebSecurity
@Deprecated(since = "10/Oct/22", forRemoval = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/students/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/management/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/management/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userJon = User.builder()
                .username("Jon")
                .password(passwordEncoder.encode("qwerty"))
//                .roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(STUDENT.grantedAuthorities())
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("qwerty"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.grantedAuthorities())
                .build();

        UserDetails adminTraineeUser = User.builder()
                .username("Tom")
                .password(passwordEncoder.encode("qwerty"))
//                .roles(ADMIN_TRAINEE.name())
                .authorities(ADMIN_TRAINEE.grantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(userJon, adminUser, adminTraineeUser);
    }
}
