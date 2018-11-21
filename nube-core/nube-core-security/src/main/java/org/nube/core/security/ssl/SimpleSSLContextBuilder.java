/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.ssl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.property.NubeSSLProperties;
import org.springframework.core.env.Environment;

/**
 * Builds a default SSL Context with the parametrized properties found in
 * bootstrap or application files (.yml or .properties extension).
 * For now the default properties are:
 * <ul>
 * <li>server.ssl.key-store: Keystore location</li>
 * <li>server.ssl.key-key-store-type: Keystore type</li>
 * <li>server.ssl.key-store-password: Keystore password</li>
 * <li>server.ssl.key-password: Key password (optional if is the same that keystore password)</li>
 * <li>server.ssl.key-alias: Keystore alias</li>
 * <li>server.ssl.trust-store: Truststore location</li>
 * <li>server.ssl.trust-store-type: Truststore type (optional)</li>
 * <li>server.ssl.trust-store-password: Truststore password</li>
 * </ul>
 *
 * @author Rubén García Ríos
 * @see AbstractSSLContextBuilder
 */
public class SimpleSSLContextBuilder
        extends AbstractSSLContextBuilder {
    private static final Logger _LOG = LogManager.getLogger( SimpleSSLContextBuilder.class );
    private static final long serialVersionUID = -4325110864647029525L;


    /**
     * Embedded servlet container ssl properties.
     */
    public static class EmbeddedServletContainerSSLProperties {
        /**
         * KEYSTORE_PATH_PROPERTY constant.
         */
        public static final String KEYSTORE_PATH_PROPERTY = "server.ssl.key-store";
        /**
         * KEYSTORE_TYPE_PROPERTY constant.
         */
        public static final String KEYSTORE_TYPE_PROPERTY = "server.ssl.key-store-type";
        /**
         * KEY_PASSWORD_PROPERTY constant.
         */
        public static final String KEY_PASSWORD_PROPERTY = "server.ssl.key-password";
        /**
         * KEYSTORE_PASSWORD_PROPERTY constant.
         */
        public static final String KEYSTORE_PASSWORD_PROPERTY = "server.ssl.key-store-password";
        /**
         * KEY_ALIAS_PROPERTY constant.
         */
        public static final String KEY_ALIAS_PROPERTY = "server.ssl.key-alias";
        /**
         * TRUSTSTORE_PATH_PROPERTY constant.
         */
        public static final String TRUSTSTORE_PATH_PROPERTY = "server.ssl.trust-store";
        /**
         * TRUSTSTORE_TYPE_PROPERTY constant.
         */
        public static final String TRUSTSTORE_TYPE_PROPERTY = "server.ssl.trust-store-type";
        /**
         * TRUSTSTORE_PASSWORD_PROPERTY constant.
         */
        public static final String TRUSTSTORE_PASSWORD_PROPERTY = "server.ssl.trust-store-password";
    }

    /**
     * Instantiates a new Simple ssl context builder.
     *
     * @param keyStorePath       Key store directory path location
     * @param keyStoreType       Key store type (JKS, PKCS12...)
     * @param keyPassword        Key password
     * @param keyStorePassword   Key store password
     * @param keyAlias           Key alias
     * @param trustStorePath     Trust store directory path location
     * @param trustStorePassword Trust store password
     * @param trustStoreType     Trust store type (JKS, PKCS12...)
     */
    public SimpleSSLContextBuilder( String keyStorePath,
                                    String keyStoreType,
                                    String keyPassword,
                                    String keyStorePassword,
                                    String keyAlias,
                                    String trustStorePath,
                                    String trustStorePassword,
                                    String trustStoreType ) {
        _LOG.debug( "Setting keyStorePath to value: {}", keyStorePath );
        super.setKeyStorePath( keyStorePath );
        _LOG.debug( "Setting keyStoreType to value: {}", keyStoreType );
        super.setKeyStoreType( keyStoreType );
        keyPassword = keyPassword == null || keyPassword.length( ) == 0
                ? keyStorePassword : keyPassword;
        _LOG.debug( "Setting keyPassword to value: {}", keyPassword );
        super.setKeyPassword( keyPassword );
        keyStorePassword = keyStorePassword == null || keyStorePassword.length( ) == 0
                ? keyPassword : keyStorePassword;
        _LOG.debug( "Setting keyStorePassword to value: {}", keyStorePassword );
        super.setKeyStorePassword( keyStorePassword );
        _LOG.debug( "Setting keyAlias to value: {}", keyAlias );
        super.setKeyAlias( keyAlias );
        _LOG.debug( "Setting trustStorePath to value: {}", trustStorePath );
        super.setTrustStorePath( trustStorePath );
        _LOG.debug( "Setting trustStorePassword to value: {}", trustStoreType );
        super.setTrustStorePassword( trustStorePassword);
        trustStoreType = trustStoreType == null || trustStoreType.length( ) == 0
                ? keyStoreType : trustStoreType;
        _LOG.debug( "Setting trustStoreType to value: {}", trustStoreType );
        super.setTrustStoreType( trustStoreType );
    }

    /**
     * Instantiates a new Default ssl context builder.
     *
     * @param nubeSSLProperties NUBE SSL Properties.
     */
    public SimpleSSLContextBuilder( NubeSSLProperties nubeSSLProperties ) {
        this( nubeSSLProperties.getKeyStore( ).getPath( ),
              nubeSSLProperties.getKeyStore( ).getType( ),
              nubeSSLProperties.getKeyStore( ).getPassword( ),
              nubeSSLProperties.getKeyStore( ).getStorePassword( ),
              nubeSSLProperties.getKeyStore( ).getAlias( ),
              nubeSSLProperties.getTrustStore( ).getPath( ),
              nubeSSLProperties.getTrustStore( ).getStorePassword( ),
              nubeSSLProperties.getTrustStore( ).getType( ) );
    }

    /**
     * Instantiates a new Default ssl context builder.
     *
     * @param environment Spring Environment
     */
    public SimpleSSLContextBuilder( Environment environment ) {
        this( environment.getProperty( EmbeddedServletContainerSSLProperties.KEYSTORE_PATH_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.KEYSTORE_TYPE_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.KEY_PASSWORD_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.KEYSTORE_PASSWORD_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.KEY_ALIAS_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.TRUSTSTORE_PATH_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.TRUSTSTORE_PASSWORD_PROPERTY ),
              environment.getProperty( EmbeddedServletContainerSSLProperties.TRUSTSTORE_TYPE_PROPERTY ) );
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }
}
