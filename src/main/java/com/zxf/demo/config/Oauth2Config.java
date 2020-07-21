package com.zxf.demo.config;

import com.zxf.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @author zxf
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl kiteUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore redisTokenStore;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //第一行代码是允许客户端访问 OAuth2 授权接口，否则请求 token 会返回 401。
        security.allowFormAuthenticationForClients();
        //第二行和第三行分别是允许已授权用户访问 checkToken 接口和获取 token 接口。
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("order-client")
//                .secret(passwordEncoder.encode("order-secret-8888"))
//                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                .accessTokenValiditySeconds(3600)
//                .scopes("all")
//                .and()
//                .withClient("user-client")
//                .secret(passwordEncoder.encode("user-secret-8888"))
//                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                .accessTokenValiditySeconds(3600)
//                .scopes("all");
        JdbcClientDetailsServiceBuilder jcsb = clients.jdbc(dataSource);
        jcsb.passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**         * redis token 方式         */
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(kiteUserDetailsService)
                .tokenStore(redisTokenStore);


    }
}
