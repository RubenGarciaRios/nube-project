/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud.discovery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.property.NubeDiscoveryClusterProperties;

public class NubePropertiesDiscoveryInstanceInfo
        implements DiscoveryInstanceInfo {
    private final static Logger _LOG = LogManager.getLogger( NubePropertiesDiscoveryInstanceInfo.class );
    private static final long serialVersionUID = -1285834380378524495L;

    public NubePropertiesDiscoveryInstanceInfo(
            NubeDiscoveryClusterProperties nubeDiscoveryClusterProperties ) {
    }
}