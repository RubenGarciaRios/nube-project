/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 11:56
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.adminmanager;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class AdminManager {
    public static void main( String[] args ) {
        SpringApplication.run( AdminManager.class, args );
    }
}
