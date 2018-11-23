/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:59
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data.provider.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
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
public interface MongoInstanceAdapter
        extends NubeConfigurationObject {
    // DEFAULT VALUES.
    /**
     * DEFAULT_SERVER_SERVER_ADDRESS constant.
     */
    ServerAddress DEFAULT_SERVER_SERVER_ADDRESS = new ServerAddress( );
    /**
     * DEFAULT_USERNAME constant.
     */
    Source DEFAULT_SOURCE = new Source( );
    //formatter:off
    /**
     * Gets new instance of server address.
     *
     * @return the server address
     */
    static ServerAddress serverAddress( )
        { return new ServerAddress( ); }

    /**
     * Gets new instance of source
     *
     * @return the source
     */
    static Source source( )
        { return new Source( ); }
    //formatter:on
    /**
     * MongoDB Server Address.
     * Used to connect with MongoDB server.
     *
     * @author Rubén García Ríos
     */
    class ServerAddress
            implements NubeConfigurationObject {
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
        private String host;
        private int port;
        // CONSTRUCTORS.
        /**
         * Instantiates a new MongoDB server address.
         */
        public ServerAddress( ) { 
            this.host = DEFAULT_HOST; 
            this.port = DEFAULT_PORT;
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
         * Sets host.
         *
         * @param host the host
         * @return {@code this}
         */
        public ServerAddress host( final String host ) {
            this.host = host;
            return this;
        }

        /**
         * Sets port.
         *
         * @param port the port
         * @return {@code this}            
         */
        public ServerAddress port( final int port ) {
            this.port = port;
            return this;
        }
        
        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof ServerAddress ) ) return false;
            final ServerAddress that = ( ServerAddress ) o;
            return port == that.port && Objects.equals( host, that.host );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( host, port ); }

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
            implements NubeConfigurationObject {
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
        private String source;
        private String username;
        private String password;
        // CONSTRUCTORS.
        /**
         * Instantiates a new Mongo credential.
         */
        public Source( ) {
            this.source = DEFAULT_SOURCE;
            this.username = DEFAULT_USERNAME;
            this.password = DEFAULT_PASSWORD;
        }
        //@formatter:on
        /**
         * Gets serial version uid.
         *
         * @return the serial version uid
         */
        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        /**
         * Sets source.
         *
         * @param source the source
         * @return {@code this}
         */
        public Source source( final String source ) {
            this.source = source;
            return this;
        }

        /**
         * Sets username.
         *
         * @param username the username
         * @return {@code this}
         */
        public Source username( final String username ) {
            this.username = username;
            return this;
        }

        /**
         * Sets password.
         *
         * @param password the password
         * @return {@code this}
         */
        public Source password( final String password ) {
            this.password = password;
            return this;
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof Source ) ) return false;
            final Source that = ( Source ) o;
            return Objects.equals( source, that.source ) &&
                   Objects.equals( username, that.username ) &&
                   Objects.equals( password, that.password );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( source, username, password ); }

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
            implements NubeConfigurationObject {
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
         * Used to configure new MongoDB Configured Objects.
         *
         * @author Rubén García Ríos
         */
        public static class Configurator
                implements NubeConfigurationObject {
            private final static Logger _LOG = LogManager.getLogger( Configurator.class );
            private static final long serialVersionUID = 835836726798208610L;
            // CONSTANTS.
            private static final int CONNECTION_TIMEOUT = 1000 * 60; // 60 seconds.
            //@formatter:off
            /**
             * Configure MongoDB Objects.
             * Used to configure new MongoDB Configured Objects.
             *
             * @param serverAddress the host and ip for server connection
             * @param source the database, username and password selected
             */
            public static MongoDBConfiguredObjects configure(
                    final ServerAddress serverAddress,
                    final Source source ) {
                Collection< ServerAddress > serverAddresses = new ArrayList< >( );
                Collection< Source > sources = new ArrayList< >( );
                // Server address validation. If not valid default values are added.
                serverAddresses.add(
                        serverAddress != null
                        && serverAddress.host != null && !serverAddress.host.isEmpty( )
                        && serverAddress.port > 0
                                ? serverAddress
                                : DEFAULT_SERVER_SERVER_ADDRESS );
                // Source validation. If not valid default values are added.
                return configure(
                        serverAddress,
                        source != null &&
                        source.source   != null && !source.source.isEmpty( ) &&
                        source.username != null && !source.username.isEmpty( ) &&
                        source.password != null && !source.password.isEmpty( )
                                ? source
                                : DEFAULT_SOURCE );
            }

            /**
             * Configure MongoDB Objects.
             * Used to configure new MongoDB Configured Objects.
             *
             * @param serverAddresses the list of hosts and ips for servers connection
             * @param source the database, username and password selected
             */
            public static MongoDBConfiguredObjects configure(
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
                        // Prevents configure if any of server addresses are not valid.
                    } else for ( ServerAddress serverAddress : serverAddresses ) {
                        if ( serverAddress == null
                             && serverAddress.host != null && !serverAddress.host.isEmpty( )
                             && serverAddress.port > 0 ) {
                            mongoDBServerAddress.add(
                                    new com.mongodb.ServerAddress(
                                            serverAddress.host,
                                            serverAddress.port ) );
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
                            /* Prevents configure if any of server addresses are not valid. */
                            source != null
                            && source.source   != null && !source.source.isEmpty( )
                            && source.username != null && !source.username.isEmpty( )
                            && source.password != null && !source.password.isEmpty( )
                                    ? MongoCredential.createCredential(
                                    source.username,
                                    source.source,
                                    source.password.toCharArray( ) )
                                    : MongoCredential.createCredential(
                                            DEFAULT_SOURCE.username,
                                            DEFAULT_SOURCE.source,
                                            DEFAULT_SOURCE.password.toCharArray( ) ),
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
                            source.source );
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
