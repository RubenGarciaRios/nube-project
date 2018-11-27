/*
 *  Developed by Rubén García Ríos
 *  Last modified 27/11/18 23:58
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration
        implements NubeConfigurationObject {
    private final static Logger _LOG = LogManager.getLogger( MongoConfiguration.class );
    private static final long serialVersionUID = 6163887373571549242L;
    // CONSTANTS.
    public static final String DEFAULT_BASE_PACKAGE = "org.nube";

}
