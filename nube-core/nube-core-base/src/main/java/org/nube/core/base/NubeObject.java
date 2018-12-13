/*
 *  Developed by Rubén García Ríos
 *  Last modified 13/12/18 18:33
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.utils.RandomStringGenerator;
import org.nube.core.base.utils.StringParser;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * NUBE Object.
 * All NUBE related objects implements this interface.
 *
 * @author Rubén García Ríos.
 */
public abstract class NubeObject
        implements Serializable,
        Cloneable {
    private static final long serialVersionUID = -1680093483289080721L;
    /**
     * META_DATA constant.
     * Have all information of the NUBE Objects instantiation.
     */
    public final MetaData META_DATA;

    /**
     * Instantiates a new Nube object.
     */
    protected NubeObject( )
        { META_DATA = new MetaData( NubeObjectType.BASIC ); }

    /**
     * Instantiates a new Nube object.
     *
     * @param nubeObjectType the nube object type
     */
    protected NubeObject( NubeObjectType nubeObjectType )
        { META_DATA = new MetaData( nubeObjectType ); }

    /**
     * MetaData.
     * Used to provide usefull information of NUBE Objects instantiation.
     *
     * @author Rubén García Ríos.
     */
    public static class MetaData {
        private final static Logger _LOG = LogManager.getLogger( MetaData.class );
        // CONSTANTS.
        /**
         * ID constant.
         * Unique id, useful to check multiple instances of the same object.
         */
        public final String ID = RandomStringGenerator.builder( )
                .length( 15 )
                .build( )
                .generate( );
        /**
         * TYPE constant.
         */
        public final NubeObjectType TYPE;
        /**
         * CREATION_DATE constant.
         */
        public final Date CREATION_DATE = new Date( );

        /**
         * Instantiates a new MetaData.
         *
         * @param nubeObjectType the nube object type
         */
        public MetaData( NubeObjectType nubeObjectType ) {
            TYPE = nubeObjectType;
            _LOG.debug( "Object info of the new instance of [{}]: {}",
                        super.getClass().getName( ), this.toString( ) );
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof MetaData ) ) return false;
            final MetaData metaData = ( MetaData ) o;
            return TYPE == metaData.TYPE &&
                    Objects.equals( CREATION_DATE, metaData.CREATION_DATE );
        }

        @Override
        public int hashCode( ) {
            return Objects.hash( TYPE, CREATION_DATE );
        }

        @Override
        public String toString( )
            { return this.getClass( ).getName( ) + "{ID='" + ID + "', TYPE=" + TYPE + ", CREATION_DATE=" + CREATION_DATE + '}'; }
    }

    /**
     * Object hierarchy validation.
     *
     * @param nubeObjectType the nube object type
     * @return the nube object type
     */
    protected static NubeObjectType objectHierarchyValidation( NubeObjectType nubeObjectType )
        { return nubeObjectType; }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    @Override
    public String toString( ) {
        return StringParser.forObject( this )
                .beautify( )
                    .withLineBreaks( )
                    .withSpaceIndent( )
                    .withIndentSizeOf( 4 )
                    .and( )
                .useStatements( )
                    .jsonLikeStyle( )
                    .and( )
                .doRecursivityWithInstancesOf( MetaData.class )
                .apply( ).parse( );
    }
}
