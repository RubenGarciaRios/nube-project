package org.nube.core.base.data.property;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * NUBE SSL Configuration properties.
 *
 * @author Rubén García Ríos
 */
@ConfigurationProperties( prefix = NubeSSLProperties.NUBE_PROPERTIES_SSL_PREFIX )
public class NubeSSLProperties
        implements NubePropertiesObject {
    public static final String NUBE_PROPERTIES_SSL_PREFIX = "nube.ssl";
    private static final Logger _LOG = LogManager.getLogger( NubeSSLProperties.class );
    private static final long serialVersionUID = 6376617351138655352L;
    // Two way SSL, indicates that client must to be authenticated.
    private String clientAuth = "need";
    // Keystore configuration.
    private KeyStore keyStore = new KeyStore( );
    // TrustStore configuration.
    private TrustStore trustStore = new TrustStore( );

    public String getClientAuth( ) {
        return clientAuth;
    }

    public void setClientAuth( String clientAuth ) {
        this.clientAuth = clientAuth;
    }

    public KeyStore getKeyStore( ) {
        return keyStore;
    }

    public void setKeyStore( KeyStore keyStore ) {
        this.keyStore = keyStore;
    }

    public TrustStore getTrustStore( ) {
        return trustStore;
    }

    public void setTrustStore( TrustStore trustStore ) {
        this.trustStore = trustStore;
    }

    // KEYSTORE CONFIGURATION.
    public static class KeyStore
            implements NubePropertiesObject {
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

        public String getPath( ) {
            return path;
        }

        public void setPath( String path ) {
            this.path = path;
        }

        public String getType( ) {
            return type;
        }

        public void setType( String type ) {
            this.type = type;
        }

        public String getAlias( ) {
            return alias;
        }

        public void setAlias( String alias ) {
            this.alias = alias;
        }
        // If password is null gets the store password (Indicates that password and store password is the same).
        public String getPassword( ) {
            return password != null ? password : storePassword;
        }

        public void setPassword( String password ) {
            this.password = password;
        }
        // If store password is null gets the password (Indicates that password and store password is the same).
        public String getStorePassword( ) {
            return storePassword != null ? storePassword : password;
        }

        public void setStorePassword( String storePassword ) {
            this.storePassword = storePassword;
        }

        public static long getSerialVersionUID( ) {
            return serialVersionUID;
        }

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
        public int hashCode( ) {
            return Objects.hash( getPath( ), getType( ), getAlias( ), getPassword( ), getStorePassword( ) );
        }

        @Override
        public String toString( ) {
            return "KeyStore{" +
                    "path='" + path + '\'' +
                    ", type='" + type + '\'' +
                    ", alias='" + alias + '\'' +
                    ", password='" + password + '\'' +
                    ", storePassword='" + storePassword + '\'' +
                    '}';
        }
    }

    // TRUSTSTORE CONFIGURATION.
    public static class TrustStore
            implements NubePropertiesObject {
        private static final long serialVersionUID = -1515924486798971518L;
        // Path to truststore.
        private String path;
        // Type of truststore (JKS, PKCS12...).
        private String type;
        // Truststore password.
        private String password;
        // Truststore store password.
        private String storePassword;

        public String getPath( ) {
            return path;
        }

        public void setPath( String path ) {
            this.path = path;
        }

        public String getType( ) {
            return type;
        }

        public void setType( String type ) {
            this.type = type;
        }

        // If password is null gets the store password (Indicates that password and store password is the same).
        public String getPassword( ) {
            return password != null ? password : storePassword;
        }

        public void setPassword( String password ) {
            this.password = password;
        }
        // If store password is null gets the password (Indicates that password and store password is the same).
        public String getStorePassword( ) {
            return storePassword != null ? storePassword : password;
        }

        public void setStorePassword( String storePassword ) {
            this.storePassword = storePassword;
        }

        public static long getSerialVersionUID( ) {
            return serialVersionUID;
        }

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
        public int hashCode( ) {
            return Objects.hash( getPath( ), getType( ), getPassword( ), getStorePassword( ) );
        }

        @Override
        public String toString( ) {
            return "TrustStore{" +
                    "path='" + path + '\'' +
                    ", type='" + type + '\'' +
                    ", password='" + password + '\'' +
                    ", storePassword='" + storePassword + '\'' +
                    '}';
        }
    }

    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof NubeSSLProperties ) ) return false;
        NubeSSLProperties that = ( NubeSSLProperties ) o;
        return Objects.equals( getClientAuth( ), that.getClientAuth( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( getClientAuth( ) );
    }

    @Override
    public String toString( ) {
        return "NubeSSLProperties{" +
                "clientAuth='" + clientAuth + '\'' +
                ", keyStore=" + keyStore +
                ", trustStore=" + trustStore +
                '}';
    }
}
