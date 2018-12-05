/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 13:48
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud.discovery.instance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.property.NubeDiscoveryInstanceProperties;

public class NubePropertiesDiscoveryInstanceLocatorService
        implements DiscoveryInstanceLocatorService {
    private final static Logger _LOG = LogManager.getLogger( NubePropertiesDiscoveryInstanceLocatorService.class );
    private static final long serialVersionUID = -1285834380378524495L;

    public NubePropertiesDiscoveryInstanceLocatorService(
            NubeDiscoveryInstanceProperties nubeDiscoveryInstanceProperties ) {
    }
}