/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 14:34
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.property;

import org.jetbrains.annotations.Nullable;
import org.nube.core.base.data.AbstractServerAddress;
import org.nube.core.base.data.provider.DataProviderType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ConfigurationProperties( prefix =
        NubeDataSourceProperties.PREFIX )
//TODO - DOCUMENTATION
public class NubeDataSourceProperties
        extends NubePropertiesObject {
    public static final String PREFIX = "nube.datasource";
    private static final long serialVersionUID = 8169624474566318142L;
    @Valid
    private DataSource[ ] dataSources;

    public NubeDataSourceProperties( final DataSource[ ] dataSources )
        { this.dataSources = dataSources; }

    public DataSource[ ] getDataSources( )
        { return dataSources; }

    public void setDataSources( final DataSource[ ] dataSources )
        { this.dataSources = dataSources; }

    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    public static class DataSource
            extends NubePropertiesObject {
        private static final long serialVersionUID = -1051358036546910054L;
        // ATTRIBUTES.
        @NotEmpty
        private String id;
        private DataProviderType dataProviderType;
        @NotEmpty
        private String database;
        @Nullable
        private String username;
        @Nullable
        private char[ ] password;
        @Valid
        private List< ServerAddress > serverAddresses;
        // CONSTRUCTORS.
        public DataSource( ) { }

        public DataSource(
                @NotEmpty final String id,
                final DataProviderType dataProviderType,
                @NotEmpty final String database,
                @Nullable final String username,
                @Nullable final char[] password,
                @Valid final List< ServerAddress > serverAddresses ) {
            this.id = id;
            this.dataProviderType = dataProviderType;
            this.database = database;
            this.username = username;
            this.password = password;
            this.serverAddresses = serverAddresses;
        }

        public DataProviderType getDataProviderType( )
            { return dataProviderType; }

        public void setDataProviderType( final DataProviderType dataProviderType )
            { this.dataProviderType = dataProviderType; }

        public String getId( )
            { return id; }

        public void setId( @NotNull final String id )
            { this.id = id; }

        public String getUsername( )
            { return username; }

        public void setUsername( final String username )
            { this.username = username; }

        public String getDatabase( )
            { return database; }

        public void setDatabase( @NotNull final String database )
            { this.database = database; }

        public char[ ] getPassword( )
            { return password; }

        public void setPassword( final char[ ] password )
            { this.password = password; }

        public List< ServerAddress > getServerAddresses( )
            { return serverAddresses; }

        public void setServerAddresses( final List< ServerAddress > serverAddresses )
            { this.serverAddresses = serverAddresses; }

        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        public static class ServerAddress
                extends AbstractServerAddress { }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof DataSource ) ) return false;
            final DataSource that = ( DataSource ) o;
            return Objects.equals( getId( ), that.getId( ) ) &&
                   getDataProviderType( ) == that.getDataProviderType( ) &&
                   Objects.equals( getDatabase( ), that.getDatabase( ) ) &&
                   Objects.equals( getUsername( ), that.getUsername( ) ) &&
                   Arrays.equals( getPassword( ), that.getPassword( ) ) &&
                   Objects.equals( getServerAddresses( ), that.getServerAddresses( ) );
        }

        @Override
        public int hashCode( ) {
            int result = Objects.hash(
                    getId( ),
                    getDataProviderType( ),
                    getDatabase( ),
                    getUsername( ),
                    getServerAddresses( ) );
            result = 31 * result + Arrays.hashCode( getPassword( ) );
            return result;
        }

        @Override
        public String toString( )
            { return super.toString( ); }
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof NubeDataSourceProperties ) ) return false;
        final NubeDataSourceProperties that = ( NubeDataSourceProperties ) o;
        return Arrays.equals( getDataSources( ), that.getDataSources( ) );
    }

    @Override
    public int hashCode( )
        { return Arrays.hashCode( getDataSources( ) ); }

    @Override
    public String toString( )
        { return super.toString( ); }
}
