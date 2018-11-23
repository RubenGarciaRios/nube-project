/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:59
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud.discovery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.data.provider.mongodb.MongoInstanceAdapter;

public class MongoDiscoveryInstanceInfo
        implements DiscoveryInstanceInfo,
        MongoInstanceAdapter {
    private final static Logger _LOG = LogManager.getLogger( MongoDiscoveryInstanceInfo.class );
    private static final long serialVersionUID = -5521827949876602770L;
    private MongoDBConfiguredObjects mongoDBConfiguredObjects;

    public MongoDiscoveryInstanceInfo(
            String host,
            int port,
            String database,
            String username,
            String password ) {
        try {
            _LOG.debug( "Building [{}] object", mongoDBConfiguredObjects.getClass( ).getName( ) );
            mongoDBConfiguredObjects = MongoDBConfiguredObjects.Configurator.configure(
                    MongoInstanceAdapter.serverAddress( )
                            .host( host )
                            .port( port ),
                    MongoInstanceAdapter.source( )
                            .source( database )
                            .username( username )
                            .password( password ) );
            _LOG.debug( "[{}] object has been built successfully: {}",
                        mongoDBConfiguredObjects.getClass( ).getName( ),
                        mongoDBConfiguredObjects );
        } catch ( Exception e ) {
            _LOG.error( "An error has occurred during [{}] mongoDBConfiguredObjects initialization: {}",
                        mongoDBConfiguredObjects.getClass( ).getName( ), e );
        }
    }
}