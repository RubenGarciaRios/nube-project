/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 19:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.cloud;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;

public interface NubeCloudObject
        extends NubeObject {
    /**
     * META_DATA constant.
     * @see NubeObject#META_DATA
     */
    MetaData META_DATA = new MetaData( NubeObjectType.CLOUD );
}
