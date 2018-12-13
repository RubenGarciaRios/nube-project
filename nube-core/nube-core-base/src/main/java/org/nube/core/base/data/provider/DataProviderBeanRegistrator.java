/*
 *  Developed by Rubén García Ríos
 *  Last modified 13/12/18 19:03
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import org.nube.core.base.data.NubeDataObject;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;

public class DataProviderBeanRegistrator
        extends NubeDataObject {
    private final DataProviderFactory dataProviderFactory;

    public DataProviderBeanRegistrator(
            final DataProviderFactory dataProviderFactory ) {
        this.dataProviderFactory = dataProviderFactory;
    }

    public void register( final ApplicationContext applicationContext, final String providerID ) {
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory( );
        //beanFactory.initializeBean(  )
        BeanDefinitionRegistry beanDefinitionRegistry = ( BeanDefinitionRegistry ) beanFactory;
        BeanDefinition[ ] beanDefinitions = dataProviderFactory.getBeanDefinitions( );

        for ( BeanDefinition beanDefinition : beanDefinitions ) {
            String beanClassName = beanDefinition.getBeanClassName( );
            String beanID = providerID +
                            Character.toLowerCase( beanClassName.charAt( 0 ) ) +
                            beanClassName.substring( 1 );
            beanDefinitionRegistry.registerBeanDefinition( beanID, beanDefinition );
        }
    }
}
