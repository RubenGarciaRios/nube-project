/*
 *  Developed by Rubén García Ríos
 *  Last modified 4/12/18 22:16
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.cloud.discovery.instance.zone;

import org.nube.core.base.data.NubeDataObject;

/**
 * Discovery instance zone.
 *
 * @author Rubén García Ríos
 */
public abstract class DiscoveryInstanceZone
        extends NubeDataObject {
    private static final long serialVersionUID = -7214588509682807999L;
    // CONSTANTS.
    /**
     * NAME constant.
     */
    public static final String NAME = "name";
    /**
     * IP constant.
     */
    public static final String IP = "ip";
    /**
     * DNS constant.
     */
    public static final String DNS = "dns";
    /**
     * DISCOVERY_PORT constant.
     */
    public static final String DISCOVERY_PORT = "discoveryPort";
    /**
     * ENABLED constant.
     */
    public static final String ENABLED = "enabled";
    // ATTRIBUTES.
    private String id;
    private String name;
    private String ip;
    private String dns;
    private int discoveryPort;
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
     * Gets discovery instance ip address.
     *
     * @return the ip
     */
    public String getIp( )
        { return ip; }

    /**
     * Sets discovery instance ip address.
     *
     * @param ip the ip
     */
    public void setIp( final String ip )
        { this.ip = ip; }

    /**
     * Gets discovery instance dns address.
     *
     * @return the dns
     */
    public String getDns( )
        { return dns; }

    /**
     * Sets discovery instance dns address.
     *
     * @param dns the dns
     */
    public void setDns( final String dns )
        { this.dns = dns; }

    /**
     * Gets discovery instance port.
     *
     * @return the discovery port
     */
    public int getDiscoveryPort( )
        { return discoveryPort; }

    /**
     * Sets discovery instance port.
     *
     * @param discoveryPort the discovery port
     */
    public void setDiscoveryPort( final int discoveryPort )
        { this.discoveryPort = discoveryPort; }

    /**
     * Is enabled boolean.
     * Indicates if Zone is enabled or not.
     *
     * @return the boolean
     */
    public boolean isEnabled( )
        { return enabled; }

    /**
     * Enable Zone.
     *
     */
    public void enabled( )
        { this.enabled = true; }
    /**
     * Disable Zone.
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
