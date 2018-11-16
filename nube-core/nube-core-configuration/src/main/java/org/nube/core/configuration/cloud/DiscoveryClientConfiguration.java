/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.cloud;

import com.netflix.discovery.DiscoveryClient;
import org.nube.core.base.CoreConfigurationObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.net.ssl.SSLContext;

public interface DiscoveryClientConfiguration
        extends CoreConfigurationObject {
    @Bean( "discoveryClientOptionalArgs" )
    @DependsOn( { "sslContext" } )
    @ConditionalOnBean( SSLContext.class )
    DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs(
            SSLContext sslContext );
}
