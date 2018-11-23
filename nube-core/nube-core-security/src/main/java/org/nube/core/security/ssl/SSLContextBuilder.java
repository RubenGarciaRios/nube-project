/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.ssl;

import org.nube.core.base.security.NubeSecurityObject;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface SSLContextBuilder
        extends NubeSecurityObject {
    void setKeyStorePath( String keyStorePath );

    void setKeyStoreType( String keyStoreType );

    void setKeyPassword( String keyPassword );

    void setKeyStorePassword( String keyStorePassword );

    void setKeyAlias( String keyAlias );

    void setTrustStorePath( String trustStorePath );

    void setTrustStorePassword( String trustStorePassword );

    void setTrustStoreType( String trustStoreType );

    SSLContext build( )
            throws UnrecoverableKeyException,
            CertificateException,
            NoSuchAlgorithmException,
            KeyStoreException,
            IOException,
            KeyManagementException;
}
