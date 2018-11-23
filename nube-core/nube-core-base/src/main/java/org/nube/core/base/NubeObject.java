/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.utils.RandomStringGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * NUBE Object.
 * All NUBE related objects implements this interface.
 *
 * @author Rubén García Ríos.
 */
public interface NubeObject
        extends Serializable,
        Cloneable {
    /**
     * INFO constant.
     * Have all information of the NUBE Objects instantiation.
     */
    Info INFO = new Info( NubeObjectType.BASIC );
    /**
     * ID constant.
     * Unique id, useful to check multiple instances of the same object.
     */
    String ID = RandomStringGenerator.builder( )
            .length( 7 )
            .build( )
            .generate( );

    /**
     * Info.
     * Used to provide usefull information of NUBE Objects instantiation.
     *
     * @author Rubén García Ríos.
     */
    class Info {
        private final static Logger _LOG = LogManager.getLogger( Info.class );
        // CONSTANTS.
        /**
         * The Type.
         */
        public final NubeObjectType TYPE;
        /**
         * The Creation date.
         */
        public final Date CREATION_DATE = new Date( );

        /**
         * Instantiates a new Info.
         *
         * @param nubeObjectType the nube object type
         */
        public Info( NubeObjectType nubeObjectType ) {
            TYPE = nubeObjectType;
            _LOG.debug( "Object info of the new instance of [{}]: {}",
                        super.getClass().getName( ), this.toString( ) );
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof Info ) ) return false;
            final Info info = ( Info ) o;
            return TYPE == info.TYPE &&
                    Objects.equals( CREATION_DATE, info.CREATION_DATE );
        }

        @Override
        public int hashCode( ) {
            return Objects.hash( TYPE, CREATION_DATE );
        }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) + "{" +
                    "TYPE=" + TYPE +
                    ", CREATION_DATE=" + CREATION_DATE + '}';
        }
    }
}
