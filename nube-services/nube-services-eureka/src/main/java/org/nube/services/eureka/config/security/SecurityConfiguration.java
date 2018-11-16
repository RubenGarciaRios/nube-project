/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 13:48
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.eureka.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order( 1 )
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value( "${nube.service.security.username}" )
    private String username;
    @Value( "${nube.service.security.password}" )
    private String password;
    @Value( "${nube.service.security.roles}" )
    protected String[] roles;

    /**
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
                .withUser( this.username )
                .password( "{noop}" + this.password ) /* {noop} for plain text password (No password encoding). */
                .roles( this.roles[ 0 ] );
    }

    @Override
    protected void configure( HttpSecurity http )
            throws Exception {
        http.sessionManagement( )
                .sessionCreationPolicy( SessionCreationPolicy.ALWAYS )
                .and( ).requestMatchers( ).antMatchers("/eureka/**")
                .and( ).authorizeRequests( ).antMatchers("/eureka/**")
                .hasRole( this.roles[ 0 ] ).anyRequest( ).denyAll( )
                .and( ).httpBasic( )
                .and( ).csrf( ).disable( );
    }

    @Configuration
    // no order tag means this is the last security filter to be evaluated
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Value( "${nube.service.security.roles}" )
        protected String[] roles;

        @Autowired
        public void configureGlobal( AuthenticationManagerBuilder auth )
                throws Exception {
            auth.inMemoryAuthentication( );
        }

        @Override
        protected void configure( HttpSecurity http )
                throws Exception {
            /*
            http.sessionManagement( )
                    .sessionCreationPolicy( SessionCreationPolicy.NEVER ).and( )
                    .httpBasic( ).disable( ).authorizeRequests( )
                    .antMatchers( HttpMethod.GET, "/" ).hasRole( this.roles[ 1 ] )
                        .antMatchers( "/info", "/health" ).authenticated( ).anyRequest( ).denyAll( )
                    .and( ).csrf( ).disable( );
            */
            http.sessionManagement( )
                    .sessionCreationPolicy( SessionCreationPolicy.NEVER ).and( )
                    .httpBasic( ).disable( ).authorizeRequests( )
                    .antMatchers( HttpMethod.GET, "/" ).permitAll( )
                    .antMatchers( "/info", "/health" ).permitAll( )
                    .and( ).csrf( ).disable( );
        }
    }
}
