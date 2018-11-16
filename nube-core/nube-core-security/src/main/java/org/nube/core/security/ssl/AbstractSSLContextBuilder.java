/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.ssl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.ssl.SSLContexts;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Abstract implementation of SSLContextBuilder.
 *
 * @author Rubén García Ríos
 * @see SSLContextBuilder
 */
public abstract class AbstractSSLContextBuilder
        implements SSLContextBuilder {
    private static final long serialVersionUID = -6923672013897532L;
    /**
     * Path to key store file.
     */
    private String keyStorePath;
    /**
     * Key store type (PKCS12, JKS, ...).
     */
    private String keyStoreType;
    /**
     * Key password.
     */
    private String keyPassword;
    /**
     * Key store password.
     */
    private String keyStorePassword;
    /**
     * Key store alias.
     */
    private String keyAlias;
    /**
     * Path to trust store file.
     */
    private String trustStorePath;
    /**
     * Trust store password.
     */
    private String trustStorePassword;
    /**
     * Trust store type (PKCS12, JKS, ...).
     */
    private String trustStoreType;

    /**
     * Spring Default Resource Loader.
     */
    @Autowired
    private DefaultResourceLoader resourceLoader;

    /**
     * Instantiates a new Abstract SSL Context Builder.
     */
    public AbstractSSLContextBuilder( ) { }

    /**
     * Instantiates a new Abstract SSL Context Builder.
     *
     * @param keyStorePath       the key store path
     * @param keyStoreType       the key store type
     * @param keyPassword        the key password
     * @param keyStorePassword   the key store password
     * @param keyAlias           the key alias
     * @param trustStorePath     the trust store path
     * @param trustStorePassword the trust store password
     * @param trustStoreType     the trust store type
     */
    public AbstractSSLContextBuilder(
            String keyStorePath,
            String keyStoreType,
            String keyPassword,
            String keyStorePassword,
            String keyAlias,
            String trustStorePath,
            String trustStorePassword,
            String trustStoreType ) {
        this.keyStorePath = keyStorePath;
        this.keyStoreType = keyStoreType;
        this.keyPassword = keyPassword;
        this.keyStorePassword = keyStorePassword;
        this.keyAlias = keyAlias;
        this.trustStorePath = trustStorePath;
        this.trustStorePassword = trustStorePassword;
        this.trustStoreType = trustStoreType;
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    private File getResourceFile( String filePath )
            throws IOException {
        File resourceFile = File.createTempFile(
                RandomStringUtils.random( 5, true, true ), ".tmp" );
        try ( OutputStream outputStream = new FileOutputStream( resourceFile ) ) {
            IOUtils.copy( resourceLoader.getResource( filePath ).getInputStream( ), outputStream );
        }
        return resourceFile;
    }

    private File getKeystoreFile( )
            throws IOException {
        return this.getResourceFile( this.keyStorePath );
    }

    private File getTrustStoreFile( )
            throws IOException {
        return this.getResourceFile( this.trustStorePath );
    }

    @Override
    public void setKeyStorePath( String keyStorePath ) {
        this.keyStorePath = keyStorePath;
    }

    @Override
    public void setKeyStoreType( String keyStoreType ) {
        this.keyStoreType = keyStoreType;
    }

    @Override
    public void setKeyPassword( String keyPassword ) {
        this.keyPassword = keyPassword;
    }

    @Override
    public void setKeyStorePassword( String keyStorePassword ) {
        this.keyStorePassword = keyStorePassword;
    }

    @Override
    public void setKeyAlias( String keyAlias ) {
        this.keyAlias = keyAlias;
    }

    @Override
    public void setTrustStorePath( String trustStorePath ) {
        this.trustStorePath = trustStorePath;
    }

    @Override
    public void setTrustStorePassword( String trustStorePassword ) {
        this.trustStorePassword = trustStorePassword;
    }

    @Override
    public void setTrustStoreType( String trustStoreType ) {
        this.trustStoreType = trustStoreType;
    }

    /**
     * Create a SSLContext with the parametrized attributes.
     *
     * @return {@link SSLContext}
     * @throws UnrecoverableKeyException the unrecoverable key exception
     * @throws CertificateException      the certificate exception
     * @throws NoSuchAlgorithmException  the no such algorithm exception
     * @throws KeyStoreException         the key store exception
     * @throws IOException               the io exception
     * @throws KeyManagementException    the key management exception
     * @see SSLContextBuilder#build()
     */
    @Override
    public SSLContext build( )
            throws UnrecoverableKeyException,
            CertificateException,
            NoSuchAlgorithmException,
            KeyStoreException,
            IOException,
            KeyManagementException {
        return SSLContexts.custom( )
                .setKeyStoreType( keyStoreType )
                .loadKeyMaterial(
                        this.getKeystoreFile( ),
                        this.keyStorePassword.toCharArray( ),
                        this.keyPassword == null ? this.keyStorePassword.toCharArray( )
                                : this.keyPassword.toCharArray( ),
                        ( aliases, socket ) -> this.keyAlias )
                .loadTrustMaterial(
                        this.getTrustStoreFile( ),
                        this.trustStorePassword.toCharArray( ) )
                .build( );
    }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) + "{" +
                "keyStorePath='" + keyStorePath + '\'' +
                ", keyStoreType='" + keyStoreType + '\'' +
                ", keyStorePassword='" + keyStorePassword + '\'' +
                ", keyAlias='" + keyAlias + '\'' +
                ", trustStorePath='" + trustStorePath + '\'' +
                ", trustStorePassword='" + trustStorePassword + '\'' +
                ", trustStoreType='" + trustStoreType + '\'' +
                '}';
    }
}
