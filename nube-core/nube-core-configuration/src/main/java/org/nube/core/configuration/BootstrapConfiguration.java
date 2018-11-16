/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.configuration.cloud.DefaultCloudConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;

/**
 * CORE - Bootstrap configuration.
 * Configure bootstrapping of all services.
 *
 * @author Rubén García Ríos
 */
@Order( 0 )
@Configuration
@Import( { DefaultCloudConfiguration.class } )
@org.springframework.cloud.bootstrap.BootstrapConfiguration
public class BootstrapConfiguration {
    private static final Logger _LOG = LogManager.getLogger( BootstrapConfiguration.class );
    /**
     * Spring Enviroment.
     */
    @Autowired
    private Environment environment;

    /**
     * CORE - Boostrap Configuration Initializer process.
     **/
    @PostConstruct
    public void initializer( ) {
        _LOG.info(
                "\n############################" +
                "\n### CORE BOOTSTRAP SETUP ###" +
                "\n############################" );
        _LOG.info( "- Embedded Servlet Container port is {}", System.getProperty( "server.port" ) );
        // If port is "0" (Random port) set a random free port for Embedded Servlet Container.
        if ( "0".equalsIgnoreCase( System.getenv( "SERVER_PORT" ) ) ||
                "0".equalsIgnoreCase( System.getenv( "server.port" ) ) ||
                "0".equalsIgnoreCase( System.getProperty( "SERVER_PORT" )  ) ||
                "0".equalsIgnoreCase( System.getProperty( "server.port" )  ) ||
                "0".equalsIgnoreCase( this.environment.getProperty( "SERVER_PORT" ) ) ||
                "0".equalsIgnoreCase( this.environment.getProperty( "server.port" ) ) ) {
            System.setProperty( "server.port", ( new Integer( getFreePort( ) ) ).toString( ) );
            _LOG.info( "- Embedded Servlet Container port is assigned to free port {}", System.getProperty( "server.port" ) );
        }
    }

    private int getFreePort( ) {
        int port = 0;
        try { port = SocketUtils.findAvailableTcpPort( ); } catch ( IllegalStateException e ) {
            _LOG.error( "NO PORTS AVAILABLE FOR APPLICATION START UP" );
        }
        return port;
    }
}