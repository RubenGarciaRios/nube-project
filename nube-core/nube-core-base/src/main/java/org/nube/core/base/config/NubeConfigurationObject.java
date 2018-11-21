/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.Serializable;

@Configuration
@Order( NubeConfigurationObject.DEFAULT_CONFIGURATION_ORDER_PRECEDENCE )
public interface NubeConfigurationObject
        extends Serializable, Cloneable {
    int DEFAULT_CONFIGURATION_ORDER_PRECEDENCE = -100;
}
