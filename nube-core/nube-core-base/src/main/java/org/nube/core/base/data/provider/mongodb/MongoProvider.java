/*
 *  Developed by Rubén García Ríos
 *  Last modified 27/11/18 23:58
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.Provider;
import org.nube.core.base.data.provider.DataProvider;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.*;

/**
 * Mongo Provider.
 * Provide a MongoDB server connection to allow database operations.
 *
 * @see org.nube.core.base.data.provider.DataProvider
 * @author Rubén García Ríos
 */
public class MongoProvider
        implements DataProvider {
    private final static Logger _LOG = LogManager.getLogger( MongoProvider.class );
    private static final long serialVersionUID = -6129138067502029817L;
    // CONSTANTS.
    /**
     * PROVIDER constant.
     */
    public static final Provider PROVIDER = Provider.MONGODB;
    /**
     * CONNECTION_TIMEOUT constant.
     */
    public static final int CONNECTION_TIMEOUT = 1000 * 60; // 60 seconds.
    // ATTRIBUTES
    private MongoClient mongoClient;
    private MongoDbFactory mongoDbFactory;
    private MongoTemplate mongoTemplate;

    /**
     * Instantiates a new Mongo provider.
     *
     * @param builder the builder
     */
    public MongoProvider( final MongoBuilder builder ) {
        provide( builder.convertServerAddresses( ), builder.convertCredential( ) );
    }

    /**
     * Mongo Client.
     *
     * @return the mongo client
     */
    public MongoClient mongoClient( )
        { return mongoClient; }

    /**
     * MongoDB Factory.
     *
     * @return the mongo db factory
     */
    public MongoDbFactory mongoDbFactory( )
        { return mongoDbFactory; }

    /**
     * Mongo Template.
     *
     * @return the mongo template
     */
    public MongoTemplate mongoTemplate( )
        { return mongoTemplate; }

    /**
     * Mongo builder.
     * Helps to Build a new instance of Mongo Provider.
     *
     * @see org.nube.core.base.data.provider.DataProvider.Builder
     * @author Rubén García Ríos
     */
    public static class MongoBuilder
            implements Builder {
        private static final long serialVersionUID = -334705676293868423L;
        // ATTRIBUTES.
        private Collection< MongoServerAddress > serverAddresses;
        private MongoCredential credential;
        private boolean hasDefaultServerAddress;

        /**
         * Instantiates a new Mongo Builder.
         */
        public MongoBuilder( ) {
            serverAddresses = new ArrayList< >( );
            serverAddresses.add( ( MongoServerAddress ) serverAddress( ) );
            hasDefaultServerAddress = true;
            credential = new MongoCredential( );
        }

        @Override
        public MongoProvider build( )
            { return new MongoProvider( this ); }

        @Override
        public MongoBuilder serverAddresses(
                final Collection< ServerAddress > serverAddresses ) {
            this.serverAddresses.clear( );
            for ( ServerAddress serverAddress : serverAddresses )
                this.serverAddresses.add( ( MongoServerAddress ) serverAddresses );
            return this;
        }

        @Override
        public MongoBuilder credential(
                final Credential credential ) {
            this.credential = ( MongoCredential ) credential;
            return this;
        }

        @Override
        public MongoBuilder addServerAdress(
                ServerAddress serverAddress ) {
            clearDefaultServerAdress( );
            this.serverAddresses.add( ( MongoServerAddress ) serverAddress );
            return this;
        }

        @Override
        public MongoBuilder removeServerAdress(
                ServerAddress serverAddress ) {
            this.serverAddresses.remove( serverAddress );
            return this;
        }

        @Override
        public MongoBuilder addAllServerAdress(
                Collection< ServerAddress > serverAddresses ) {
            clearDefaultServerAdress( );
            for ( ServerAddress serverAddress : serverAddresses )
                this.serverAddresses.add( ( MongoServerAddress ) serverAddresses );
            return this;
        }

        @Override
        public MongoBuilder removeAllServerAdress(
                Collection< ServerAddress > serverAddresses ) {
            for ( ServerAddress serverAddress : serverAddresses )
                removeServerAdress( ( MongoBuilder.ServerAddress ) serverAddresses );
            return this;
        }

        @Override
        public MongoBuilder.ServerAddress serverAddress( )
            { return new MongoServerAddress( ); }

        @Override
        public Credential credential( )
            { return new MongoCredential( ); }

        private void clearDefaultServerAdress( ) {
            if ( hasDefaultServerAddress ) {
                this.serverAddresses.clear( );
                hasDefaultServerAddress = false;
            }
        }

        private List< com.mongodb.ServerAddress > convertServerAddresses( ) {
            ArrayList< com.mongodb.ServerAddress > serverAddresses = new ArrayList< >( );
            for ( MongoBuilder.MongoServerAddress serverAddress : this.serverAddresses ) {
                if ( serverAddress != null &&
                     serverAddress.host != null && !serverAddress.host.isEmpty( ) &&
                     serverAddress.port > 0 ) {
                    serverAddresses.add( serverAddress.convert( ) );
                }
            }
            return serverAddresses;
        }

        private com.mongodb.MongoCredential convertCredential( ) {
            return credential.database != null && !credential.database.isEmpty( )
                   ? credential.convert( )
                   : null;
        }

        /**
         * Mongo Server Address.
         * Net information of MongoDB server, used to establish a connection.
         *
         * @see org.nube.core.base.data.provider.DataProvider.Builder.ServerAddress
         * @author Rubén García Ríos
         */
        public static class MongoServerAddress
                implements ServerAddress {
            private static final long serialVersionUID = -2911807985277042921L;
            // CONSTANTS.
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

            /**
             * Instantiates a new Mongo Server Address.
             */
            public MongoServerAddress( ) {
                host = DEFAULT_HOST;
                port = DEFAULT_PORT;
            }

            @Override
            public MongoServerAddress host( final String host ) {
                this.host = host;
                return this;
            }

            @Override
            public MongoServerAddress port( final int port ) {
                this.port = port;
                return this;
            }

            /**
             * Gets serial version uid.
             *
             * @return the serial version uid
             */
            public static long getSerialVersionUID( )
                { return serialVersionUID; }

            private com.mongodb.ServerAddress convert( )
                { return new com.mongodb.ServerAddress( host, port ); }

            @Override
            public boolean equals( final Object o ) {
                if ( this == o ) return true;
                if ( !( o instanceof MongoServerAddress ) ) return false;
                final MongoServerAddress that = ( MongoServerAddress ) o;
                return port == that.port &&
                       Objects.equals( host, that.host );
            }

            @Override
            public int hashCode( )
                { return Objects.hash( host, port ); }

            @Override
            public String toString( ) {
                return this.getClass( ).getName( ) + "{" +
                        "host='" + host + '\'' +
                        ", port=" + port + '}';
            }
        }

        /**
         * Mongo Credential.
         * Credential information for MongoDB connection to requested source database.
         * Used to authenticate to a mongo server, as well as the source of the credential.
         *
         * @see org.nube.core.base.data.provider.DataProvider.Builder.Credential
         * @author Rubén García Ríos
         */
        public static class MongoCredential
                implements Credential {
            private static final long serialVersionUID = -7394496989019455622L;
            // CONSTANTS.
            /**
             * DEFAULT_DATABASE constant.
             */
            public static final String DEFAULT_DATABASE = "nube";
            /**
             * DEFAULT_USERNAME constant.
             */
            public static final String DEFAULT_USERNAME = "";
            /**
             * DEFAULT_PASSWORD constant.
             */
            public static final char[ ] DEFAULT_PASSWORD = new char[ 0 ];
            // ATTRIBUTES.
            private String database;
            private String username;
            private char[ ] password;

            /**
             * Instantiates a new MongoDB Credential.
             */
            public MongoCredential( ) {
                database = DEFAULT_DATABASE;
                username = DEFAULT_USERNAME;
                password = DEFAULT_PASSWORD;
            }

            @Override
            public Credential database( final String database ) {
                this.database = database;
                return this;
            }

            @Override
            public Credential username( final String username ) {
                this.username = username;
                return this;
            }

            @Override
            public Credential password( final char[ ] password ) {
                this.password = password;
                return this;
            }

            /**
             * Gets serial version uid.
             *
             * @return the serial version uid
             */
            public static long getSerialVersionUID( )
                { return serialVersionUID; }

            private com.mongodb.MongoCredential convert( ) {
                return com.mongodb.MongoCredential.createCredential(
                        username, database, password );
            }

            @Override
            public boolean equals( final Object o ) {
                if ( this == o ) return true;
                if ( !( o instanceof MongoCredential ) ) return false;
                final MongoCredential that = ( MongoCredential ) o;
                return Objects.equals( database, that.database ) &&
                       Objects.equals( username, that.username ) &&
                       Arrays.equals( password, that.password );
            }

            @Override
            public int hashCode( ) {
                int result = Objects.hash( database, username );
                result = 31 * result + Arrays.hashCode( password );
                return result;
            }

            @Override
            public String toString( ) {
                return this.getClass( ).getName( ) + "{" +
                        "database='" + database + '\'' +
                        ", username='" + username + '\'' +
                        ", password=" + Arrays.toString( password ) + '}';
            }
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof MongoBuilder ) ) return false;
            final MongoBuilder that = ( MongoBuilder ) o;
            return hasDefaultServerAddress == that.hasDefaultServerAddress &&
                   Objects.equals( serverAddresses, that.serverAddresses ) &&
                   Objects.equals( credential, that.credential );
        }

        @Override
        public int hashCode( )
            { return Objects.hash( serverAddresses, credential, hasDefaultServerAddress ); }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) + "{" +
                    "serverAddresses=" + serverAddresses +
                    ", credential=" + credential +
                    ", hasDefaultServerAddress=" + hasDefaultServerAddress + '}';
        }
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    private void provide(
            final List< ServerAddress > serverAddresses,
            final MongoCredential mongoCredential ) {
        _LOG.debug( "Recieved argument of type [{}] serverAddresses with value: {}",
                    serverAddresses.getClass( ),
                    serverAddresses );
        _LOG.debug( "Recieved argument of type [{}] mongoCredential with value: {}",
                    mongoCredential.getClass( ),
                    mongoCredential );
        if ( serverAddresses.isEmpty( ) ) {
            _LOG.error( "The argument of type [{}] serverAddresses must not be null: {}",
                        serverAddresses.getClass( ),
                        serverAddresses );
            return;
        }
        // Mongo Credentials validation.
        if ( mongoCredential.getSource( ) == null || mongoCredential.getSource( ).isEmpty( ) ) {
            _LOG.error( mongoCredential == null
                                ? "The argument of type [{}] mongoCredential must not be null: {}"
                                : "The argument of type [{}] mongoCredential must not be have a assigned source: {}",
                        serverAddresses.getClass( ),
                        serverAddresses );
            return;
        }
        // Instance of MongoDB Client.
        _LOG.debug( "Providing a new instance of [{}]", mongoClient.getClass( ).getName( ) );
        mongoClient = new MongoClient(
                serverAddresses,
                mongoCredential,
                MongoClientOptions.builder( )
                        .connectTimeout( CONNECTION_TIMEOUT )
                        .build( ) );
        _LOG.debug( "The [{}] instance has been provided successfully: {}",
                    MongoClient.class.getName( ),
                    mongoClient );
        // Instance of MongoDB Factory.
        _LOG.debug( "Providing a new instance of [{}]", mongoClient.getClass( ).getName( ) );
        mongoDbFactory = new SimpleMongoDbFactory(
                mongoClient,
                mongoCredential.getSource( ) );
        _LOG.debug( "The [{}] instance has been provided successfully: {}",
                    MongoDbFactory.class.getName( ),
                    mongoDbFactory );
        // Instance of MongoDB Template.
        _LOG.debug( "Providing a new instance of [{}]", mongoTemplate.getClass( ).getName( ) );
        mongoTemplate = new MongoTemplate( mongoDbFactory );
        _LOG.debug( "The [{}] instance has been provided successfully: {}",
                    MongoTemplate.class.getName( ),
                    mongoTemplate );
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoProvider ) ) return false;
        final MongoProvider that = ( MongoProvider ) o;
        return Objects.equals( mongoClient, that.mongoClient ) &&
               Objects.equals( mongoDbFactory, that.mongoDbFactory ) &&
               Objects.equals( mongoTemplate, that.mongoTemplate );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( mongoClient, mongoDbFactory, mongoTemplate ); }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) + "{" +
                "mongoClient=" + mongoClient +
                ", mongoDbFactory=" + mongoDbFactory +
                ", mongoTemplate=" + mongoTemplate + '}';
    }
}
