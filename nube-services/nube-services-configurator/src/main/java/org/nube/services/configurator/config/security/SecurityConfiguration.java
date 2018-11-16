/*
 *  Developed by Rubén García Ríos
 *  Last modified 7/11/18 19:05
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.configurator.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration for Configuration Server.
 *
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 */
@Configuration
@EnableWebSecurity
@Order( 1 )
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value( "${nube.service.security.username}" )
    private String username;
    @Value( "${nube.service.security.password}" )
    private String password;
    @Value( "${nube.service.security.roles}" )
    private String roles;

    /**s
     * Configure global.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth )
            throws Exception {
        //In memory authentication based on configuration files.
        auth.inMemoryAuthentication( )
                .withUser( username )
                .password( "{noop}" + password ) /* {noop} for plain text password (No password encoding). */
                .roles( roles );
    }

    @Override
    protected void configure( HttpSecurity http )
            throws Exception {
        http.authorizeRequests( ).anyRequest( )
                .hasRole( roles )
                .and( ).httpBasic( ).and( ).csrf( ).disable( );
    }
}