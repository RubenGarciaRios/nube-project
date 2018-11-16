/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.ssl;

import org.nube.core.configuration.http.HTTPConfiguration;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.nube.core.security.ssl.SimpleSSLContextBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Configure SSL Connections for all applications with the parametrized values.
 *
 * @author Rubén García Ríos
 */
@Configuration
@AutoConfigureAfter( HTTPConfiguration.class )
public class DefaultSSLConfiguration
        implements SSLConfiguration{
    private static final Logger _LOG = LogManager.getLogger( DefaultSSLConfiguration.class );
    private static final long serialVersionUID = -3170599873338821186L;

    @Override
    @PostConstruct
    public void initializer( ) {
        _LOG.info(
                "\n###############################" +
                "\n### SSL CONFIGURATION SETUP ###" +
                "\n###############################" );
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) { return serialVersionUID; }

    /**
     * Simple SSLContext Builder prepared with the configuration set in parent pom
     * and .yml files.
     *
     * @param environment
     * @return {@link SimpleSSLContextBuilder}
     */
    @Override
    @Bean( "sslContextBuilder" )
    @ConditionalOnMissingBean
    public SSLContextBuilder simpleSSLContextBuilder( Environment environment ) {
        _LOG.info( " - Generating SimpleSSLContextBuilder bean..." );
        SSLContextBuilder simpleSSLContextBuilder = new SimpleSSLContextBuilder( environment );
        _LOG.info( " - SimpleSSLContextBuilder bean has been created successfully: {}", simpleSSLContextBuilder );
        return simpleSSLContextBuilder;
    }

    /**
     * Custom SSLContext bean.
     *
     * <p>
     * [NOTE] Needed in all communications that uses SSL protocol.
     * Uses custom configuration instead default key store and trust store used by JVM
     * ($JAVA_HOME/jre/lib/security/cacerts).
     *
     * SSLContextBuilder bean injection.
     * @see DefaultSSLConfiguration#simpleSSLContextBuilder(Environment)
     * </p>
     *
     * @param sslContextBuilder
     * @return {@link SSLContext}
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     */
    @Override
    @Bean( "sslContext" )
    @ConditionalOnClass( SSLContextBuilder.class )
    @DependsOn( { "sslContextBuilder" } )
    public SSLContext sslContext( SSLContextBuilder sslContextBuilder )
            throws CertificateException,
            UnrecoverableKeyException,
            NoSuchAlgorithmException,
            KeyStoreException,
            KeyManagementException,
            IOException {
        _LOG.info( " - Generating SSLContext bean..." );
        SSLContext sslContext = sslContextBuilder.build( );
        _LOG.info( " - SSLContext bean has been created successfully: {}", sslContext );
        return sslContext;
    }

    /**
     * Ssl connection socket factory bean.
     *
     * <p>
     * [NOTE] SSLContext Bean injection.
     * @see DefaultSSLConfiguration#sslContext(SSLContextBuilder)
     * </p>
     *
     * @param sslContext the ssl context
     * @return the ssl connection socket factory
     */
    @Override
    @Bean( "sslConnectionSocketFactory" )
    @DependsOn( { "sslContext" } )
    public SSLConnectionSocketFactory sslConnectionSocketFactory( SSLContext sslContext ) {
        _LOG.info( " - Generating SSLConnectionSocketFactory bean..." );
        SSLConnectionSocketFactory sslConnectionSocketFactory =
                new SSLConnectionSocketFactory( sslContext, NoopHostnameVerifier.INSTANCE );
        _LOG.info( " - SSLConnectionSocketFactory bean has been created successfully: {}", sslConnectionSocketFactory );
        return sslConnectionSocketFactory;
    }
}
