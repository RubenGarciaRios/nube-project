/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 14:54
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;


public abstract class NubeCloudObject
        extends NubeObject {
    private static final long serialVersionUID = -4923352732777860009L;

    /**
     * Instantiates a new Nube data object.
     */
    protected NubeCloudObject( )
        { super( NubeObjectType.CLOUD ); }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }
    @Override
    public String toString( )
        { return super.toString( ); }
}
