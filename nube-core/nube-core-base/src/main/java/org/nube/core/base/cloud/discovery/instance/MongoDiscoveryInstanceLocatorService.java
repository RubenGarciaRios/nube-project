/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 14:05
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud.discovery.instance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.base.data.provider.mongodb.MongoProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories(
        basePackages = { "org.nube.core." },
        includeFilters = {
                @ComponentScan.Filter( MongoRepository.class ) },
        excludeFilters = {
                @ComponentScan.Filter( Repository.class ) } )
public class MongoDiscoveryInstanceLocatorService
        implements DiscoveryInstanceLocatorService {
    private final static Logger _LOG = LogManager.getLogger( MongoDiscoveryInstanceLocatorService.class );
    private static final long serialVersionUID = -5521827949876602770L;
    private MongoProvider mongoProvider;

    public MongoDiscoveryInstanceLocatorService(
            String host,
            int port,
            String database,
            String username,
            char[ ] password ) {
        try {
            _LOG.debug( "Recieved argument 'host' of type [{}] with value: {}",
                        host.getClass( ),
                        host );
            _LOG.debug( "Recieved argument 'port' of type [primitive int] with value: {}",
                        port );
            _LOG.debug( "Recieved argument 'database' of type [{}] with value: {}",
                        database.getClass( ),
                        database );
            _LOG.debug( "Recieved argument 'username' of type [{}] with value: {}",
                        username.getClass( ),
                        username );
            _LOG.debug( "Recieved argument 'password' of type [{}] with value: {}",
                        password.getClass( ),
                        password );
            _LOG.debug( "Creating a new Instance of [{}]", mongoProvider.getClass( ).getName( ) );
            mongoProvider = MongoProvider.builder( )
                    /* MONGODB SERVER ADDRESS CONFIGURATION */
                    .addServerAdress(
                            MongoProvider.MongoBuilder.serverAddress( )
                                    .host( host )
                                    .port( port ) )
                    /* MONGODB CREDENTIAL CONFIGURATION */
                    .credential(
                            MongoProvider.MongoBuilder.credential( )
                                    .database( database )
                                    .username( username )
                                    .password( password ) )
                    .build( );
            _LOG.debug( "A new Instance of [{}] has created successfully", mongoProvider.getClass( ).getName( ) );
        } catch ( Exception e ) {
            _LOG.error( "An error has occurred during [{}] 'mongoProvider' initialization: {}",
                        mongoProvider.getClass( ).getName( ), e );
        }
    }

    public void getInstances( ) {
        // mongoProvider.mongoTemplate().
    }
}