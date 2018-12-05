/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:49
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
        extends DiscoveryInstanceZone {
    private static final long serialVersionUID = -2634239166769028826L;
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
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
        super.setName( name );
        super.setIp( ip );
        super.setDns( dns );
        super.setDiscoveryPort( discoveryPort );
        if ( enabled )
            super.enabled( );
        else
            super.disable( );
    }

    @Override
    public String getId( )
        { return id; }
    @Override
    public void setId( final String id )
        { this.id = id; }

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

}
