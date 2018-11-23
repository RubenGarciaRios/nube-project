/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * NUBE Service.
 * All applications that uses this annotation, are automatically preconfigured
 * to be part of NUBE Environment System.
 *
 * @author Rubén García Ríos.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
@Documented
@Inherited
@NubeConfiguration
public @interface NubeService {
    /**
     * Include specific auto-configuration classes such that they will be applied.
     * @return the classes to include
     */
    @AliasFor( annotation = NubeConfiguration.class )
    Class< ? >[ ] include( ) default { };

    /**
     * Include specific auto-configuration class names such that they will be applied.
     * @return the class names to include
     */
    @AliasFor( annotation = NubeConfiguration.class )
    String[ ] includeName( ) default { };

    /**
     * Exclude specific auto-configuration classes such that they will never be applied.
     * @return the classes to exclude
     */
    @AliasFor( annotation = NubeConfiguration.class )
    Class< ? >[ ] exclude( ) default { };

    /**
     * Exclude specific auto-configuration class names such that they will never be
     * applied.
     * @return the class names to exclude
     */
    @AliasFor( annotation = NubeConfiguration.class )
    String[ ] excludeName( ) default { };
}
