/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 3:00
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.spring.cloud;

import org.springframework.context.annotation.Import;

@Import( {
        DefaultDiscoveryInstanceConfiguration.class,
        DefaultDiscoveryClientConfiguration.class,
        DefaultConfigClientConfiguration.class } )
public class DefaultSpringCloudConfiguration
        implements SpringCloudConfiguration {
    private static final long serialVersionUID = 1752284393372226100L;

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }
}
