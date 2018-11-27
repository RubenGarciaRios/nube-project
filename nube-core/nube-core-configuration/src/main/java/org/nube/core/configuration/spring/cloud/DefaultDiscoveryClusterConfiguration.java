/*
 *  Developed by Rubén García Ríos
 *  Last modified 23/11/18 14:57
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.cloud.cluster.DiscoveryClusterResolver;
import org.nube.core.base.data.property.NubeDiscoveryClusterProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources( {
      @PropertySource( "classpath:nube-configuration.properties" ) } )
@EnableConfigurationProperties( NubeDiscoveryClusterProperties.class )
public class DefaultDiscoveryClusterConfiguration
        implements DiscoveryClusterConfiguration {
    private final static Logger _LOG = LogManager.getLogger( DefaultDiscoveryClusterConfiguration.class );
    private static final long serialVersionUID = 782011079726685921L;
    NubeDiscoveryClusterProperties nubeDiscoveryClusterProperties;

    DefaultDiscoveryClusterConfiguration( NubeDiscoveryClusterProperties nubeDiscoveryClusterProperties ) {
        _LOG.info( " - Initializing [{}] nubeDiscoveryClusterProperties artributte...",
                   nubeDiscoveryClusterProperties.getClass( ).getName( ) );
        this.nubeDiscoveryClusterProperties = nubeDiscoveryClusterProperties;
        _LOG.info( " - [{}] nubeDiscoveryClusterProperties artributte has been initialized successfully with value: {}",
                   nubeDiscoveryClusterProperties.getClass( ).getName( ),
                   nubeDiscoveryClusterProperties );
    }

    @Bean
    public DiscoveryClusterResolver discoveryClusterResolver( ) {
        //new MongoDBDiscoveryClusterResolver();
        return null;
    }
}
