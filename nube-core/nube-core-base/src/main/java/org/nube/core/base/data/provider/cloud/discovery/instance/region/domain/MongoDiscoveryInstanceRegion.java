/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 12:30
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
 * Discovery Instance Region for management by MongoDB DataProviderType.
 * <p>
 * MongoDB referenced document name: {@code discoveryInstanceRegions}
 *
 * @author Rubén García Ríos
 */
@Document( collection = "discoveryInstanceRegions" )
public class MongoDiscoveryInstanceRegion
        extends DiscoveryInstanceRegion {
    private static final long serialVersionUID = 6756850645093430986L;
    // CONSTANTS.
    /**
     * NAME constant, indicates the name of field in MongoDB document.
     */
    public static final String NAME = "name";
    /**
     * ZONES constant, indicates the name of field in MongoDB document.
     */
    public static final String ZONES = "zones";
    /**
     * ENABLED constant, indicates the name of field in MongoDB document.
     */
    public static final String ENABLED = "enabled";
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
