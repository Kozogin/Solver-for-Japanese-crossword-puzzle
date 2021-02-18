/**
 * Japanese crossword puzzle solver
 * My own project
 *
 * Class crossword.yapona10.config.WebSecurityConfig  - configuration layer
 *
 * @author Vasil Kozogin
 *
 */

package crossword.yapona10.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.authorizeRequests()
		.antMatchers("/" , "/enter_data").permitAll()			
		.antMatchers("/user", "apl_success").hasAnyRole("ADMIN", "USER")
		.antMatchers(
				"/admin", "/create_faculty", "/faculties", "/create_lesson", "/lessons",
				"/add_lesson_to_faculty", "/application_of_entrants", "/singleApplicant",
				"/selection_options"
				)
		
		.hasRole("ADMIN").anyRequest().permitAll()
		
		.and()
		
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/user")
		.usernameParameter("assignedId")
		.passwordParameter("password")
		
		.and()
		
		.logout().logoutSuccessUrl("/login?logout").and()
		.exceptionHandling().accessDeniedPage("/403").and()
		.csrf();
		
	}
	

	
}
