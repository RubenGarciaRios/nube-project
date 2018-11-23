/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 15:07
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.cloud.discovery.instance.zone.domain;

import org.nube.core.base.data.provider.cloud.discovery.instance.zone.DiscoveryInstanceZone;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Objects;

/**
 * MongoDB Discovery Instance Zone.
 * MongoDB Database Object implementation of {@link DiscoveryInstanceZone}.
 *
 * @author Rubén García Ríos
 */
public class MongoDiscoveryInstanceZone
        implements DiscoveryInstanceZone {
    private static final long serialVersionUID = -2634239166769028826L;
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
    // ATTROIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    private String name;
    private String ip;
    private String dns;
    private int discoveryPort;
    private boolean enabled;

    /**
     * Instantiates a new MongoDB discovery instance zone.
     */
    public MongoDiscoveryInstanceZone( ) { }

    /**
     * Instantiates a new MongoDB Discovery Instance Zone.
     *
     * @param name          the name
     * @param ip            the ip
     * @param dns           the dns
     * @param discoveryPort the discovery port
     * @param enabled       the enabled
     */
    public MongoDiscoveryInstanceZone(
            final String name,
            final String ip,
            final String dns,
            final int discoveryPort,
            final boolean enabled ) {
        this.name = name;
        this.ip = ip;
        this.dns = dns;
        this.discoveryPort = discoveryPort;
        this.enabled = enabled;
    }

    @Override
    public String getId( )
        { return id; }
    @Override
    public void setId( final String id )
        { this.id = id; }

    @Override
    public String getName( )
        { return name; }

    @Override
    public void setName( final String name )
        { this.name = name; }

    @Override
    public String getIp( )
        { return ip; }

    @Override
    public void setIp( final String ip )
        { this.ip = ip; }

    @Override
    public String getDns( )
        { return dns; }

    @Override
    public void setDns( final String dns )
        { this.dns = dns; }

    @Override
    public int getDiscoveryPort( )
        { return discoveryPort; }

    @Override
    public void setDiscoveryPort( final int discoveryPort )
        { this.discoveryPort = discoveryPort; }

    @Override
    public boolean isEnabled( )
        { return enabled; }

    @Override
    public void setEnabled( final boolean enabled )
        { this.enabled = enabled; }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
    { return serialVersionUID; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoDiscoveryInstanceZone ) ) return false;
        final MongoDiscoveryInstanceZone that = ( MongoDiscoveryInstanceZone ) o;
        return getDiscoveryPort( ) == that.getDiscoveryPort( ) &&
               isEnabled( ) == that.isEnabled( ) &&
               Objects.equals( getId( ), that.getId( ) ) &&
               Objects.equals( getName( ), that.getName( ) ) &&
               Objects.equals( getIp( ), that.getIp( ) ) &&
               Objects.equals( getDns( ), that.getDns( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getId( ), getName( ), getIp( ), getDns( ), getDiscoveryPort( ), isEnabled( ) ); }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) + "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", dns='" + dns + '\'' +
                ", discoveryPort=" + discoveryPort +
                ", enabled=" + enabled + '}';
    }
}
