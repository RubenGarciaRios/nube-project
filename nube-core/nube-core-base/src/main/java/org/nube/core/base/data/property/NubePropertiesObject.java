/*
 *  Developed by Rubén García Ríos
 *  Last modified 4/12/18 23:09
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.property;

import org.nube.core.base.NubeObjectType;
import org.nube.core.base.data.NubeDataObject;

/**
 * NUBE Data Object.
 * Indicates that any class that extends from it, belongs to the grouping of object type:
 * {@link NubeObjectType#DATA_PROPERTIES}
 * <p>
 * Properties data extracted from files that has .properties/.yml extension.
 *
 * @author Rubén García Ríos
 */
public abstract class NubePropertiesObject
        extends NubeDataObject {
    private static final long serialVersionUID = 173325964007974458L;

    /**
     * Instantiates a new Nube properties object.
     */
    protected NubePropertiesObject( ) {
        super( NubeObjectType.DATA_PROPERTIES );
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }
}
