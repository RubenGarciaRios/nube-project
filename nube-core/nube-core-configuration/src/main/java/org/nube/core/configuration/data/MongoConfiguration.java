/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Collection;

@Configuration
public class MongoConfiguration
        extends AbstractMongoConfiguration
        implements NubeConfigurationObject {
    private final static Logger _LOG = LogManager.getLogger( MongoConfiguration.class );
    private static final long serialVersionUID = 6163887373571549242L;
    // CONSTANTS.
    public static final String DEFAULT_BASE_PACKAGE = "org.nube";

    @Override
    protected Collection< String > getMappingBasePackages( ) {
        _LOG.info( "Adding MongoDB Base Packages..." );
        Collection< String > mappingBasePackages = super.getMappingBasePackages( );
        mappingBasePackages.add( DEFAULT_BASE_PACKAGE );
        _LOG.info( "Added {} MongoDB Base Packages: {}",
                   mappingBasePackages.size( ),
                   mappingBasePackages );
        return mappingBasePackages;
    }
}
