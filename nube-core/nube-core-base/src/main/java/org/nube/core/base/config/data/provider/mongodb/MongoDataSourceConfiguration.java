/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 17:40
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data.provider.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.data.DataSourceConfiguration;
import org.nube.core.base.data.property.NubeDataSourceProperties;
import org.nube.core.base.data.provider.DataProvider;
import org.nube.core.base.data.provider.DataProviderFactory;
import org.nube.core.base.data.provider.DataProviderType;
import org.nube.core.base.data.provider.mongodb.MongoDataProvider;
import org.nube.core.base.data.provider.mongodb.MongoDataProviderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;

import java.util.HashMap;
import java.util.Map;

public abstract class MongoDataSourceConfiguration
        extends MongoConfigurationSupport
        implements DataSourceConfiguration< MongoDataProvider > {
    private final static Logger _LOG = LogManager.getLogger( MongoDataSourceConfiguration.class );
    private static final long serialVersionUID = -4090826256920922305L;

    @Bean( "mongoDataProviders" )
    @Override
    public MongoDataProvider[ ] addAllDataProviders(
            NubeDataSourceProperties dataSourceProperties,
            ApplicationContext applicationContext ) {
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    NubeDataSourceProperties.class.getName( ), dataSourceProperties );
        NubeDataSourceProperties.DataSource[ ] dataSources = dataSourceProperties.getDataSources( );
        Map< String, MongoDataProvider > dataProviders = new HashMap< >( );
        for ( NubeDataSourceProperties.DataSource dataSource : dataSources ) {
            DataProviderFactory providerFactory = null;
            if ( dataSource.getDataProviderType( ) == DataProviderType.MONGODB ) {
                providerFactory =
                        new MongoDataProviderFactory(
                            dataSource.getId( ),
                            dataSource.getDatabase( ),
                            dataSource.getUsername( ),
                            dataSource.getPassword( ),
                            dataSource.getServerAddresses( ) );
                if ( dataProviders.containsKey( dataSource.getId( ) ) )
                    throw new RuntimeException( "Mongo Datasource with id '" + dataSource.getId( ) + "'already exists." );
                DataProvider dataProvider = providerFactory.getDataProvider( );
                dataProvider.register( applicationContext );
                dataProviders.put( dataSource.getId( ), ( MongoDataProvider ) dataProvider );
            }
        }
        return dataProviders.values( ).toArray( new MongoDataProvider[ dataProviders.size( ) ] );
    }

    public static long getSerialVersionUID( )
        { return serialVersionUID; }
}
