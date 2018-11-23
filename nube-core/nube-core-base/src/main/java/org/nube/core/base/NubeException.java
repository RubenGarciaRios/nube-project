/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base;

public class NubeException
        extends RuntimeException
        implements NubeObject {
    private static final long serialVersionUID = 7120063804722236297L;

    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }
}
