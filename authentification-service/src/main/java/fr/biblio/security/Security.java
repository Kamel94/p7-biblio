package fr.biblio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as principal, password as credentials, actif from utilisateur where email=?")
                .authoritiesByUsernameQuery("select email as principal, statut as role from utilisateur where email=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(getBC());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .formLogin()
                //.loginPage("/login")
                .defaultSuccessUrl("/app-web/accueil")
                .failureUrl("/login?error=true").permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login");

        http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/personnel/*").hasAnyRole("ADMIN", "PERSONNEL");
        http.authorizeRequests().antMatchers("/usager/*").hasAnyRole("ADMIN", "PERSONNEL", "USAGER");
    }

    @Bean
    BCryptPasswordEncoder getBC() {
        return new BCryptPasswordEncoder();
    }
}
