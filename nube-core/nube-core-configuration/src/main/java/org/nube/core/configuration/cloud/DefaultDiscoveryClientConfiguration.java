/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.cloud;

import com.netflix.discovery.DiscoveryClient;
import org.nube.core.configuration.ssl.DefaultSSLConfiguration;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

@Configuration
@ConditionalOnClass( DiscoveryClient.class )
@Import( DefaultSSLConfiguration.class )
@EnableDiscoveryClient
@EnableEurekaClient
public class DefaultDiscoveryClientConfiguration
        implements DiscoveryClientConfiguration {
    private static final Logger _LOG = LogManager.getLogger( DefaultConfigClientConfiguration.class );
    private static final long serialVersionUID = -4402558066248108982L;

    @Override
    @PostConstruct
    public void initializer( ) {
        _LOG.info(
                "\n#############################################" +
                "\n### [SPRING CLOUD] DISCOVERY CLIENT SETUP ###" +
                "\n#############################################" );
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
     * Set SSL context to discovery client optional arguments bean.
     * Provides two-way SSL authentication (x509) to clients thats connects
     * with other core services.
     *
     * <p>
     * [NOTE] SSLContext Bean injection.
     * @see DefaultSSLConfiguration#sslContext(SSLContextBuilder)
     * </p>
     *
     * @param sslContext
     * @return {@link DiscoveryClient.DiscoveryClientOptionalArgs}
     */
    @Override
    @Bean( "discoveryClientOptionalArgs" )
    @DependsOn( { "sslContext" } )
    @ConditionalOnBean( SSLContext.class )
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs(
            SSLContext sslContext ) {
        _LOG.info( " - Generating DiscoveryClient.DiscoveryClientOptionalArgs bean..." );
        DiscoveryClient.DiscoveryClientOptionalArgs args =
                new DiscoveryClient.DiscoveryClientOptionalArgs( );
        _LOG.info( " - Setting SSLContext: {}", sslContext );
        args.setSSLContext( sslContext );
        _LOG.info( " - Setting HostnameVerifier: {}", NoopHostnameVerifier.INSTANCE );
        args.setHostnameVerifier( NoopHostnameVerifier.INSTANCE );
        _LOG.info( " - DiscoveryClient.DiscoveryClientOptionalArgs bean has been created successfully: {}",
                args );
        return args;
    }
}