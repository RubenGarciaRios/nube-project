/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.ssl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;

/**
 * Builds a default SSL Context with the parametrized properties found in
 * bootstrap or application files (.yml or .properties extension).
 * For now the default properties are:
 * <ul>
 *     <li>server.ssl.key-store: Keystore location</li>
 *     <li>server.ssl.key-key-store-type: Keystore type</li>
 *     <li>server.ssl.key-store-password: Keystore password</li>
 *     <li>server.ssl.key-password: Key password (optional if is the same that keystore password)</li>
 *     <li>server.ssl.key-alias: Keystore alias</li>
 *     <li>server.ssl.trust-store: Truststore location</li>
 *     <li>server.ssl.trust-store-type: Truststore type (optional)</li>
 *     <li>server.ssl.trust-store-password: Truststore password</li>
 * </ul>
 *
 * @author Rubén García Ríos
 * @see AbstractSSLContextBuilder
 */
public class SimpleSSLContextBuilder
        extends AbstractSSLContextBuilder {
    private static final Logger _LOG = LogManager.getLogger( SimpleSSLContextBuilder.class );
    private static final long serialVersionUID = -4325110864647029525L;

    private static final String KEYSTORE_PATH_PROPERTY = "server.ssl.key-store";
    private static final String KEYSTORE_TYPE_PROPERTY = "server.ssl.key-store-type";
    private static final String KEYSTORE_PASSWORD_PROPERTY = "server.ssl.key-store-password";
    private static final String KEY_PASSWORD_PROPERTY = "server.ssl.key-password";
    private static final String KEY_ALIAS_PROPERTY = "server.ssl.key-alias";
    private static final String TRUSTSTORE_PATH_PROPERTY = "server.ssl.trust-store";
    private static final String TRUSTSTORE_TYPE_PROPERTY = "server.ssl.trust-store-type";
    private static final String TRUSTSTORE_PASSWORD_PROPERTY = "server.ssl.trust-store-password";

    /**
     * Instantiates a new Default ssl context builder.
     *
     * @param environment the environment
     */
    public SimpleSSLContextBuilder( Environment environment ) {
        String property = environment.getProperty( KEYSTORE_PATH_PROPERTY );
        _LOG.debug( "Setting keyStorePath to value: {}", property );
        super.setKeyStorePath( property );
        property = environment.getProperty( KEYSTORE_TYPE_PROPERTY );
        _LOG.debug( "Setting keyStoreType to value: {}", property );
        super.setKeyStoreType( property );
        property = environment.getProperty( KEYSTORE_PASSWORD_PROPERTY );
        _LOG.debug( "Setting keyStorePassword to value: {}", property );
        super.setKeyStorePassword( property );
        property = environment.getProperty( KEY_PASSWORD_PROPERTY ) == null
                || environment.getProperty( KEY_PASSWORD_PROPERTY ).length( ) == 0
                ? environment.getProperty( KEYSTORE_PASSWORD_PROPERTY )
                : environment.getProperty( KEY_PASSWORD_PROPERTY );
        _LOG.debug( "Setting keyPassword to value: {}", property );
        super.setKeyPassword( property );
        property = environment.getProperty( KEY_ALIAS_PROPERTY );
        _LOG.debug( "Setting keyAlias to value: {}", property );
        super.setKeyAlias( property );
        property = environment.getProperty( TRUSTSTORE_PATH_PROPERTY );
        _LOG.debug( "Setting trustStorePath to value: {}", property );
        super.setTrustStorePath( property );
        property = environment.getProperty( TRUSTSTORE_TYPE_PROPERTY ) == null
                || environment.getProperty( TRUSTSTORE_TYPE_PROPERTY ).length( ) == 0
                ? environment.getProperty( KEYSTORE_TYPE_PROPERTY )
                : environment.getProperty( TRUSTSTORE_TYPE_PROPERTY );
        _LOG.debug( "Setting trustStoreType to value: {}", property );
        super.setTrustStoreType( property );
        property = environment.getProperty( TRUSTSTORE_PASSWORD_PROPERTY );
        _LOG.debug( "Setting trustStorePassword to value: {}", property );
        super.setTrustStorePassword( property );
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
