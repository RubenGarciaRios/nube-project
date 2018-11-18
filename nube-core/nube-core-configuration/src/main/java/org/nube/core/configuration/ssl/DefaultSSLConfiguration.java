/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.ssl;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.property.NubeSSLProperties;
import org.nube.core.configuration.http.HTTPConfiguration;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.nube.core.security.ssl.SimpleSSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

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
@PropertySources( {
    @PropertySource( "classpath:nube-core.properties" ) } )
@EnableConfigurationProperties( NubeSSLProperties.class )
@AutoConfigureAfter( HTTPConfiguration.class )
public class DefaultSSLConfiguration
        implements SSLConfiguration{
    private static final Logger _LOG = LogManager.getLogger( DefaultSSLConfiguration.class );
    private static final long serialVersionUID = -3170599873338821186L;

    @Autowired
    public DefaultSSLConfiguration( NubeSSLProperties nubeSSLProperties ) {
        _LOG.info(
                "\n###############################" +
                "\n### SSL CONFIGURATION SETUP ###" +
                "\n###############################" );
        _LOG.info( " - Nube SSL properties object: {}", nubeSSLProperties );
        _LOG.info( " - Setting Embbeded Server SSL properties..." );
        /*
        System.setProperty( "server.ssl.enabled", "true" );
        System.setProperty( "server.ssl.client-auth", nubeSSLProperties.getClientAuth( ) );
        System.setProperty( "server.ssl.key-alias", nubeSSLProperties.getKeyStore( ).getAlias( ) );
        System.setProperty( "server.ssl.key-alias", nubeSSLProperties.getKeyStore( ).getAlias( ) );
        System.setProperty( "server.ssl.key-password", nubeSSLProperties.getKeyStore( ).getPassword( ) );
        System.setProperty( "server.ssl.key-store-password", nubeSSLProperties.getKeyStore( ).getStorePassword( ) );



        server.ssl.key-store= # Path to the key store that holds the SSL certificate (typically a jks file).
        server.ssl.key-store-password= # Password used to access the key store.
        server.ssl.key-store-provider= # Provider for the key store.
        server.ssl.key-store-type= # Type of the key store.
        server.ssl.protocol=TLS # SSL protocol to use.
        server.ssl.trust-store= # Trust store that holds SSL certificates.
        server.ssl.trust-store-password= # Password used to access the trust store.
        server.ssl.trust-store-provider= # Provider for the trust store.
        server.ssl.trust-store-type= # Type of the trust store.
        */
    }

    @Override
    public void initializer( ) { }

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
     * @param environment Spring CLoud Environment
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
     * @param sslContextBuilder SSL Context Builder
     * @return {@link SSLContext} SSL Context
     * @throws CertificateException Certificate Exception
     * @throws UnrecoverableKeyException Unrecoverable Key Exception
     * @throws NoSuchAlgorithmException No Such Algorithm Exception
     * @throws KeyStoreException Key Store Exception
     * @throws KeyManagementException Key Management Exception
     * @throws IOException IO Exception
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
