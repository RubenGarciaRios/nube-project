/*
 *  Developed by Rubén García Ríos
 *  Last modified 22/11/18 18:59
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import com.mongodb.MongoClient;
import org.nube.core.base.data.NubeDataObject;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Objects;

/**
 * MongoDB Connector interface.
 * Used to create a connection for MongoDB transactions.
 *
 * @author Rubén García Ríos
 */
public interface MongoDBConnector
        extends NubeDataObject {
    // DEFAULT VALUES.
    /**
     * DEFAULT_SERVER_SERVER_ADDRESS constant.
     */
    ServerAddress DEFAULT_SERVER_SERVER_ADDRESS = new ServerAddress( );
    /**
     * DEFAULT_USERNAME constant.
     */
    String DEFAULT_USERNAME = "";
    /**
     * DEFAULT_PASSWORD constant.
     */
    String DEFAULT_PASSWORD = "";

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

    class Connection {
        private MongoClient mongoClient;
        private MongoDbFactory mongoDbFactory;
        private MongoTemplate mongoTemplate;

        public Connection( ) { }

        public Connection(
                final MongoClient mongoClient,
                final MongoDbFactory mongoDbFactory,
                final MongoTemplate mongoTemplate ) {
            this.mongoClient = mongoClient;
            this.mongoDbFactory = mongoDbFactory;
            this.mongoTemplate = mongoTemplate;
        }

        public MongoClient getMongoClient( )
            { return mongoClient; }

        public void setMongoClient( final MongoClient mongoClient )
            { this.mongoClient = mongoClient; }

        public MongoDbFactory getMongoDbFactory( )
            { return mongoDbFactory; }
    }
}
