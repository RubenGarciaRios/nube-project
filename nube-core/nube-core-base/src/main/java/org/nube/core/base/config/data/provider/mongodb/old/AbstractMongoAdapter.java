/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data.provider.mongodb.old;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Implementation of Mongo Database Adapter for configurations.
 *
 * @see MongoAdapter
 */
public abstract class AbstractMongoAdapter
        implements MongoAdapter {
    protected final String DEFAULT_MONGODB_HOST = "localhost";
    protected final String DEFAULT_MONGODB_PORT = "27017";
    protected final String DEFAULT_MONGODB_DATABASE = "core";

    /**
     * For a clustered Mongo environment we would want to load multiple
     * hosts. This will work if we use a single host or clustered.
     * <p>
     * If the mongo.hosts key could not be found defaults to localhost
     * <p>
     * One of the following would exist in Spring application.yml file:
     * [application.yml]
     * mongodb:
     * oAuth2:
     * hosts: [localhost || (Comma separated) 10.0.0.1,10.0.0.2,10.0.0.3 ...]
     * port: [port number]
     * database: [database name]
     */
    @Value( "#{'${mongo.oauth2.hosts:" + DEFAULT_MONGODB_HOST + "}'.split( ',' )}" )
    protected List< String > oAuth2Hosts;

    /**
     * The port Mongo hosts port.
     * Defaults to 27017
     */
    @Value( "${mongo.oauth2.port:" + DEFAULT_MONGODB_PORT + "}" )
    protected int oAuth2Port;

    /**
     * The Mongo database name.
     * Defaults to 27017
     */
    @Value( "${mongo.oauth2.database:" + DEFAULT_MONGODB_DATABASE + "}" )
    protected String oAuth2Database;

    /**
     * The Mongo hosts list.
     * Defaults to localhost
     */
    @Value( "#{'${mongo.httpsessions.hosts:" + DEFAULT_MONGODB_HOST + "}'.split( ',' )}" )
    protected List< String > httpSessionsHosts;

    /**
     * The port Mongo hosts port.
     * Defaults to 27017
     */
    @Value( "${mongo.httpsessions.port:" + DEFAULT_MONGODB_PORT + "}" )
    protected int httpSessionsPort;

    /**
     * The Mongo database name.
     * Defaults to 27017
     */
    @Value( "${mongo.httpsessions.database:" + DEFAULT_MONGODB_DATABASE + "}" )
    protected String httpSessionsDatabase;

    /**
     * Creates a base Mongo instance that can be configured for each
     * implementation.
     * <p>
     * NOTE: If you are trying to connect to multiple MongoDB's then
     * you would want to create 2 instances of this method as beans
     * loading the correct mongo hosts. For this implementation I just
     * wanted different global configurations pointed at the same
     * database.
     * </p>
     *
     * @return A generic Mongo instance pointed at the hosts.
     */
    @Override
    public MongoClient oAuth2MongoClient( ) {
        final List< ServerAddress > serverList = new ArrayList<>( );
        for ( final String host : oAuth2Hosts ) {
            serverList.add( new ServerAddress( host, oAuth2Port ) );
        }
        // MongoClientOptions would be created here and passed into
        // the MongoClient as it's second param.
        return new MongoClient( serverList );
    }

    /**
     * OAuth 2 database name string.
     *
     * @return the string
     */
    @Override
    public String oAuthDatabaseName( ) {
        return this.oAuth2Database;
    }

    /**
     * OAuth 2 mongo template.
     *
     * @return the mongo template
     * @throws Exception the exception
     */
    @Override
    public MongoTemplate oAuth2MongoTemplate( )
            throws Exception {
        return new MongoTemplate( this.mongoDbFactory( this.oAuth2MongoClient( ), this.oAuthDatabaseName( ) ) );
    }

    /**
     * Creates a base Mongo instance that can be configured for each
     * implementation.
     * <p>
     * NOTE: If you are trying to connect to multiple MongoDB's then
     * you would want to create 2 instances of this method as beans
     * loading the correct mongo hosts. For this implementation I just
     * wanted different global configurations pointed at the same
     * database.
     * </p>
     *
     * @return A generic Mongo instance pointed at the hosts.
     * @throws Exception the exception
     */
    @Override
    public MongoClient httpSessionsMongoClient( )
            throws Exception {
        final List< ServerAddress > serverList = new ArrayList<>( );
        for ( final String host : httpSessionsHosts ) {
            serverList.add( new ServerAddress( host, httpSessionsPort ) );
        }
        // MongoClientOptions would be created here and passed into
        // the MongoClient as it's second param.
        return new MongoClient( serverList );
    }

    /**
     * HTTP Sessions database name string.
     *
     * @return the string
     */
    @Override
    public String httpSessionsDatabaseName( ) {
        return this.httpSessionsDatabase;
    }

    /**
     * HTTP Sessions mongo template.
     *
     * @return the mongo template
     * @throws Exception the exception
     */
    @Override
    public MongoTemplate httpSessionsMongoTemplate( )
            throws Exception {
        return new MongoTemplate( this.mongoDbFactory( this.httpSessionsMongoClient( ), this.httpSessionsDatabaseName( ) ) );
    }

    /**
     * This is the default DB Factory and will have the
     *
     * @param mongoClient auto injected using the @Primary bean
     * @param database    the database
     * @return a new MongoDbFactory
     * @throws Exception the exception
     */
    @Override
    public MongoDbFactory mongoDbFactory( MongoClient mongoClient, String database )
            throws Exception {
        return new SimpleMongoDbFactory( mongoClient, database );
    }
}
