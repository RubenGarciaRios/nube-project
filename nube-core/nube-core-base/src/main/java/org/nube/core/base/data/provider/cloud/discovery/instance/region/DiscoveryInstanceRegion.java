/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 15:09
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.cloud.discovery.instance.region;

import org.nube.core.base.data.NubeDataObject;
import org.nube.core.base.data.provider.cloud.discovery.instance.zone.DiscoveryInstanceZone;

import java.util.Collection;

/**
 * Discovery instance region.
 *
 * @author Rubén García Ríos
 */
public interface DiscoveryInstanceRegion
        extends NubeDataObject {
    /**
     * Gets id.
     *
     * @return the id
     */
    String getId( );

    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId( String id );

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName( );

    /**
     * Sets name.
     *
     * @param name the name
     */
    void setName( String name );

    /**
     * Gets Zones.
     * Lis of available Zones in Region.
     *
     * @return the zones
     */
    Collection< DiscoveryInstanceZone > getZones( );

    /**
     * Sets Zones.
     *
     * @param zones the zones
     */
    void setZones( Collection< DiscoveryInstanceZone > zones );

    /**
     * Is enabled boolean.
     * Indicates if Region is enabled or not
     *
     * @return the boolean
     */
    boolean isEnabled( );

    /**
     * Sets enabled.
     * Enable/disable Region.
     *
     * @param enabled the enabled
     */
    void setEnabled( boolean enabled );
}
