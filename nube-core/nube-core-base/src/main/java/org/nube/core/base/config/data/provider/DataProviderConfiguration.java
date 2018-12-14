/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 14:10
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
import org.nube.core.base.data.BasicServerAddress;
import org.nube.core.base.data.provider.DataProviderFactory;
import org.nube.core.base.data.provider.mongodb.MongoDataProviderFactory;

public abstract class DataProviderConfiguration
        extends NubeConfigurationObject {
    private final static Logger _LOG = LogManager.getLogger( DataProviderConfiguration.class );

    public static void main( String args[ ] ) {
        DataProviderFactory providerFactory =
                new MongoDataProviderFactory(
                        "test",
                        "test",
                        null,
                        null,
                        new BasicServerAddress( ) );
        _LOG.debug( providerFactory );
    }
}
