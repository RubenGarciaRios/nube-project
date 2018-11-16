/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.http;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.nube.core.base.CoreConfigurationObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public interface HTTPConfiguration
        extends CoreConfigurationObject {
    @Bean( "httpClient" )
    @ConditionalOnBean( SSLConnectionSocketFactory.class )
    @DependsOn( { "sslConnectionSocketFactory" } )
    HttpClient httpClient( SSLConnectionSocketFactory sslConnectionSocketFactory );

    @Bean( "clientHttpRequestFactory" )
    @ConditionalOnBean( HttpClient.class )
    @DependsOn( { "httpClient" } )
    ClientHttpRequestFactory clientHttpRequestFactory( HttpClient httpClient );

    @Primary
    @Bean( "restTemplate" )
    @DependsOn( { "clientHttpRequestFactory" } )
    @ConditionalOnBean( ClientHttpRequestFactory.class )
    RestTemplate restTemplate(
            ClientHttpRequestFactory clientHttpRequestFactory );
}
