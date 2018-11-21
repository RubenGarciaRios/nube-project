/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.ssl;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.nube.core.base.config.NubeConfigurationObject;
import org.nube.core.base.data.property.NubeSSLProperties;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface SSLConfiguration
        extends NubeConfigurationObject {
    @Bean( "sslContextBuilder" )
    @ConditionalOnMissingBean
    SSLContextBuilder simpleSSLContextBuilder( NubeSSLProperties nubeSSLProperties );

    @Bean( "sslContext" )
    @ConditionalOnClass( SSLContextBuilder.class )
    @DependsOn( { "sslContextBuilder" } )
    SSLContext sslContext( SSLContextBuilder sslContextBuilder )
            throws CertificateException,
            UnrecoverableKeyException,
            NoSuchAlgorithmException,
            KeyStoreException,
            KeyManagementException,
            IOException;

    @Bean( "sslConnectionSocketFactory" )
    @DependsOn( { "sslContext" } )
    SSLConnectionSocketFactory sslConnectionSocketFactory( SSLContext sslContext );
}
