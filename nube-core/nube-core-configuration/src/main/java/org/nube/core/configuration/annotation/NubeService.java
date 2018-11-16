/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.annotation;

import org.nube.core.configuration.ConfigurationImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Rubén García Ríos.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
@Documented
@Inherited
@Import( ConfigurationImportSelector.class )
public @interface NubeService {
    /**
     * Include specific auto-configuration classes such that they will be applied.
     * @return the classes to include
     */
    Class< ? >[ ] include( ) default { };

    /**
     * Include specific auto-configuration class names such that they will be applied.
     * @return the class names to include
     */
    String[ ] includeName( ) default { };

    /**
     * Exclude specific auto-configuration classes such that they will never be applied.
     * @return the classes to exclude
     */
    Class< ? >[ ] exclude( ) default { };

    /**
     * Exclude specific auto-configuration class names such that they will never be
     * applied.
     * @return the class names to exclude
     */
    String[ ] excludeName( ) default { };
}
