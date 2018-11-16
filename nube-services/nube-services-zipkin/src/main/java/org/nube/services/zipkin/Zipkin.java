/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.zipkin;

import org.nube.core.configuration.annotation.NubeService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@NubeService
@EnableZipkinServer
public class Zipkin {
    public static void main( String[ ] args ) {
        new SpringApplicationBuilder( Zipkin.class ).web( WebApplicationType.SERVLET ).run( args );
    }
}