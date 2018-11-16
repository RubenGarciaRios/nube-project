/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.nosql.mongodb;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * The interface Mongo ecosystem.
 */
public interface MongoAdapter {
    /**
     * OAuth 2 mongo client.
     *
     * @return the mongo client
     * @throws Exception the exception
     */
    MongoClient oAuth2MongoClient( )
            throws Exception;

    /**
     * OAuth 2 database name string.
     *
     * @return the string
     */
    String oAuthDatabaseName( );

    /**
     * OAuth 2 mongo template.
     *
     * @return the mongo template
     * @throws Exception the exception
     */
    MongoTemplate oAuth2MongoTemplate( )
            throws Exception;

    /**
     * Http sessions mongo client.
     *
     * @return the mongo client
     * @throws Exception the exception
     */
    MongoClient httpSessionsMongoClient( )
            throws Exception;

    /**
     * Http sessions database name string.
     *
     * @return the string
     */
    String httpSessionsDatabaseName( );

    /**
     * Http sessions mongo template.
     *
     * @return the mongo template
     * @throws Exception the exception
     */
    MongoTemplate httpSessionsMongoTemplate( )
            throws Exception;

    /**
     * Mongo db factory.
     *
     * @param mongoClient the mongo client
     * @param database    the database
     * @return the mongo db factory
     * @throws Exception the exception
     */
    MongoDbFactory mongoDbFactory( MongoClient mongoClient, String database )
            throws Exception;
}
