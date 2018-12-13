/*
 *  Developed by Rubén García Ríos
 *  Last modified 13/12/18 18:50
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import org.springframework.beans.factory.config.BeanDefinition;

import javax.validation.constraints.NotNull;

public interface DataProviderFactory {
    @NotNull
    BeanDefinition[ ] getBeanDefinitions( );
}
