/*
 *  Developed by Rubén García Ríos
 *  Last modified 23/11/18 14:28
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.NubeDataObject;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * MongoDB Configuration Adapter interface.
 * Used to help configure MongoDB.
 *
 * @author Rubén García Ríos
 */
public interface MongoDBConfigurationAdapter
        extends NubeDataObject {
    // DEFAULT VALUES.
    /**
     * DEFAULT_SERVER_SERVER_ADDRESS constant.
     */
    ServerAddress DEFAULT_SERVER_SERVER_ADDRESS = new ServerAddress( );
    /**
     * DEFAULT_USERNAME constant.
     */
    Source DEFAULT_SOURCE = new Source( );

    /**
     * MongoDB Server Address.
     * Used to connect with MongoDB server.
     *
     * @author Rubén García Ríos
     */
    class ServerAddress
            implements NubeDataObject {
        private static final long serialVersionUID = 7271253691928677587L;
        // DEFAULT VALUES.
        /**
         * DEFAULT_HOST constant.
         */
        public static final String DEFAULT_HOST = "localhost";
        /**
         * DEFAULT_PORT constant.
         */
        public static final int DEFAULT_PORT = 27017;
        // ATTRIBUTES.
        private String host = DEFAULT_HOST;
        private int port = DEFAULT_PORT ;
        // CONSTRUCTORS.
        /**
         * Instantiates a new MongoDB server address.
         */
        public ServerAddress( ) { }

        /**
         * Instantiates a new MongoDB server address.
         *
         * @param host the host
         * @param port the port
         */
        public ServerAddress( final String host, final int port ) {
            this.host = host;
            this.port = port;
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
        public void setHost( final String host )
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
        public void setPort( final int port )
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
        public String toString( )
            { return "ServerAddress{" + "host='" + host + '\'' + ", port=" + port + '}'; }
        //@formatter:on
    }

    /**
     * MongoDB Source.
     * Used to authenticate to a mongo server,as well as the source of the credentials.
     *
     * @author Rubén García Ríos
     */
    class Source
            implements NubeDataObject {
        private static final long serialVersionUID = -6290577400278292426L;
        // DEFAULT VALUES.
        /**
         * DEFAULT_SOURCE constant.
         */
        String DEFAULT_SOURCE = "nube";
        /**
         * DEFAULT_USERNAME constant.
         */
        String DEFAULT_USERNAME = "";
        /**
         * DEFAULT_PASSWORD constant.
         */
        String DEFAULT_PASSWORD = "";
        // ATTRIBUTES.
        private String source = DEFAULT_SOURCE;
        private String username = DEFAULT_USERNAME;
        private String password = DEFAULT_PASSWORD;
        // CONSTRUCTORS.
        /**
         * Instantiates a new Mongo credential.
         */
        public Source( ) { }

        /**
         * Instantiates a new Mongo credential.
         *
         * @param source   the source
         * @param username the username
         * @param password the password
         */
        public Source(
                final String source,
                final String username,
                final String password ) {
            this.source = source;
            this.username = username;
            this.password = password;
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
         * Gets source.
         *
         * @return the source
         */
        public String getSource( )
            { return source; }

        /**
         * Sets source.
         *
         * @param source the source
         */
        public void setSource( final String source )
            { this.source = source; }

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
        public void setUsername( final String username )
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
        public void setPassword( final String password )
            { this.password = password; }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof Source ) ) return false;
            final Source that = ( Source ) o;
            return Objects.equals( getSource( ), that.getSource( ) )
                   && Objects.equals( getUsername( ), that.getUsername( ) )
                   && Objects.equals( getPassword( ), that.getPassword( ) );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( getSource( ), getUsername( ), getPassword( ) ); }

        @Override
        public String toString( ) {
            return "Source{" + "source='" + source + '\'' +
                   ", username='" + username + '\'' +
                   ", password='" + password + '\'' + '}';
        }
        //@formatter:on
    }

    /**
     * MongoDB Configured Objects.
     * Set final instances of MongoDB Configured Objects ready for
     * MongoDB transactions.
     *
     * @author Rubén García Ríos
     */
    class MongoDBConfiguredObjects
            implements NubeDataObject {
        private static final long serialVersionUID = 6703596301441733942L;
        // CONSTANTS.
        public final MongoClient MONGO_CLIENT;
        public final MongoDbFactory MONGO_DBFACTORY;
        public final MongoTemplate MONGO_TEMPLATE;
        // CONSTRUCTORS.
        // Prevents new instances outside class.
        private MongoDBConfiguredObjects(
                final MongoClient mongoClient,
                final MongoDbFactory mongoDbFactory,
                final MongoTemplate mongoTemplate ) {
            this.MONGO_CLIENT = mongoClient;
            this.MONGO_DBFACTORY = mongoDbFactory;
            this.MONGO_TEMPLATE = mongoTemplate;
        }

        /**
         * MongoDB Configured Objects Builder.
         * Used to build new MongoDB Configured Objects.
         *
         * @author Rubén García Ríos
         */
        public static class Builder
                implements NubeDataObject {
            private final static Logger _LOG = LogManager.getLogger( Builder.class );
            private static final long serialVersionUID = 835836726798208610L;
            // CONSTANTS.
            private static final int CONNECTION_TIMEOUT = 1000 * 60; // 60 seconds.
            //@formatter:off
            /**
             * MongoDB Configured Objects Builder.
             * Used to build new MongoDB Configured Objects.
             *
             * @param serverAddress the host and ip for server connection
             * @param source the database, username and password selected
             */
            public static MongoDBConfiguredObjects build(
                    final ServerAddress serverAddress,
                    final Source source ) {
                Collection< ServerAddress > serverAddresses = new ArrayList< >( );
                Collection< Source > sources = new ArrayList< >( );
                // Server address validation. If not valid default values are added.
                serverAddresses.add(
                        serverAddress != null
                        && serverAddress.getHost( ) != null && !serverAddress.getHost( ).isEmpty( )
                        && serverAddress.getPort( ) > 0
                                ? serverAddress
                                : DEFAULT_SERVER_SERVER_ADDRESS );
                // Source validation. If not valid default values are added.
                return build(
                        serverAddress,
                        source != null
                        && source.getSource( )   != null && !source.getSource( ).isEmpty( )
                        && source.getUsername( ) != null && !source.getUsername( ).isEmpty( )
                        && source.getPassword( ) != null && !source.getPassword( ).isEmpty( )
                                ? source
                                : DEFAULT_SOURCE );
            }

            /**
             * MongoDB Configured Objects Builder.
             * Used to build new MongoDB Configured Objects.
             *
             * @param serverAddresses the list of hosts and ips for servers connection
             * @param source the database, username and password selected
             */
            public static MongoDBConfiguredObjects build(
                    Collection< ServerAddress > serverAddresses,
                    Source source ) {
                try {
                    List< com.mongodb.ServerAddress > mongoDBServerAddress = new ArrayList< >( );
                    // If server addresses are not valid, default values are added.
                    if ( serverAddresses == null || serverAddresses.isEmpty( ) ) {
                        serverAddresses = new ArrayList< >( );
                        serverAddresses.add( DEFAULT_SERVER_SERVER_ADDRESS );
                        _LOG.debug( "The default values of server addresses has been loaded: {}",
                                    DEFAULT_SERVER_SERVER_ADDRESS );
                        // Prevents build if any of server addresses are not valid.
                    } else for ( ServerAddress serverAddress : serverAddresses ) {
                        if ( serverAddress == null
                             && serverAddress.getHost( ) != null && !serverAddress.getHost( ).isEmpty( )
                             && serverAddress.getPort( ) > 0 ) {
                            mongoDBServerAddress.add(
                                    new com.mongodb.ServerAddress(
                                            serverAddress.getHost( ),
                                            serverAddress.getPort( ) ) );
                            _LOG.debug( "Server address has been loaded successfully: {}",
                                        serverAddress );
                            continue;
                        }
                        _LOG.error( "Server Addresses must have a valid values: {}", serverAddress );
                        return null;
                    }
                    // Instance of MongoDB Client.
                    _LOG.debug( "Building a new instance of [{}]", MongoClient.class.getName( ) );
                    MongoClient mongoClient = new MongoClient(
                            mongoDBServerAddress,
                            /* Prevents build if any of server addresses are not valid. */
                            source != null
                            && source.getSource( )   != null && !source.getSource( ).isEmpty( )
                            && source.getUsername( ) != null && !source.getUsername( ).isEmpty( )
                            && source.getPassword( ) != null && !source.getPassword( ).isEmpty( )
                                    ? MongoCredential.createCredential(
                                    source.getUsername( ),
                                    source.getSource( ),
                                    source.getPassword( ).toCharArray( ) )
                                    : MongoCredential.createCredential(
                                            DEFAULT_SOURCE.getUsername( ),
                                            DEFAULT_SOURCE.getSource( ),
                                            DEFAULT_SOURCE.getPassword( ).toCharArray( ) ),
                            MongoClientOptions.builder( )
                                              .connectTimeout( CONNECTION_TIMEOUT )
                                              .build( ) );
                    _LOG.debug( "The [{}] instance has been built successfully: {}",
                                MongoClient.class.getName( ),
                                mongoClient );
                    // Instance of MongoDB Factory.
                    _LOG.debug( "Building a new instance of [{}]", MongoDbFactory.class.getName( ) );
                    MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(
                            mongoClient,
                            source.getSource( ) );
                    _LOG.debug( "The [{}] instance has been built successfully: {}",
                                MongoDbFactory.class.getName( ),
                                mongoDbFactory );
                    // Instance of MongoDB Template.
                    _LOG.debug( "Building a new instance of [{}]", MongoTemplate.class.getName( ) );
                    MongoTemplate mongoTemplate = new MongoTemplate( mongoDbFactory );
                    _LOG.debug( "The [{}] instance has been built successfully: {}",
                                MongoTemplate.class.getName( ),
                                mongoTemplate );
                    final MongoDBConfiguredObjects mongoDBConfiguredObjects = new MongoDBConfiguredObjects(
                            mongoClient,
                            mongoDbFactory,
                            mongoTemplate );
                    _LOG.debug( "Returning {}", mongoDBConfiguredObjects );
                    return mongoDBConfiguredObjects;
                } catch ( Exception e ) {
                    _LOG.error( "An exception has occurred during the construction of the object of type [{}]: {}",
                                MongoDBConfiguredObjects.class.getName( ) , e );
                }
                return null;
            }

            /**
             * Gets serial version uid.
             *
             * @return the serial version uid
             */
            public static long getSerialVersionUID( )
                { return serialVersionUID; }

            @Override
            public String toString( )
                { return this.getClass( ).getName( ) + "{}"; }
            //@formatter:on
        }
        
        /**
         * Gets serial version uid.
         *
         * @return the serial version uid
         */
        public static long getSerialVersionUID( )
            { return serialVersionUID; }
    }
}
