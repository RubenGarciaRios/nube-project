/*
 *  Developed by Rubén García Ríos
 *  Last modified 4/12/18 23:19
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.security;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;

/**
 * NUBE Data Object.
 * Indicates that any class that extends from it, belongs to the grouping of object type:
 * {@link NubeObjectType#SECURITY}
 * <p>
 * Security Management, improvements and any realated object with security in general.
 *
 * @author Rubén García Ríos
 */
public abstract class NubeSecurityObject
        extends NubeObject {
    private static final long serialVersionUID = 2529095755340823259L;

    /**
     * Instantiates a new Nube security object.
     */
    protected NubeSecurityObject( )
        { super( NubeObjectType.SECURITY ); }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }
}
