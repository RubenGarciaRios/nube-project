/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 14:34
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.property;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.provider.DataProviderType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * NUBE Discovery Instance Properties.
 * Used by core configuration module for disovery instance configuration.
 *
 * @author Rubén García Ríos
 */
@ConfigurationProperties( prefix =
        NubeDiscoveryInstanceProperties.PREFIX )
public class NubeDiscoveryInstanceProperties
        extends NubePropertiesObject {
    private static final Logger _LOG = LogManager.getLogger( NubeDiscoveryInstanceProperties.class );
    /**
     * PREFIX constant.
     */
    public static final String PREFIX = "nube.cloud.discovery-instance";
    private static final long serialVersionUID = -4542913480070535272L;
    // ATTRIBUTES.
    private DataBase dataBase;
    private Collection< Region > regions;

    public NubeDiscoveryInstanceProperties( ) { }
    public NubeDiscoveryInstanceProperties(
            final DataBase dataBase,
            final Collection< Region > regions ) {
        this.dataBase = dataBase;
        this.regions = regions;
    }

    /**
     * NUBE Discovery Cluster Database Properties.
     * Used by core configuration module for disovery discovery Database configuration.
     * <p>
     * If <code>nube.cloud.discovery-discovery.database.enabled = true</code> NUBE Services
     * connect to database to search all regions availables and connect to discovery service for high availability.
     *
     * @author Rubén García Ríos.
     */
    public static class DataBase
            extends NubePropertiesObject {
        private static final Logger _LOG = LogManager.getLogger( DataBase.class );
        private static final long serialVersionUID = 1484876254726430361L;
        // DEFAULT VALUES.
        /**
         * DEFAULT_ENABLED constant.
         */
        public static final boolean DEFAULT_ENABLED = true;
        /**
         * DEFAULT_DATA_PROVIDER_TYPE constant.
         */
        public static final DataProviderType DEFAULT_DATA_PROVIDER_TYPE = DataProviderType.MONGODB;
        /**
         * DEFAULT_SERVER_ADDRESS constant.
         */
        public static final ServerAddress DEFAULT_SERVER_ADDRESS =
                new ServerAddress( "localhost", 27017 );
        /**
         * DEFAULT_NAME constant.
         */
        public static final String DEFAULT_NAME = "nube_discovery_cluster";
        /**
         * DEFAULT_USERNAME constant.
         */
        public static final String DEFAULT_USERNAME = "";
        /**
         * DEFAULT_PASSWORD constant.
         */
        public static final String DEFAULT_PASSWORD = "";
        // ATTRIBUTES.
        private boolean enabled = DEFAULT_ENABLED;
        private DataProviderType dataProviderType = DEFAULT_DATA_PROVIDER_TYPE;
        private Collection< ServerAddress > addresses =
            new ArrayList< ServerAddress >( ) { {
                add( DEFAULT_SERVER_ADDRESS );
        } };
        private String name = DEFAULT_NAME;
        private String username = DEFAULT_USERNAME;
        private String password = DEFAULT_PASSWORD;

        public DataBase( ) { }
        public DataBase(
                final boolean enabled,
                final DataProviderType dataProviderType,
                final Collection< ServerAddress > addresses,
                final String name,
                final String username,
                final String password ) {
            this.enabled = enabled;
            this.dataProviderType = dataProviderType;
            this.addresses = addresses;
            this.name = name;
            this.username = username;
            this.password = password;
        }

        /**
         * Discovery Cluster Database Server Address properties.
         * Used by core configuration module for disovery discovery Database server
         * addresses configuration.
         *
         * @author Rubén García Ríos
         */
        public static class ServerAddress
                extends NubePropertiesObject {
            private static final Logger _LOG = LogManager.getLogger( ServerAddress.class );
            private static final long serialVersionUID = -618112279689199266L;

            private String host;
            private int port;

            public ServerAddress( ) { }
            public ServerAddress( final String host, final int port ) {
                this.host = host;
                this.port = port;
            }

            //formatter:off
            /**
             * Gets serial version uid.
             *
             * @return the serial version uid
             */
            public static long getSerialVersionUID( )
            { return serialVersionUID; }

            /**
             * Gets host.
             *
             * @return the host
             */
            public String getHost( )
            { return host; }

            /**
             * Sets host.
             *
             * @param host the host
             */
            public void setHost( String host )
            { this.host = host; }

            /**
             * Gets port.
             *
             * @return the port
             */
            public int getPort( )
            { return port; }

            /**
             * Sets port.
             *
             * @param port the port
             */
            public void setPort( int port )
                { this.port = port; }

            @Override
            public boolean equals( final Object o ) {
                if ( this == o ) return true;
                if ( !( o instanceof ServerAddress ) ) return false;
                final ServerAddress that = ( ServerAddress ) o;
                return getPort( ) == that.getPort( ) && Objects.equals( getHost( ), that.getHost( ) );
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
            //formatter:on
        }

        //@formatter:off
        /**
         * Gets serial version uid.
         *
         * @return the serial version uid
         */
        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        /**
         * Is enabled boolean.
         * Default value: <code>true</code>
         *
         * @return the boolean
         */
        public boolean isEnabled( )
            { return enabled; }

        /**
         * Sets enabled.
         *
         * @param enabled the enabled
         */
        public void setEnabled( boolean enabled )
            { this.enabled = enabled; }

        /**
         * Gets dataProviderType.
         *
         * @return the dataProviderType
         */
        public DataProviderType getDataProviderType( )
            { return dataProviderType; }

        /**
         * Sets dataProviderType.
         *
         * @param dataProviderType the dataProviderType
         */
        public void setDataProviderType( DataProviderType dataProviderType )
            { this.dataProviderType = dataProviderType; }

        /**
         * Gets addresses.
         *
         * @return the addresses
         */
        public Collection< ServerAddress > getAddresses( )
            { return addresses; }

        /**
         * Sets addresses.
         *
         * @param addresses the addresses
         */
        public void setAddresses( final Collection< ServerAddress > addresses )
            { this.addresses = addresses; }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName( )
            { return name; }

        /**
         * Sets name.
         *
         * @param name the name
         */
        public void setName( String name )
            { this.name = name; }

        /**
         * Gets username.
         *
         * @return the username
         */
        public String getUsername( )
            { return username; }

        /**
         * Sets username.
         *
         * @param username the username
         */
        public void setUsername( String username )
            { this.username = username; }

        /**
         * Gets password.
         *
         * @return the password
         */
        public String getPassword( )
            { return password; }

        /**
         * Sets password.
         *
         * @param password the password
         */
        public void setPassword( String password )
            { this.password = password; }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof DataBase ) ) return false;
            final DataBase dataBase = ( DataBase ) o;
            return isEnabled( ) == dataBase.isEnabled( )
                   && getDataProviderType( ) == dataBase.getDataProviderType( )
                   && Objects.equals( getAddresses( ), dataBase.getAddresses( ) )
                   && Objects.equals( getName( ), dataBase.getName( ) )
                   && Objects.equals( getUsername( ),dataBase.getUsername( ) )
                   && Objects.equals( getPassword( ), dataBase.getPassword( ) );
        }

        @Override
        public int hashCode( ) {
            return Objects.hash(
                    isEnabled( ),
                    getDataProviderType( ),
                    getAddresses( ),
                    getName( ),
                    getUsername( ),
                    getPassword( ) );
        }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) +
                   "{enabled=" + enabled +
                   ", dataProviderType=" + dataProviderType +
                   ", addresses=" + addresses +
                   ", name='" + name + '\'' +
                   ", username='" + username + '\'' +
                   ", password='" + password + "'}" +
                   META_DATA;
        }
        //@formatter:on
    }

    /**
     * NUBE Discovery Cluster Region Properties.
     * Used by core configuration module for disovery discovery Region configuration.
     * <p>
     * If <code>nube.cloud.discovery-discovery.database.enabled = false</code> or NUBE Services
     * can't connect to database, this properties are default used to connect to discovery
     * service for high availability.
     *
     * @author Rubén García Ríos
     */
    public static class Region
            extends NubePropertiesObject {
        private static final Logger _LOG = LogManager.getLogger( Region.class );
        private static final long serialVersionUID = -1968683883215746298L;
        // ATTRIBUTES.
        @NotBlank
        private String name;
        @NotBlank
        private Collection< Zone > zones;

        public Region( ) { }
        public Region(
                @NotBlank final String name,
                @NotBlank final Collection< Zone > zones ) {
            this.name = name;
            this.zones = zones;
        }

        /**
         * NUBE Discovery Cluster Region Zone Properties.
         *
         * @author Rubén García Ríos
         */
        public static class Zone
                extends NubePropertiesObject {
            private static final Logger _LOG = LogManager.getLogger( Zone.class );
            private static final long serialVersionUID = -2602112810157402198L;
            // DEFAULT VALUES.
            private static final boolean DEFAULT_ENABLED = true;
            // ATTRIBUTES.
            @NotBlank
            private String name;
            @NotBlank
            @Pattern( regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$" )
            private String ip;
            @NotBlank
            private String dns;
            @NotBlank
            private int discoveryPort;
            private boolean enabled = DEFAULT_ENABLED;

            public Zone( ) { }
            public Zone(
                    @NotBlank final String name,
                    @NotBlank
                    @Pattern( regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$" )
                    final String ip,
                    @NotBlank final String dns,
                    @NotBlank final int discoveryPort,
                    final boolean enabled ) {
                this.name = name;
                this.ip = ip;
                this.dns = dns;
                this.discoveryPort = discoveryPort;
                this.enabled = enabled;
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
             * Gets name.
             *
             * @return the name
             */
            public String getName( )
                { return name; }

            /**
             * Sets name.
             *
             * @param name the name
             */
            public void setName( final String name )
                { this.name = name; }

            /**
             * Gets ip.
             *
             * @return the ip
             */
            public String getIp( )
                { return ip; }

            /**
             * Sets ip.
             *
             * @param ip the ip
             */
            public void setIp( final String ip )
                { this.ip = ip; }

            /**
             * Gets dns.
             *
             * @return the dns
             */
            public String getDns( )
                { return dns; }

            /**
             * Sets dns.
             *
             * @param dns the dns
             */
            public void setDns( final String dns )
                { this.dns = dns; }

            /**
             * Gets discovery port.
             *
             * @return the discovery port
             */
            public int getDiscoveryPort( )
                { return discoveryPort; }

            /**
             * Sets discovery port.
             *
             * @param discoveryPort the discovery port
             */
            public void setDiscoveryPort( final int discoveryPort )
                { this.discoveryPort = discoveryPort; }

            /**
             * Is enabled boolean.
             *
             * @return the boolean
             */
            public boolean isEnabled( )
                { return enabled; }

            /**
             * Sets enabled.
             *
             * @param enabled the enabled
             */
            public void setEnabled( final boolean enabled )
                { this.enabled = enabled; }

            @Override
            public boolean equals( final Object o ) {
                if ( this == o ) return true;
                if ( !( o instanceof Zone ) ) return false;
                final Zone zone = ( Zone ) o;
                return getDiscoveryPort( ) == zone.getDiscoveryPort( )
                       && isEnabled( ) == zone.isEnabled( )
                       && Objects.equals( getName( ), zone.getName( ) )
                       && Objects.equals( getIp( ), zone.getIp( ) )
                       && Objects.equals( getDns( ),                                                                                   zone.getDns( ) );
            }

            @Override
            public int hashCode( )
                { return Objects.hash( getName( ), getIp( ), getDns( ), getDiscoveryPort( ), isEnabled( ) ); }

            @Override
            public String toString( ) {
                return this.getClass( ).getName( ) +
                     "{name='" + name + '\'' +
                     ", ip='" + ip + '\'' +
                     ", dns='" + dns + '\'' +
                     ", discoveryPort=" + discoveryPort +
                     ", enabled=" + enabled + '}' +
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
         * Gets name.
         *
         * @return the name
         */
        public String getName( )
            { return name; }

        /**
         * Sets name.
         *
         * @param name the name
         */
        public void setName( final String name )
            { this.name = name; }

        /**
         * Gets zones.
         *
         * @return the zones
         */
        public Collection< Zone > getZones( )
            { return zones; }

        /**
         * Sets zones.
         *
         * @param zones the zones
         */
        public void setZones( final Collection< Zone > zones )
            { this.zones = zones; }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof Region ) ) return false;
            final Region region = ( Region ) o;
            return Objects.equals( getName( ), region.getName( ) )
                   && Objects.equals( getZones( ), region.getZones( ) );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( getName( ), getZones( ) ); }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) +
                   "{name='" + name + '\'' +
                   ", zones=" + zones + '}' +
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
     * Gets data base.
     *
     * @return the data base
     */
    public DataBase getDataBase( )
        { return dataBase; }

    /**
     * Sets data base.
     *
     * @param dataBase the data base
     */
    public void setDataBase( final DataBase dataBase )
        { this.dataBase = dataBase; }

    /**
     * Gets regions.
     *
     * @return the regions
     */
    public Collection< Region > getRegions( )
        { return regions; }

    /**
     * Sets regions.
     *
     * @param regions the regions
     */
    public void setRegions( final Collection< Region > regions )
        { this.regions = regions; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof NubeDiscoveryInstanceProperties ) ) return false;
        final NubeDiscoveryInstanceProperties that = ( NubeDiscoveryInstanceProperties ) o;
        return Objects.equals( getDataBase( ), that.getDataBase( ) )
               && Objects.equals( getRegions( ), that.getRegions( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getDataBase( ), getRegions( ) ); }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) +
                "{dataBase=" + dataBase +
                ", regions=" + regions + '}' +
                META_DATA;
    }
    //@formatter:on
}
