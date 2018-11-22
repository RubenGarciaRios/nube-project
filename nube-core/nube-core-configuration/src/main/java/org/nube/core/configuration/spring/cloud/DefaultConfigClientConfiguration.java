/*
 *  Developed by Rubén García Ríos
 *  Last modified 22/11/18 14:37
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.configuration.http.DefaultHTTPConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Default Spring Cloud Config Client configuration.
 * @author Rubén García Ríos
 */
@ConditionalOnClass( { ConfigClientProperties.class, RestTemplate.class } )
@Import( DefaultHTTPConfiguration.class )
public class DefaultConfigClientConfiguration
        implements ConfigClientConfiguration {
    private static final Logger _LOG = LogManager.getLogger( DefaultConfigClientConfiguration.class );
    private static final long serialVersionUID = 6382632455937473506L;

    @Autowired
    public DefaultConfigClientConfiguration( ) {
        _LOG.info(
                "\n##########################################" +
                "\n### [SPRING CLOUD] CONFIG CLIENT SETUP ###" +
                "\n##########################################" );
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
     * Config Client properties bean.
     * Configure ConfigClientProperties with Spring Environment for
     * Config Client services configuration.
     *
     * <p>
     * [NOTE] Environment Bean injection.
     * @see Environment
     * </p>
     *
     * @param environment
     * @return {@link ConfigClientProperties}
     */
    @Override
    @Bean( "configClientProperties" )
    public ConfigClientProperties configClientProperties( Environment environment ) {
        _LOG.info( " - Generating ConfigClientProperties bean..." );
        ConfigClientProperties configClientProperties = new ConfigClientProperties( environment );
        _LOG.info( " - ConfigClientProperties bean has been created successfully: {}",
                configClientProperties );
        return configClientProperties;
    }

    /**
     * Configured Config service property source locator bean.
     * Provides two-way SSL authentication (x509) to clients that
     * connect with spring config server.
     *
     * <p>
     * [NOTE] RestTemplate Bean injection.
     * @see DefaultHTTPConfiguration#restTemplate(ClientHttpRequestFactory)
     * </p>
     *
     * @param restTemplate
     * @return {@link ConfigServicePropertySourceLocator}
     */
    @Override
    @Bean( "configServicePropertySourceLocator" )
    @DependsOn( { "restTemplate" } )
    @ConditionalOnBean( RestTemplate.class )
    public ConfigServicePropertySourceLocator configServicePropertySourceLocator(
            RestTemplate restTemplate, ConfigClientProperties configClientProperties ) {
        _LOG.info( " - Generating ConfigServicePropertySourceLocator bean..." );
        ConfigServicePropertySourceLocator configServicePropertySourceLocator =
                new ConfigServicePropertySourceLocator( configClientProperties );
        configServicePropertySourceLocator.setRestTemplate( restTemplate );
        _LOG.info( " - ConfigServicePropertySourceLocator bean has been created successfully: {}",
                configServicePropertySourceLocator );
        return configServicePropertySourceLocator;
    }
}
