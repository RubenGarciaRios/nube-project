/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 13:05
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.cloud.cluster.DiscoveryClusterResolver;
import org.nube.core.base.data.property.NubeDiscoveryInstanceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources( {
      @PropertySource( "classpath:nube-configuration.properties" ) } )
@EnableConfigurationProperties( NubeDiscoveryInstanceProperties.class )
public class DefaultDiscoveryClusterConfiguration
        implements DiscoveryClusterConfiguration {
    private final static Logger _LOG = LogManager.getLogger( DefaultDiscoveryClusterConfiguration.class );
    private static final long serialVersionUID = 782011079726685921L;
    NubeDiscoveryInstanceProperties nubeDiscoveryInstanceProperties;

    DefaultDiscoveryClusterConfiguration( NubeDiscoveryInstanceProperties nubeDiscoveryInstanceProperties ) {
        _LOG.info( " - Initializing [{}] nubeDiscoveryInstanceProperties artributte...",
                   nubeDiscoveryInstanceProperties.getClass( ).getName( ) );
        this.nubeDiscoveryInstanceProperties = nubeDiscoveryInstanceProperties;
        _LOG.info( " - [{}] nubeDiscoveryInstanceProperties artributte has been initialized successfully with value: {}",
                   nubeDiscoveryInstanceProperties.getClass( ).getName( ),
                   nubeDiscoveryInstanceProperties );
    }

    @Bean
    public DiscoveryClusterResolver discoveryClusterResolver( ) {
        //new MongoDBDiscoveryClusterResolver();
        return null;
    }
}
