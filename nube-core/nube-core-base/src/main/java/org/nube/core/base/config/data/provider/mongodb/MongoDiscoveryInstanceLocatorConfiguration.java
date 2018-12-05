/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 14:17
 *  Copyright (c) 2018 All rights reserved.
 */
/*
package org.nube.core.base.config.data.provider.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
import org.nube.core.base.config.data.provider.mongodb.annotation.ScanMongoRepositories;
import org.nube.core.base.data.property.NubeDiscoveryInstanceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@ScanMongoRepositories(
        basePackages = { "org.nube.core.base.data.provider.cloud.discovery.instance.region.repository" }  )
@PropertySources( {
        @PropertySource( "classpath:nube-configuration.properties" ) } )
@EnableConfigurationProperties( NubeDiscoveryInstanceProperties.class )

public class MongoDiscoveryInstanceLocatorConfiguration
        extends AbstractMongoConfiguration
        implements NubeConfigurationObject {
    private final static Logger _LOG = LogManager.getLogger( MongoDiscoveryInstanceLocatorConfiguration.class );
    private static final long serialVersionUID = 2517993753793119158L;
    // ATTRIBUTES.

    MongoDiscoveryInstanceLocatorConfiguration(
            NubeDiscoveryInstanceProperties nubeDiscoveryInstanceProperties ) {

    }
}
*/