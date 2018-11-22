/*
 *  Developed by Rubén García Ríos
 *  Last modified 22/11/18 18:55
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import com.mongodb.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class AbstractMongoDBConnector
        implements MongoDBConnector {
    protected final Logger _LOG = LogManager.getLogger( this.getClass( ) );
    private static final long serialVersionUID = -4726786715635985784L;
    // ATTRIBUTES.
    protected Collection< ServerAddress > serverAddresses =
            new ArrayList< ServerAddress >( ) { {
                add( DEFAULT_SERVER_SERVER_ADDRESS ); } };
    protected String database;
    protected String username = DEFAULT_USERNAME;
    protected String password = DEFAULT_PASSWORD;

    public AbstractMongoDBConnector(
            ServerAddress serverAddress,
            @NotNull final String database,
            @NotNull final String username,
            @NotNull final String password ) {
        if ( serverAddress != null
             && serverAddress.getHost( ) != null
             && serverAddress.getPort( ) > 0 ) {
            this.serverAddresses = new ArrayList< >( );
            this.serverAddresses.add( serverAddress );
        }
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public AbstractMongoDBConnector(
            Collection< ServerAddress > serverAddresses,
            @NotNull final String database,
            @NotNull final String username,
            @NotNull final String password ) {
        if ( serverAddresses != null && !serverAddresses.isEmpty( ) )
            this.serverAddresses = serverAddresses;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public Connection connect( ) {
        try {
            List< com.mongodb.ServerAddress > serverAddresses = new ArrayList< >( );
            for ( ServerAddress serverAddress : this.serverAddresses )
                serverAddresses.add(
                        new com.mongodb.ServerAddress(
                                serverAddress.getHost( ),
                                serverAddress.getPort( ) ) );
            MongoClient mongoClient = new MongoClient( serverAddresses );
            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory( mongoClient, database );
            MongoTemplate mongoTemplate = new MongoTemplate( mongoDbFactory );
            return new Connection(
                    mongoClient,
                    mongoDbFactory,
                    mongoTemplate );
        } catch ( Exception e ) {
            _LOG.error( "{}", e );
        }
        return null;
    }

    //@formatter:on
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof AbstractMongoDBConnector ) ) return false;
        final AbstractMongoDBConnector that = ( AbstractMongoDBConnector ) o;
        return Objects.equals( this.serverAddresses, that.serverAddresses )
               && Objects.equals( this.database, that.database )
               && Objects.equals( this.username, that.username )
               && Objects.equals( this.password, that.password );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( serverAddresses, database, username, password ); }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) + "{" +
               "serverAddresses=" + serverAddresses +
               ", database='" + database + '\'' +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' + '}';
    }
    //@formatter:on
}
