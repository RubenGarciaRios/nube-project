/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.security;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;

/**
 * The interface Core security object.
 *
 * @see NubeObject
 */
public interface NubeSecurityObject
        extends NubeObject {
    /**
     * INFO constant.
     * @see NubeObject#INFO
     */
    Info INFO = new Info( NubeObjectType.SECURITY );
}
