/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.http;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.configuration.ssl.DefaultSSLConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Import( DefaultSSLConfiguration.class )
public class DefaultHTTPConfiguration
        implements HTTPConfiguration {
    private static final Logger _LOG = LogManager.getLogger( DefaultSSLConfiguration.class );
    private static final long serialVersionUID = -2549689124050903785L;

    @Autowired
    public DefaultHTTPConfiguration( ) {
        _LOG.info(
                "\n################################" +
                "\n### HTTP CONFIGURATION SETUP ###" +
                "\n################################" );
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    /**
     * Http client bean with SSL Configuration.
     *
     * <p>
     * [NOTE] SSLConnectionSocketFactory Bean injection.
     * @see DefaultSSLConfiguration#sslConnectionSocketFactory(SSLContext)
     * </p>
     *
     * @param sslConnectionSocketFactory
     * @return {@link HttpClient}
     */
    @Override
    @Bean( "httpClient" )
    @ConditionalOnBean( SSLConnectionSocketFactory.class )
    @DependsOn( { "sslConnectionSocketFactory" } )
    public HttpClient httpClient( SSLConnectionSocketFactory sslConnectionSocketFactory ) {
        _LOG.info( " - Generating HttpClient bean..." );
        HttpClient httpClient = HttpClients
                .custom( )
                .setSSLSocketFactory( sslConnectionSocketFactory )
                .build( );
        _LOG.info( " - HttpClient bean has been created successfully: {}", httpClient );
        return httpClient;
    }

    /**
     * Client http request factory bean with custom {@link HttpClient}.
     *
     * <p>
     * [NOTE] HttpClient Bean injection.
     * @see DefaultHTTPConfiguration#httpClient(SSLConnectionSocketFactory)
     * </p>
     *
     * @param httpClient
     * @return {@link ClientHttpRequestFactory}
     */
    @Override
    @Bean( "clientHttpRequestFactory" )
    @ConditionalOnBean( HttpClient.class )
    @DependsOn( { "httpClient" } )
    public ClientHttpRequestFactory clientHttpRequestFactory( HttpClient httpClient ) {
        _LOG.info( " - Generating HttpComponentsClientHttpRequestFactory bean..." );
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory( httpClient );
        _LOG.info( " - HttpComponentsClientHttpRequestFactory bean has been created successfully: {}",
                httpComponentsClientHttpRequestFactory );
        return httpComponentsClientHttpRequestFactory;
    }

    /**
     * Configured rest template bean for https connections.
     * To force the client-side application to use two-way SSL authentication (x509) with custom self-signed
     * certificates instead of a standard, nonsecure HTTP connection.
     *
     * <p>
     * [NOTE] HttpClient Bean injection.
     * @see DefaultHTTPConfiguration#restTemplate(ClientHttpRequestFactory)
     * </p>
     *
     * @param clientHttpRequestFactory
     * @return {@link RestTemplate}
     */
    @Override
    @Primary
    @Bean( "restTemplate" )
    @DependsOn( { "clientHttpRequestFactory" } )
    @ConditionalOnBean( ClientHttpRequestFactory.class )
    public RestTemplate restTemplate(
            ClientHttpRequestFactory clientHttpRequestFactory ) {
        _LOG.info( " - Generating RestTemplate bean..." );
        RestTemplate restTemplate = new RestTemplate( clientHttpRequestFactory );
        _LOG.info( " - RestTemplate bean has been created successfully: {}", restTemplate );
        return restTemplate;
    }

    @Configuration
    @ConditionalOnBean( ClientHttpRequestFactory.class )
    @ConditionalOnClass( LoadBalancerClient.class )
    protected static class RibbonRestTemplateConfiguration {
        /**
         * Load balanced configured rest template bean (Uses Ribbon).
         *
         * <p>
         * [NOTE] RestTemplate Bean injection.
         * @see DefaultHTTPConfiguration#restTemplate(ClientHttpRequestFactory)
         * </p>
         *
         * @param clientHttpRequestFactory
         * @return Load balanced {@link RestTemplate}
         */
        @LoadBalanced
        @Bean( "loadBalancedRestTemplate" )
        @DependsOn( { "clientHttpRequestFactory" } )
        public RestTemplate loadBalancedRestTemplate(
                ClientHttpRequestFactory clientHttpRequestFactory ) {
            _LOG.info( " - Generating RestTemplate bean..." );
            RestTemplate restTemplate = new RestTemplate( clientHttpRequestFactory );
            _LOG.info( " - RestTemplate bean has been created successfully: {}", restTemplate );
            return restTemplate;
        }
    }
}
