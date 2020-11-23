/**
 * 
 */
package com.pon.controller;
import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanjeev Kumar
 * @Date   Dec 7, 2018-
 * @Time   3:38:10 AM
 */

@RestController
public class SpringBootResourceController {
    @RequestMapping("/demo")
    public String demo(Principal principal) {
    	
    	/**
    	 * Case-1 : If the token is of simple nature or it has been stored in a jdbc. The call for the token verification always goes at AS from RS at every 
    	 * API call. It increases the response time.
    	 * 
    	 * case-2 : But in case of JWT implementation, RS contact AS at the time of its booting for accessing token_key.
    	 * This key accessing is carried out once and being stored in memory by RS for further verification of any token
    	 * Rs will ask for token_key when RS is restarted. 
    	 * Remark: AS must be started prior to RS
    	 * **/
    	
    	
        return "HELLO "+principal.getName()+".\n\n RS has checked your access_token and found you a valid user.";
    }
}