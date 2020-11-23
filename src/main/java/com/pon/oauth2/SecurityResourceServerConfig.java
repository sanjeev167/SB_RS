/**
 * 
 */
package com.pon.oauth2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Sanjeev Kumar
 * @Date   Dec 7, 2018
 * @Time   3:35:11 AM
 */

@Configuration
@EnableResourceServer 
public class SecurityResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	
    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/demo")
                .access("#oauth2.hasScope('read')");// require 'read' scope to access /demo URL
    }
    
    @Override    
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    	// TODO Auto-generated method stub
    	super.configure(resources);
    	resources.resourceId("");
    }
    
    
    
    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent){
        // write custom code here for login success audit
        System.out.println("\n\nRS = > gets => SUCCESS ACKNOWLEDGEMENT. ");
        System.out.println("\nAuthorization verified by AS for the user [ "+authorizedEvent.getAuthentication().getPrincipal()+" ] "
        		);
        
       
    }
    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent){
        // write custom code here login failed audit.
    	System.out.println("\n\nRS = > gets => FAILURE ACKNOWLEDGEMENT. ");
    	 System.out.println("\nAuthorization failed by AS for the user [ "+oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal()+" ]."
    	 		);
         
    	
    }
    
   /* @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }*/
}
