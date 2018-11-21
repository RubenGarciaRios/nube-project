/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import com.netflix.discovery.DiscoveryClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.configuration.ssl.DefaultSSLConfiguration;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.net.ssl.SSLContext;

@Import( DefaultSSLConfiguration.class )
public class DefaultDiscoveryClientConfiguration
        implements DiscoveryClientConfiguration {
    private static final Logger _LOG = LogManager.getLogger( DefaultConfigClientConfiguration.class );
    private static final long serialVersionUID = -4402558066248108982L;

    @Autowired
    public void DefaultDiscoveryClientConfiguration( ) {
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