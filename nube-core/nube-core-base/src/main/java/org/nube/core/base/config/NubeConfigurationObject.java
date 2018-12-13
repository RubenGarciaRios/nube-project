/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 14:51
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * NUBE Configuration Object.
 * Indicates that any class that extends from it, belongs to the grouping of object type:
 * {@link NubeObjectType#CONFIGURATION}
 * <p>
 * Environment Configuration related objects. All child members innerits {@code @Confiuration}
 * spring annotation.
 *
 * @author Rubén García Ríos
 */
@Configuration
@Order( NubeConfigurationObject.DEFAULT_CONFIGURATION_ORDER_PRECEDENCE )
public abstract class NubeConfigurationObject
        extends NubeObject {
    private static final long serialVersionUID = 7951891263881266155L;
    public static final int DEFAULT_CONFIGURATION_ORDER_PRECEDENCE = -100;

    /**
     * Instantiates a new Nube data object.
     */
    protected NubeConfigurationObject( )
        { super( NubeObjectType.DATA ); }

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
