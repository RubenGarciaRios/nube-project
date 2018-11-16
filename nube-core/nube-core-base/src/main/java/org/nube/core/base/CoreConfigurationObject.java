/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base;

import java.io.Serializable;

public interface CoreConfigurationObject
        extends Serializable, Cloneable {
    void initializer( );
}
