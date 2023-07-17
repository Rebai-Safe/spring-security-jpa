package com.springsecurity.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * there is authentication object that holds credentials then holds authentcated user when
 * it authenticates
 * authentication provider is responsibe of doing authentication it has
 * a method called authenticate()
 *
 *In the UserDetailsService you get only the username and when you return the custom UserDetails,
 * the framework performs a check on the password.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;
    /**
     * every time there is authentication attempt sp security will
     * call this method and get userdetailobject then spring security
     * will compare this credentials with object that retrieved
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin") //specify the path
                .hasRole("ADMIN")
                .antMatchers("/user") //specify the path
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/")
                .permitAll() //permit all request for path /
                //someone who has user role
                .and().formLogin();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
