/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 14:34
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.property;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * NUBE SSL properties.
 * Used by core configuration module for ssl configuration.
 *
 * @author Rubén García Ríos
 */
@ConfigurationProperties( prefix = NubeSSLProperties.PREFIX )
public class NubeSSLProperties
        extends NubePropertiesObject {
    /**
     * PREFIX constant.
     */
    public static final String PREFIX = "nube.ssl";
    private static final Logger _LOG = LogManager.getLogger( NubeSSLProperties.class );
    private static final long serialVersionUID = 6376617351138655352L;
    // DEFAULT VALUES.
    /**
     * DEFAULT_CLIENT_AUTH constant.
     */
    public static final String DEFAULT_CLIENT_AUTH = "need";
    // ATTRIBUTES.
    // Two way SSL, indicates that client must to be authenticated.
    private String clientAuth = DEFAULT_CLIENT_AUTH;
    // Keystore configuration.
    private KeyStore keyStore = new KeyStore( );
    // TrustStore configuration.
    private TrustStore trustStore = new TrustStore( );
    // CONSTRUCTORS.
    public NubeSSLProperties( ) { }
    public NubeSSLProperties(
            final String clientAuth,
            final KeyStore keyStore,
            final TrustStore trustStore ) {
        this.clientAuth = clientAuth;
        this.keyStore = keyStore;
        this.trustStore = trustStore;
    }

    /**
     * NUBE SSL Keystore Properties.
     * Used by core configuration module for ssl Keystore configuration.
     *
     * @author Rubén García Ríos.
     */
    public static class KeyStore
            extends NubePropertiesObject {
        private static final long serialVersionUID = -5102790233088935086L;
        // Path to keystore.
        private String path;
        // Type of keystore (JKS, PKCS12...).
        private String type;
        // Alias.
        private String alias;
        // Key password.
        private String password;
        // Key store password.
        private String storePassword;
        // CONSTRUCTORS.
        public KeyStore( ) { }
        public KeyStore(
                final String path,
                final String type,
                final String alias,
                final String password,
                final String storePassword ) {
            this.path = path;
            this.type = type;
            this.alias = alias;
            this.password = password;
            this.storePassword = storePassword;
        }

        /**
         * Gets path.
         *
         * @return the path
         */
        //@formatter:off
        public String getPath( )
            { return path; }

        /**
         * Sets path.
         *
         * @param path the path
         */
        public void setPath( String path )
            { this.path = path; }

        /**
         * Gets type.
         *
         * @return the type
         */
        public String getType( )
            { return type; }

        /**
         * Sets type.
         *
         * @param type the type
         */
        public void setType( String type )
            { this.type = type; }

        /**
         * Gets alias.
         *
         * @return the alias
         */
        public String getAlias( )
            { return alias; }

        /**
         * Sets alias.
         *
         * @param alias the alias
         */
        public void setAlias( String alias )
            { this.alias = alias; }

        /**
         * Gets password.
         * If password is null gets the store password (Indicates that password and
         * store password is the same).
         *
         * @return the password
         */
        public String getPassword( )
            { return password != null ? password : storePassword; }

        /**
         * Sets password.
         *
         * @param password the password
         */
        public void setPassword( String password )
            { this.password = password; }

        /**
         * Gets store password.
         * If store password is null gets the password (Indicates that password
         * and store password is the same).
         *
         * @return the store password
         */
        public String getStorePassword( )
            { return storePassword != null ? storePassword : password; }

        /**
         * Sets store password.
         *
         * @param storePassword the store password
         */
        public void setStorePassword( String storePassword )
            { this.storePassword = storePassword; }

        /**
         * Gets serial version uid.
         *
         * @return the serial version uid
         */
        public static long getSerialVersionUID( )
            { return serialVersionUID; }
        //@formatter:on
        @Override
        public boolean equals( Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof KeyStore ) ) return false;
            KeyStore keyStore = ( KeyStore ) o;
            return Objects.equals( getPath( ), keyStore.getPath( ) ) &&
                    Objects.equals( getType( ), keyStore.getType( ) ) &&
                    Objects.equals( getAlias( ), keyStore.getAlias( ) ) &&
                    Objects.equals( getPassword( ), keyStore.getPassword( ) ) &&
                    Objects.equals( getStorePassword( ), keyStore.getStorePassword( ) );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( getPath( ), getType( ), getAlias( ), getPassword( ), getStorePassword( ) ); }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) +
                   "{path='" + path + '\'' +
                   ", type='" + type + '\'' +
                   ", alias='" + alias + '\'' +
                   ", password='" + password + '\'' +
                   ", storePassword='" + storePassword + "'}" +
                   META_DATA;
        }
        //@formatter:on
    }

    /**
     * NUBE SSL Truststore Properties.
     * Used by core configuration module for ssl Truststore configuration.
     *
     */
    public static class TrustStore
            extends NubePropertiesObject {
        private static final long serialVersionUID = -1515924486798971518L;
        // Path to truststore.
        private String path;
        // Type of truststore (JKS, PKCS12...).
        private String type;
        // Truststore password.
        private String password;
        // Truststore store password.
        private String storePassword;
        // CONSTRUCTORS.

        public TrustStore( ) { }
        public TrustStore(
                final String path,
                final String type,
                final String password,
                final String storePassword ) {
            this.path = path;
            this.type = type;
            this.password = password;
            this.storePassword = storePassword;
        }

        /**
         * Gets path.
         *
         * @return the path
         */
        //@formatter:off
        public String getPath( )
            { return path; }

        /**
         * Sets path.
         *
         * @param path the path
         */
        public void setPath( String path )
            { this.path = path; }

        /**
         * Gets type.
         *
         * @return the type
         */
        public String getType( )
            { return type; }

        /**
         * Sets type.
         *
         * @param type the type
         */
        public void setType( String type )
            { this.type = type; }

        /**
         * Gets password.
         * If password is null gets the store password (Indicates that password and
         * store password is the same).
         *
         * @return the password
         */
        public String getPassword( )
            { return password != null ? password : storePassword; }

        /**
         * Sets password.
         *
         * @param password the password
         */
        public void setPassword( String password )
            { this.password = password; }

        /**
         * Gets store password.
         * If store password is null gets the password (Indicates that password and
         * store password is the same.
         *
         * @return the store password
         */
        public String getStorePassword( )
            { return storePassword != null ? storePassword : password; }

        /**
         * Sets store password.
         *
         * @param storePassword the store password
         */
        public void setStorePassword( String storePassword )
            { this.storePassword = storePassword; }

        /**
         * Gets serial version uid.
         *
         * @return the serial version uid
         */
        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        @Override
        public boolean equals( Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof TrustStore ) ) return false;
            TrustStore that = ( TrustStore ) o;
            return Objects.equals( getPath( ), that.getPath( ) ) &&
                    Objects.equals( getType( ), that.getType( ) ) &&
                    Objects.equals( getPassword( ), that.getPassword( ) ) &&
                    Objects.equals( getStorePassword( ), that.getStorePassword( ) );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( getPath( ), getType( ), getPassword( ), getStorePassword( ) ); }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) +
                    "{path='" + path + '\'' +
                    ", type='" + type + '\'' +
                    ", password='" + password + '\'' +
                    ", storePassword='" + storePassword + "'}" +
                    META_DATA;
        }
        //@formatter:on
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    //@formatter:off
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    /**
     * Gets client auth.
     *
     * @return the client auth
     */
    public String getClientAuth( )
        { return clientAuth; }

    /**
     * Sets client auth.
     * Default value <code>"need"</code>
     *
     * @param clientAuth the client auth
     */
    public void setClientAuth( String clientAuth )
        { this.clientAuth = clientAuth; }

    /**
     * Gets key store.
     *
     * @return the key store
     */
    public KeyStore getKeyStore( )
        { return keyStore; }

    /**
     * Sets key store.
     *
     * @param keyStore the key store
     */
    public void setKeyStore( KeyStore keyStore )
        { this.keyStore = keyStore; }

    /**
     * Gets trust store.
     *
     * @return the trust store
     */
    public TrustStore getTrustStore( )
        { return trustStore; }

    /**
     * Sets trust store.
     *
     * @param trustStore the trust store
     */
    public void setTrustStore( TrustStore trustStore )
        { this.trustStore = trustStore; }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof NubeSSLProperties ) ) return false;
        NubeSSLProperties that = ( NubeSSLProperties ) o;
        return Objects.equals( getClientAuth( ), that.getClientAuth( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getClientAuth( ) ); }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) +
                "{clientAuth='" + clientAuth + '\'' +
                ", keyStore=" + keyStore +
                ", trustStore=" + trustStore + '}' +
                META_DATA;
    }
    //@formatter:on
}
