/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import com.netflix.discovery.DiscoveryClient;
import org.nube.core.base.config.NubeConfigurationObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.net.ssl.SSLContext;

@ConditionalOnClass( DiscoveryClient.class )
@EnableDiscoveryClient
@EnableEurekaClient
public interface DiscoveryClientConfiguration
        extends NubeConfigurationObject {
    @Bean( "discoveryClientOptionalArgs" )
    @DependsOn( { "sslContext" } )
    @ConditionalOnBean( SSLContext.class )
    DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs(
            SSLContext sslContext );
}
