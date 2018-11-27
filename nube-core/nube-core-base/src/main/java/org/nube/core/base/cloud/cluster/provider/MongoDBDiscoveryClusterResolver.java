/*
 *  Developed by Rubén García Ríos
 *  Last modified 23/11/18 14:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud.cluster.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.cloud.cluster.DiscoveryClusterResolver;
import org.nube.core.base.data.provider.mongodb.MongoDBConfigurationAdapter;

public class MongoDBDiscoveryClusterResolver
        implements DiscoveryClusterResolver,
        MongoDBConfigurationAdapter {
    private final static Logger _LOG = LogManager.getLogger( MongoDBDiscoveryClusterResolver.class );
    private static final long serialVersionUID = -5521827949876602770L;
    private MongoDBConfiguredObjects mongoDBConfiguredObjects;

    public MongoDBDiscoveryClusterResolver(
            String host,
            int port,
            String database,
            String username,
            String password ) {
        try {
            _LOG.debug( "Building [{}] object", mongoDBConfiguredObjects.getClass( ).getName( ) );
            this.mongoDBConfiguredObjects = MongoDBConfiguredObjects.Builder.build(
                new ServerAddress( host, port ),
                new Source( database, username, password ) );
            _LOG.debug( "[{}] object has been built successfully: {}",
                        mongoDBConfiguredObjects.getClass( ).getName( ),
                        mongoDBConfiguredObjects );
        } catch ( Exception e ) {
            _LOG.error( "An error has occurred during [{}] mongoDBConfiguredObjects initialization: {}",
                        mongoDBConfiguredObjects.getClass( ).getName( ), e );
        }
    }
}