/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.cloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

@Order( 0 )
@Configuration
@Import( {
        DefaultDiscoveryClientConfiguration.class,
        DefaultConfigClientConfiguration.class } )
public class DefaultCloudConfiguration
        implements CloudConfiguration {
    private static final Logger _LOG = LogManager.getLogger( DefaultConfigClientConfiguration.class );
    private static final long serialVersionUID = 1752284393372226100L;

    @Override
    @PostConstruct
    public void initializer( ) {
        _LOG.info( "\n=== END OF CLOUD CONFIGURATION ===" );
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }
}
