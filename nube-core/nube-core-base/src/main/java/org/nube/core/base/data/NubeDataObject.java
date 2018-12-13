/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 14:55
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;

/**
 * NUBE Data Object.
 * Indicates that any class that extends from it, belongs to the grouping of object type:
 * {@link NubeObjectType#DATA}
 * <p>
 * Data Management and Storage Objects.
 *
 * @author Rubén García Ríos
 */
public abstract class NubeDataObject
        extends NubeObject {
    private static final long serialVersionUID = -4420342538473087944L;

    /**
     * Instantiates a new Nube data object.
     */
    protected NubeDataObject( )
        { super( NubeObjectType.DATA ); }

    /**
     * Instantiates a new Nube data object.
     *
     * @param nubeObjectType the nube object type
     */
    protected NubeDataObject( NubeObjectType nubeObjectType )
        { super( NubeDataObject.objectHierarchyValidation( nubeObjectType ) ); }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    /**
     * Object hierarchy validation.
     * Validates that all child members that extends of this class, have a valid NUBE Object Type based
     * on hierarchy.
     * <p>
     * If child member hasn't a valid NUBE Object type, returns {@link NubeObjectType#UNDEFINED}
     *
     * @param nubeObjectType the nube object type
     * @return the nube object type
     */
    protected static NubeObjectType objectHierarchyValidation( NubeObjectType nubeObjectType ) {
        switch ( nubeObjectType ) {
            case DATA_PROPERTIES:
                return nubeObjectType;
            default:
                return NubeObjectType.UNDEFINED;
        }
    }

    @Override
    public String toString( )
        { return super.toString( ); }
}
