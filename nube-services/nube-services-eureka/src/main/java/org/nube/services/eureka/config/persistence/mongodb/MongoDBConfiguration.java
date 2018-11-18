/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 13:48
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.eureka.config.persistence.mongodb;

import com.mongodb.MongoClient;
import org.nube.core.base.data.provider.mongodb.AbstractMongoAdapter;
import org.nube.core.base.data.provider.mongodb.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * HTTP Sessions Mongo DB Configuration.
 *
 * @see MongoAdapter
 * @see AbstractMongoAdapter
 */
@Configuration
@EnableMongoRepositories(
        basePackages = "org.nube.services.eureka",
        mongoTemplateRef = "mongoHttpSession" )
public class MongoDBConfiguration extends AbstractMongoAdapter
        implements MongoAdapter {

    @Primary
    @Bean( name = "httpSessionsMongoClient" )
    public MongoClient httpSessionsMongoClient( )
            throws Exception {
        return super.httpSessionsMongoClient( ); }

    @Primary
    @Bean( name = "httpSessionsDatabaseName" )
    public String httpSessionsDatabaseName( ) {
        return super.httpSessionsDatabaseName( ); }

    @Primary
    @Bean( name = "httpSessionsMongoTemplate" )
    public MongoTemplate httpSessionsMongoTemplate( )
            throws Exception {
        return super.httpSessionsMongoTemplate( ); }

    @Primary
    @Bean
    public MongoDbFactory mongoDbFactory( MongoClient mongoClient, String database )
            throws Exception {
        return super.mongoDbFactory( mongoClient, database ); }
}
