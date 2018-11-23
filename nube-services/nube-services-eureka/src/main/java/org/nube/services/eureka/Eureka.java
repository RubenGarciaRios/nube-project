/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.eureka;

import org.nube.core.configuration.annotation.NubeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@NubeService
public class Eureka {
    public static void main( String[] args ) {
        SpringApplication.run( Eureka.class, args );
    }
}
