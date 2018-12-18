/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 18:24
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data;

import org.nube.core.base.data.property.NubeDataSourceProperties;
import org.nube.core.base.data.provider.DataProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources( { @PropertySource( "classpath:nube-datasource.properties" ) } )
@EnableConfigurationProperties( NubeDataSourceProperties.class )
public interface DataSourceConfiguration< T extends DataProvider > {
    @Bean
    T[ ] addAllDataProviders( NubeDataSourceProperties dataSourceProperties, ApplicationContext applicationContext );
}
