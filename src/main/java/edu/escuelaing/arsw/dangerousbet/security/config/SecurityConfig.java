package edu.escuelaing.arsw.dangerousbet.security.config;


import edu.escuelaing.arsw.dangerousbet.service.UserService;
import edu.escuelaing.arsw.dangerousbet.util.EncrytedPasswodUtils;
import edu.escuelaing.arsw.dangerousbet.util.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     @Autowired
     UserDetailsServiceImpl us;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(us).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("user").password(EncrytedPasswodUtils.encryted("user")).roles("user");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/logout","/registro","/menu").permitAll();
        http.authorizeRequests().antMatchers("/bienvenido").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/user").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/login").anonymous();
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        http.sessionManagement().maximumSessions(1);
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/login_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/bienvenido")//
                .failureUrl("/login?error=true")//
                .usernameParameter("nickname")//
                .passwordParameter("contrasena")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

    }
}
