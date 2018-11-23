/*
 *  Developed by Rubén García Ríos
 *  Last modified 23/11/18 14:43
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.ssl;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.property.NubeSSLProperties;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.nube.core.security.ssl.SimpleSSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Default SSL connection configuration for all NUBE services.
 *
 * @author Rubén García Ríos
 */
@PropertySources( {
    @PropertySource( "classpath:nube-configuration.properties" ) } )
@EnableConfigurationProperties( NubeSSLProperties.class )
public class DefaultSSLConfiguration
        implements SSLConfiguration{
    private static final Logger _LOG = LogManager.getLogger( DefaultSSLConfiguration.class );
    private static final long serialVersionUID = -3170599873338821186L;

    @Autowired
    public DefaultSSLConfiguration( ) {
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
     * @param nubeSSLProperties NUBE SSL Properties
     * @return {@link SimpleSSLContextBuilder}
     */
    @Override
    @Bean( "sslContextBuilder" )
    @ConditionalOnMissingBean
    public SSLContextBuilder simpleSSLContextBuilder( NubeSSLProperties nubeSSLProperties ) {
        _LOG.info( " - Generating SimpleSSLContextBuilder bean..." );
        SSLContextBuilder simpleSSLContextBuilder = new SimpleSSLContextBuilder( nubeSSLProperties );
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
     * @see DefaultSSLConfiguration#simpleSSLContextBuilder(NubeSSLProperties)
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
