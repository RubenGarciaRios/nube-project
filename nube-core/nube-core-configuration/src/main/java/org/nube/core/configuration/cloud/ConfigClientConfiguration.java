/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.cloud;

import org.nube.core.base.CoreConfigurationObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

public interface ConfigClientConfiguration
        extends CoreConfigurationObject {
    @Bean( "configClientProperties" )
    ConfigClientProperties configClientProperties( Environment environment );

    @Bean( "configServicePropertySourceLocator" )
    @DependsOn( { "restTemplate" } )
    @ConditionalOnBean( RestTemplate.class )
    ConfigServicePropertySourceLocator configServicePropertySourceLocator(
            RestTemplate restTemplate, ConfigClientProperties configClientProperties );
}
