package com.jrv.springbootandjavaconcepts.security;

import com.jrv.springbootandjavaconcepts.security.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

/**
 *
 * @deprecated Using this Deprecated method for learning purpose. Should need to go for a better one after
 * completion of the course.
 *
 */
@Configuration
@EnableWebSecurity
//To Tell the Spring Security to use Annotation based Authentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Deprecated(since = "10/Oct/22", forRemoval = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService){
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*", "/public/**").permitAll()
                //.antMatchers("/students/**").hasRole(STUDENT.name())    //Role Based Authentication
                /* Commented these lines to use Annotation (PreAuthorise()) based Authentication instead of Authority Based
                .antMatchers(HttpMethod.DELETE, "/management/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/management/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())*/
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/login-success")
                .and()
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(28)).key("Secured")
                .and()
                .logout().logoutUrl("/logoff")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logoff", "GET"))
                .clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
