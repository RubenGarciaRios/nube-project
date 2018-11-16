/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.ssl;

import org.nube.core.base.CoreConfigurationObject;
import org.nube.core.security.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface SSLConfiguration
        extends CoreConfigurationObject {
    @Bean( "sslContextBuilder" )
    @ConditionalOnMissingBean
    SSLContextBuilder simpleSSLContextBuilder( Environment environment );

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