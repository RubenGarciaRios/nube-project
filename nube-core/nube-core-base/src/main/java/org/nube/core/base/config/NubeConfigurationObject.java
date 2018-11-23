/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config;

import org.nube.core.base.NubeObject;
import org.nube.core.base.NubeObjectType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order( NubeConfigurationObject.DEFAULT_CONFIGURATION_ORDER_PRECEDENCE )
public interface NubeConfigurationObject
        extends NubeObject {
    int DEFAULT_CONFIGURATION_ORDER_PRECEDENCE = -100;
    /**
     * INFO constant.
     * @see NubeObject#INFO
     */
    Info INFO = new Info( NubeObjectType.CONFIGURATION );
}
