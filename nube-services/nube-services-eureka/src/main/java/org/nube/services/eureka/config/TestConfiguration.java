/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.eureka.config;

import com.netflix.discovery.DiscoveryClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.nube.core.security.ssl.SimpleSSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
@Configuration
public class TestConfiguration {
    private static final Logger _LOG = LogManager.getLogger( TestConfiguration.class );

    @Value( "${server.ssl.keystore}" )
    private String keystorePath;

    @Bean
    public HashMap< String, String > test1(
            SSLContextBuilder sslContextBuilder,
            DiscoveryClient.DiscoveryClientOptionalArgs args, Environment environment ) {
        _LOG.info( "\n\n\n\n@Value(keystorePath): {}\n\n\n\n", keystorePath );
        _LOG.info( "\n\n\n\nEnvironment {}: {}\n\n\n\n",
                   SimpleSSLContextBuilder.EmbeddedServletContainerSSLProperties.KEY_ALIAS_PROPERTY,
                   environment.getProperty( SimpleSSLContextBuilder.EmbeddedServletContainerSSLProperties.KEY_ALIAS_PROPERTY ) );
        _LOG.info( "\n\n\n\nsslContextBuilder: {}\n\n\n\n", sslContextBuilder );
        _LOG.info( "\n\n\n\nargs.getHostnameVerifier(): {}\n\n\n\n", args.getHostnameVerifier() );
        return new HashMap< >( );
    }
}
