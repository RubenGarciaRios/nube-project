/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 12:30
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.property;

import org.nube.core.base.data.provider.DataProviderType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ConfigurationProperties( prefix =
        NubeDatabaseProperties.NUBE_PROPERTIES_DATABASE_PREFIX )
//TODO - DOCUMENTATION
public class NubeDatabaseProperties
        extends NubePropertiesObject {
    public static final String NUBE_PROPERTIES_DATABASE_PREFIX = "nube.databases";
    private static final long serialVersionUID = -1051358036546910054L;
    // ATTRIBUTES.
    @NotEmpty
    private String type;
    private DataProviderType dataProviderType;
    @Valid
    private List< ServerAddress > serverAddresses;
    @Valid
    private Credentials credentials;
    // CONSTRUCTORS.
    public NubeDatabaseProperties( ) { }
    public NubeDatabaseProperties(
            @NotEmpty final String type, final DataProviderType dataProviderType,
            @Valid final List< ServerAddress > serverAddresses,
            @Valid final Credentials credentials ) {
        this.type = type;
        this.dataProviderType = dataProviderType;
        this.serverAddresses = serverAddresses;
        this.credentials = credentials;
    }

    public static class ServerAddress
            extends NubePropertiesObject {
        private static final long serialVersionUID = 3710769124992262963L;
        // CONSTANTS
        public final String DEFAULT_HOST = "localhost";
        // ATTRIBUTES.
        @NotEmpty
        private String host = DEFAULT_HOST;
        private int port;
        // CONSTRUCTORS.
        public ServerAddress( ) { }
        public ServerAddress(
                @NotEmpty final String host,
                final int port ) {
            this.host = host;
            this.port = port;
        }

        public String getHost( )
            { return host; }

        public void setHost( final String host )
            { this.host = host; }

        public int getPort( )
            { return port; }

        public void setPort( final int port )
            { this.port = port; }

        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof ServerAddress ) ) return false;
            final ServerAddress that = ( ServerAddress ) o;
            return getPort( ) == that.getPort( ) &&
                   Objects.equals( getHost( ), that.getHost( ) );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( getHost( ), getPort( ) ); }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) +
                   "{host='" + host + '\'' +
                   ", port=" + port + '}' +
                   META_DATA;
        }
    }

    public static class Credentials
            extends NubePropertiesObject {
        private static final long serialVersionUID = -5814005680134039838L;
        // ATTRIBUTES.
        @NotEmpty
        private String database;
        private String username;
        private char[ ] password;
        // CONSTRUCTORS.
        public Credentials( ) { }
        public Credentials(
                @NotEmpty final String database,
                final String username,
                final char[ ] password ) {
            this.database = database;
            this.username = username;
            this.password = password;
        }

        public String getDatabase( )
            { return database; }

        public void setDatabase( final String database )
            { this.database = database; }

        public String getUsername( )
            { return username; }

        public void setUsername( final String username )
            { this.username = username; }

        public char[ ] getPassword( )
            { return password; }

        public void setPassword( final char[ ] password )
            { this.password = password; }

        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof Credentials ) ) return false;
            final Credentials that = ( Credentials ) o;
            return Objects.equals( getDatabase( ), that.getDatabase( ) ) &&
                   Objects.equals( getUsername( ), that.getUsername( ) ) &&
                   Arrays.equals( getPassword( ), that.getPassword( ) );
        }

        @Override
        public int hashCode( ) {
            int result = Objects.hash( getDatabase( ), getUsername( ) );
            result = 31 * result + Arrays.hashCode( getPassword( ) );
            return result;
        }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) +
                   "{database='" + database + '\'' +
                   ", username='" + username + '\'' +
                   ", password=" + Arrays.toString( password ) + '}' +
                   META_DATA;
        }
    }

    public String getType( )
        { return type; }

    public void setType( final String type )
        { this.type = type; }

    public DataProviderType getDataProviderType( )
        { return dataProviderType; }

    public void setDataProviderType( final DataProviderType dataProviderType )
        { this.dataProviderType = dataProviderType; }

    public List< ServerAddress > getServerAddresses( )
        { return serverAddresses; }

    public void setServerAddresses( final List< ServerAddress > serverAddresses )
        { this.serverAddresses = serverAddresses; }

    public Credentials getCredentials( )
        { return credentials; }

    public void setCredentials( final Credentials credentials )
        { this.credentials = credentials; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof NubeDatabaseProperties ) ) return false;
        final NubeDatabaseProperties that = ( NubeDatabaseProperties ) o;
        return Objects.equals( getType( ), that.getType( ) ) &&
               getDataProviderType( ) == that.getDataProviderType( ) &&
               Objects.equals( getServerAddresses( ), that.getServerAddresses( ) ) &&
               Objects.equals( getCredentials( ), that.getCredentials( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getType( ), getDataProviderType( ), getServerAddresses( ), getCredentials( ) ); }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) +
               "{type='" + type + '\'' +
               ", dataProviderType=" + dataProviderType +
               ", serverAddresses=" + serverAddresses +
               ", credentials=" + credentials + '}' +
               META_DATA;
    }
}
