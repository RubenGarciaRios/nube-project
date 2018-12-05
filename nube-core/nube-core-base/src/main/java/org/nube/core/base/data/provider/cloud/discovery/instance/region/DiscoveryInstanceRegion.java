/*
 *  Developed by Rubén García Ríos
 *  Last modified 4/12/18 22:17
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
public abstract class DiscoveryInstanceRegion
        extends NubeDataObject {
    private static final long serialVersionUID = -1084133934279905049L;
    // CONSTANTS.
    /**
     * NAME constant.
     */
    public static final String NAME = "name";
    /**
     * ZONES constant.
     */
    public static final String ZONES = "zones";
    /**
     * ENABLED constant.
     */
    public static final String ENABLED = "enabled";
    // ATTRIBUTES.
    private String id;
    private String name;
    private Collection< DiscoveryInstanceZone > zones;
    private boolean enabled;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId( )
        { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId( final String id )
        { this.id = id; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName( )
        { return name; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName( final String name )
        { this.name = name; }

    /**
     * Gets Zones.
     * Lis of available Zones in Region.
     *
     * @return the zones
     */
    public Collection< DiscoveryInstanceZone > getZones( )
        { return zones; }

    /**
     * Sets Zones.
     *
     * @param zones the zones
     */
    public void setZones( final Collection< DiscoveryInstanceZone > zones )
        { this.zones = zones; }

    /**
     * Is enabled boolean.
     * Indicates if Region is enabled or not
     *
     * @return the boolean
     */
    public boolean isEnabled( )
        { return enabled; }

    /**
     * Enable Region.
     *
     */
    public void enabled( )
        { this.enabled = true; }
    /**
     * Disable Region.
     *
     */
    public void disable( )
        { this.enabled = false; }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }
}
