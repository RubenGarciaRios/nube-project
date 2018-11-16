/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.configurator;

import org.nube.core.configuration.annotation.NubeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NubeService
public class Configurator {
    public static void main( String[ ] args ) {
        SpringApplication.run(Configurator.class, args);
    }
}