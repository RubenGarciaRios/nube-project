/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 15:05
 *  Copyright (c) 2018 All rights reserved.
 */
/*
package org.nube.core.base.config.data.provider.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.NubeConfigurationObject;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class MongoConfiguration
        extends AbstractMongoConfiguration
        implements NubeConfigurationObject {
    private final static Logger _LOG = LogManager.getLogger( MongoConfiguration.class );

    MongoConfiguration(  )

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected Collection< String > getMappingBasePackages( )
        { return new ArrayList< String >( ) { { add( "org.nube" ); } }; }

    @Bean
    public UserCascadeSaveMongoEventListener userCascadingMongoEventListener() {
        return new UserCascadeSaveMongoEventListener();
    }

    @Bean
    public CascadeSaveMongoEventListener cascadingMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }

    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new UserWriterConverter());
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
*/