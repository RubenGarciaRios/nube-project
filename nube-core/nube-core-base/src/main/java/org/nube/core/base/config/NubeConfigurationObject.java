/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 19:04
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
     * META_DATA constant.
     * @see NubeObject#META_DATA
     */
    MetaData META_DATA = new MetaData( NubeObjectType.CONFIGURATION );
}
