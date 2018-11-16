/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 11:56
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.configurator.config;

import org.springframework.cloud.config.monitor.GithubPropertyPathNotificationExtractor;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigServer
public class ConfiguratorConfiguration {
    @Bean
    public GithubPropertyPathNotificationExtractor githubPropertyPathNotificationExtractor( ) {
        return new GithubPropertyPathNotificationExtractor( );
    }
}
