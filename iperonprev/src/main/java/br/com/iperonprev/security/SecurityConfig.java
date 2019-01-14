package br.com.iperonprev.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.iperonprev.dao.UsuarioDao;

@EnableWebSecurity  
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired  
/*    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {  
        auth.inMemoryAuthentication().withUser("saulo").password("123456").roles("COMUM");
    }  
    
*/	
	private UsuarioDao usuario = new UsuarioDao();
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuario)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	@Override  
    protected void configure(HttpSecurity http) throws Exception {  
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        
  
        http.exceptionHandling().and().authorizeRequests()
        		.and().exceptionHandling().accessDeniedPage("/acessoNegado.xhtml").and().authorizeRequests()  
                .antMatchers("/javax.faces.resource/**").permitAll()  
                .and().authorizeRequests()  
//                .antMatchers("/cadastro-usuarios.jsf", "/cadastro-empresas.jsf").hasRole("ADMINISTRADOR")  
                .anyRequest().authenticated()  
                .and().formLogin().loginPage("/login.xhtml").usernameParameter("username").passwordParameter("password")
                .permitAll() //.loginPage("/login.jsf") Para utilizar a própria página de login 
                .and().logout().logoutSuccessUrl("/login.xhtml?logout").permitAll();
    }  
  
    @Override  
    public void configure(WebSecurity web) throws Exception {  
        web.ignoring().antMatchers("/resources/**");  
    } 
	

}
