/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 15:08
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.cloud.discovery.instance.zone;

import org.nube.core.base.data.NubeDataObject;

/**
 * Discovery instance zone.
 *
 * @author Rubén García Ríos
 */
public interface DiscoveryInstanceZone
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
     * Gets discovery instance ip address.
     *
     * @return the ip
     */
    String getIp( );

    /**
     * Sets discovery instance ip address.
     *
     * @param ip the ip
     */
    void setIp( String ip );

    /**
     * Gets discovery instance dns address.
     *
     * @return the dns
     */
    String getDns( );

    /**
     * Sets discovery instance dns address.
     *
     * @param dns the dns
     */
    void setDns( String dns );

    /**
     * Gets discovery instance port.
     *
     * @return the discovery port
     */
    int getDiscoveryPort( );

    /**
     * Sets discovery instance port.
     *
     * @param discoveryPort the discovery port
     */
    void setDiscoveryPort( int discoveryPort );

    /**
     * Is enabled boolean.
     * Indicates if Zone is enabled or not.
     *
     * @return the boolean
     */
    boolean isEnabled( );

    /**
     * Sets enabled.
     * Enable/disable Zone.
     *
     * @param enabled the enabled
     */
    void setEnabled( boolean enabled );
}
