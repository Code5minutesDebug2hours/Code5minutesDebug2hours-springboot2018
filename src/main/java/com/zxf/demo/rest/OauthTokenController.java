package com.zxf.demo.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxf
 */
@RestController
public class OauthTokenController {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "get")
    public Object get(Authentication authentication){
         //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         authentication.getCredentials();
         OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
         String token = details.getTokenValue();
         return token;
         }
}
