/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.cloud.discovery.DiscoveryInstanceInfo;
import org.nube.core.base.data.property.NubeDiscoveryClusterProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources( {
      @PropertySource( "classpath:nube-configuration.properties" ) } )
@EnableConfigurationProperties( NubeDiscoveryClusterProperties.class )
public class DefaultDiscoveryInstanceConfiguration
        implements DiscoveryInstanceConfiguration {
    private final static Logger _LOG = LogManager.getLogger( DefaultDiscoveryInstanceConfiguration.class );
    private static final long serialVersionUID = 782011079726685921L;
    NubeDiscoveryClusterProperties nubeDiscoveryClusterProperties;

    DefaultDiscoveryInstanceConfiguration( NubeDiscoveryClusterProperties nubeDiscoveryClusterProperties ) {
        _LOG.info( " - Initializing [{}] nubeDiscoveryClusterProperties artributte...",
                   nubeDiscoveryClusterProperties.getClass( ).getName( ) );
        this.nubeDiscoveryClusterProperties = nubeDiscoveryClusterProperties;
        _LOG.info( " - [{}] nubeDiscoveryClusterProperties artributte has been initialized successfully with value: {}",
                   nubeDiscoveryClusterProperties.getClass( ).getName( ),
                   nubeDiscoveryClusterProperties );
    }

    @Bean
    public DiscoveryInstanceInfo discoveryClusterResolver( ) {
        //new MongoDiscoveryInstanceInfo();
        return null;
    }
}
