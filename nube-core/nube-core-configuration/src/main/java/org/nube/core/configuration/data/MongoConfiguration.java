/*
 *  Developed by Rubén García Ríos
 *  Last modified 18/12/18 18:57
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration.data;

import com.mongodb.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.support.IsNewStrategyFactory;

import java.util.Collection;
import java.util.Set;

@AutoConfigureAfter( DataSourceConfiguration.class )
public class MongoConfiguration
        extends AbstractMongoConfiguration {
    private final static Logger _LOG = LogManager.getLogger( MongoConfiguration.class );
    private static final long serialVersionUID = 6163887373571549242L;
    // CONSTANTS.
    public static final String DEFAULT_BASE_PACKAGE = "org.nube";

    public MongoConfiguration( ) {
        super( );
    }

    @Override
    public MongoTemplate mongoTemplate( )
            throws
            Exception {
        return super.mongoTemplate( );
    }

    @Override
    public MongoDbFactory mongoDbFactory( ) {
        return super.mongoDbFactory( );
    }

    @Override
    protected String getMappingBasePackage( ) {
        return super.getMappingBasePackage( );
    }

    @Override
    public MappingMongoConverter mappingMongoConverter( )
            throws
            Exception {
        return super.mappingMongoConverter( );
    }

    @Override
    protected Collection< String > getMappingBasePackages( ) {
        return super.getMappingBasePackages( );
    }

    @Override
    public MongoMappingContext mongoMappingContext( )
            throws
            ClassNotFoundException {
        return super.mongoMappingContext( );
    }

    @Override
    public IsNewStrategyFactory isNewStrategyFactory( )
            throws
            ClassNotFoundException {
        return super.isNewStrategyFactory( );
    }

    @Override
    public CustomConversions customConversions( ) {
        return super.customConversions( );
    }

    @Override
    protected Set< Class< ? > > getInitialEntitySet( )
            throws
            ClassNotFoundException {
        return super.getInitialEntitySet( );
    }

    @Override
    protected Set< Class< ? > > scanForEntities( final String basePackage )
            throws
            ClassNotFoundException {
        return super.scanForEntities( basePackage );
    }

    @Override
    protected boolean abbreviateFieldNames( ) {
        return super.abbreviateFieldNames( );
    }

    @Override
    protected FieldNamingStrategy fieldNamingStrategy( ) {
        return super.fieldNamingStrategy( );
    }

    @Override
    public MongoClient mongoClient( ) {
        return null;
    }

    @Override
    protected String getDatabaseName( ) {
        return null;
    }
}
