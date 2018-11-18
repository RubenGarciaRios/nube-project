/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config;

import java.io.Serializable;

public interface NubeConfigurationObject
        extends Serializable, Cloneable {
    void initializer( );
}
