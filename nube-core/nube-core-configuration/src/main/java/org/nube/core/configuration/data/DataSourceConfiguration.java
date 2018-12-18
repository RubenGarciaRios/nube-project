/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 18:24
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
import org.nube.core.base.data.property.NubeDataSourceProperties;
import org.nube.core.base.data.provider.DataProvider;
import org.nube.core.base.data.provider.DataProviderFactory;
import org.nube.core.base.data.provider.mongodb.MongoDataProviderFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.HashMap;
import java.util.Map;

@PropertySources( { @PropertySource( "classpath:nube-datasource.properties" ) } )
@EnableConfigurationProperties( NubeDataSourceProperties.class )
public class DataSourceConfiguration
        extends NubeConfigurationObject {
    private final static Logger _LOG = LogManager.getLogger( DataSourceConfiguration.class );
    private static final long serialVersionUID = -4090826256920922305L;

    @Bean( "dataProviders" )
    public DataProvider[ ] dataProviders(
            NubeDataSourceProperties dataSourceProperties,
            ApplicationContext applicationContext ) {
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    NubeDataSourceProperties.class.getName( ), dataSourceProperties );
        NubeDataSourceProperties.DataSource[ ] dataSources = dataSourceProperties.getDataSources( );
        Map< String, DataProvider > dataProviders = new HashMap< >( );
        for ( NubeDataSourceProperties.DataSource dataSource : dataSources ) {
            DataProviderFactory providerFactory = null;
            switch ( dataSource.getDataProviderType( ) ) {
                case MONGODB:
                    providerFactory =
                            new MongoDataProviderFactory(
                                dataSource.getId( ),
                                dataSource.getDatabase( ),
                                dataSource.getUsername( ),
                                dataSource.getPassword( ),
                                dataSource.getServerAddresses( ) );
                    break;
                default:
                    _LOG.warn( "Datasource of type {} with id {} don't have any implementation of {]",
                               dataSource.getDataProviderType( ),
                               dataSource.getId( ),
                               DataProvider.class );
            }
            if ( providerFactory != null ) {
                if ( dataProviders.containsKey( dataSource.getId( ) ) )
                    throw new RuntimeException( "Datasource with id '" + dataSource.getId( ) + "'already exists." );
                DataProvider dataProvider = providerFactory.getDataProvider( );
                dataProvider.register( applicationContext );
                dataProviders.put( dataSource.getId( ), dataProvider );
            }
        }
        return dataProviders.values( ).toArray( new DataProvider[ dataProviders.size( ) ] );
    }

    public static long getSerialVersionUID( )
        { return serialVersionUID; }
}
