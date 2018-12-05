/*
 *  Developed by Rubén García Ríos
 *  Last modified 4/12/18 22:11
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.cloud.discovery.instance.region.domain;

import org.nube.core.base.data.provider.cloud.discovery.instance.region.DiscoveryInstanceRegion;
import org.nube.core.base.data.provider.cloud.discovery.instance.zone.DiscoveryInstanceZone;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Objects;

/**
 * MongoDB Discovery Instance Region.
 * MongoDB Database Object implementation of {@link DiscoveryInstanceRegion}.
 *
 * @author Rubén García Ríos
 */
@Document( collection = "discoveryInstanceRegions" )
public class MongoDiscoveryInstanceRegion
        extends DiscoveryInstanceRegion {
    private static final long serialVersionUID = 6756850645093430986L;
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    /**
     * Instantiates a new Mongo discovery instance region.
     */
    public MongoDiscoveryInstanceRegion( ) { }

    /**
     * Instantiates a new Mongo discovery instance region.
     *
     * @param id      the id
     * @param name    the name
     * @param zones   the zones
     * @param enabled the enabled
     */
    public MongoDiscoveryInstanceRegion(
            final String id,
            final String name,
            final Collection< DiscoveryInstanceZone > zones,
            final boolean enabled ) {
        this.id = id;
        super.setName( name );
        super.setZones( zones );
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
        if ( !( o instanceof MongoDiscoveryInstanceRegion ) ) return false;
        final MongoDiscoveryInstanceRegion that = ( MongoDiscoveryInstanceRegion ) o;
        return isEnabled( ) == that.isEnabled( ) &&
               Objects.equals( getId( ), that.getId( ) ) &&
               Objects.equals( getName( ), that.getName( ) ) &&
               Objects.equals( getZones( ), that.getZones( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getId( ), getName( ), getZones( ), isEnabled( ) ); }
}
