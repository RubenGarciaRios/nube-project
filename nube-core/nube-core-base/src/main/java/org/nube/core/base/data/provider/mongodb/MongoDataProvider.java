/*
 *  Developed by Rubén García Ríos
 *  Last modified 18/12/18 18:57
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.nube.core.base.data.provider.DataProvider;
import org.nube.core.base.data.provider.DataProviderType;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mapping.model.CamelCaseAbbreviatingFieldNamingStrategy;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.PropertyNameFieldNamingStrategy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Mongo DataProviderType.
 * Provide a MongoDB server connection to allow database operations.
 *
 * @author Rubén García Ríos
 * @see org.nube.core.base.data.provider.DataProvider
 */
public class MongoDataProvider
        extends DataProvider {
    private final static Logger _LOG = LogManager.getLogger( MongoDataProvider.class );
    private static final long serialVersionUID = -6129138067502029817L;
    // CONSTANTS.
    /**
     * DATA_PROVIDER_TYPE constant.
     */
    public static final DataProviderType DATA_PROVIDER_TYPE = DataProviderType.MONGODB;
    /**
     * CONNECTION_TIMEOUT constant.
     */
    public static final int CONNECTION_TIMEOUT = 1000 * 60; // 60 seconds.
    // DEFAULT VALUES.
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 27017;
    private static final String DEFAULT_DATABASE = "nube";
    private static final String DEFAULT_USERNAME = "";
    private static final char[ ] DEFAULT_PASSWORD = { };
    private static final boolean ABREVIATE_FIELD_NAMES = false;
    // ATTRIBUTES.
    private final List< com.mongodb.ServerAddress > SERVER_ADDRESSES;
    private final BeanDefinition[ ] BEAN_DEFINITIONS;
    private CustomConversions customConversions;

    /**
     * Instantiates a new Mongo provider.
     *
     * @param mongoConnectionManagementConfigurer the mongo connection management configurer
     */
    protected MongoDataProvider(
            MongoConnectionManagementConfigurer mongoConnectionManagementConfigurer )
            throws InvocationTargetException,
                   NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException {
        super( mongoConnectionManagementConfigurer );
        SERVER_ADDRESSES = mongoConnectionManagementConfigurer.serverAddresses;
        BEAN_DEFINITIONS = generateBeanDefinitions( );
        customConversions = new MongoCustomConversions( Collections.emptyList( ) );
    }

    //@formatter:off
    /**
     * Connection management mongo connection management configurer.
     *
     * @return the mongo connection management configurer
     */
    public static MongoConnectionManagementConfigurer connectionManagement( )
        { return new MongoConnectionManagementConfigurer( ); }

    @NotNull
    @Override
    public BeanDefinition[ ] beanDefinitions( )
        { return BEAN_DEFINITIONS; }

    public CustomConversions getCustomConversions( )
        { return customConversions; }

    public void setCustomConversions( final CustomConversions customConversions )
        { this.customConversions = customConversions; }

    protected BeanDefinition[ ] generateBeanDefinitions( )
            throws NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException,
                   InvocationTargetException {
        BeanDefinitionBuilder< MongoClient > mongoClientDefinition =
                getMongoClientBeanDefinition( );
        BeanDefinitionBuilder< SimpleMongoDbFactory > mongoDbFactoryDefinition =
                getMongoDbFactoryBeanDefinition( mongoClientDefinition.object( ) );
        BeanDefinitionBuilder< MongoTemplate > mongoTemplateDefinition =
                getMongoTemplateBeanDefinition( mongoDbFactoryDefinition.object( ) );
        BeanDefinitionBuilder< MongoMappingContext > mongoMappingContextDefinition =
                getMongoMappingContextBeanDefinition( );
        return new BeanDefinition[ ] {
                mongoClientDefinition.beanDefinition( ),
                mongoDbFactoryDefinition.beanDefinition( ),
                mongoTemplateDefinition.beanDefinition( ),
                mongoMappingContextDefinition.beanDefinition( ) };
    }
    //@formatter:on

    protected BeanDefinitionBuilder< MongoClient > getMongoClientBeanDefinition( )
            throws InvocationTargetException,
                   NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException {
        _LOG.debug( "Defining a new Bean of [{}]", MongoClient.class.getName( ) );
        BeanDefinitionBuilder< MongoClient > beanDefinitionBuilder;
        // FIRST ARGUMENT - Server Addresses List.
        _LOG.debug( "Adding a constructor argument of type [{}], with value: {}",
                    SERVER_ADDRESSES.getClass( ).getName( ), SERVER_ADDRESSES );
        // THIRD/SECOND* ARGUMENT - Mongo client options. (Third if has been defined username or password or Second if not).
        MongoClientOptions mongoClientOptions = MongoClientOptions
                .builder( )
                .connectTimeout( CONNECTION_TIMEOUT )
                .build( );
        _LOG.debug( "Adding a constructor argument of type [{}], with value: {}",
                    mongoClientOptions.getClass( ).getName( ), mongoClientOptions );
        // SECOND* ARGUMENT - Authentication Credentials. (Only if has been defined username or password).
        MongoCredential mongoCredential = null;
        if ( authenticate( ) ) {
            mongoCredential = MongoCredential.createCredential( username( ), database( ), password( ) );
            _LOG.debug( "Adding a constructor argument of type [{}], with value: {}",
                        mongoCredential.getClass( ).getName( ), mongoCredential );
            return new BeanDefinitionBuilder< >(
                    MongoClient.class,
                    SERVER_ADDRESSES,
                    mongoCredential,
                    mongoClientOptions );
        }
        return new BeanDefinitionBuilder< >(
                MongoClient.class,
                SERVER_ADDRESSES,
                mongoClientOptions );
    }

    protected BeanDefinitionBuilder< SimpleMongoDbFactory > getMongoDbFactoryBeanDefinition( MongoClient mongoClient )
            throws InvocationTargetException,
                   NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException {
        _LOG.debug( "Defining a new Bean of [{}]", SimpleMongoDbFactory.class.getName( ) );
        BeanDefinitionBuilder< SimpleMongoDbFactory > beanDefinitionBuilder;
        // FIRST ARGUMENT - Mongo Client.
        _LOG.debug( "Adding a constructor argument of type [{}], with value: {}",
                    mongoClient.getClass( ).getName( ), mongoClient );
        // SECOND ARGUMENT - Database Name.
        _LOG.debug( "Adding a constructor argument of type [{}], with value: {}",
                    database( ).getClass( ).getName( ), database( ) );
        return new BeanDefinitionBuilder< >(
                SimpleMongoDbFactory.class,
                mongoClient,
                database( ) );
    }

    protected BeanDefinitionBuilder< MongoTemplate > getMongoTemplateBeanDefinition( MongoDbFactory mongoDbFactory )
            throws InvocationTargetException,
                   NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException {
        _LOG.debug( "Defining a new Bean of [{}]", MongoTemplate.class.getName( ) );
        // FIRST ARGUMENT - MongoDB Factory.
        _LOG.debug( "Adding a constructor argument of type [{}], with value: {}",
                    mongoDbFactory.getClass( ).getName( ), mongoDbFactory );
        return new BeanDefinitionBuilder< >( MongoTemplate.class, mongoDbFactory );
    }

    @SuppressWarnings( "unchecked" )
    protected BeanDefinitionBuilder< MongoMappingContext > getMongoMappingContextBeanDefinition( )
            throws InvocationTargetException,
                   NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException {
        _LOG.debug( "Defining a new Bean of [{}]", MongoMappingContext.class.getName( ) );
        BeanDefinitionBuilder< MongoMappingContext > beanDefinitionBuilder =
                new BeanDefinitionBuilder< >( MongoMappingContext.class );
        try {
            GenericBeanDefinition beanDefinition = beanDefinitionBuilder.beanDefinition( );
            MongoMappingContext mongoMappingContext = beanDefinitionBuilder.object( );
            Set< Class< ? > > initialEntitySet = MongoDataProvider.scanAnnotatedEntities( basePackages( ) );
            _LOG.debug( "Adding a attribute value of type [{}], with value: {}",
                        initialEntitySet.getClass( ).getName( ), initialEntitySet );
            beanDefinition
                    .setAttribute( "initialEntitySet", initialEntitySet );
            mongoMappingContext
                    .setInitialEntitySet( initialEntitySet );
            _LOG.debug( "Adding a attribute value of type [{}], with value: {}",
                        getCustomConversions( ).getSimpleTypeHolder( ).getClass( ).getName( ),
                        getCustomConversions( ).getSimpleTypeHolder( ) );
            beanDefinition
                    .setAttribute( "simpleTypeHolder", getCustomConversions( ).getSimpleTypeHolder( ) );
            mongoMappingContext
                    .setSimpleTypeHolder( getCustomConversions( ).getSimpleTypeHolder( ) );
            _LOG.debug( "Adding a attribute value of type [{}], with value: {}",
                        fieldNamingStrategy( ).getClass( ).getName( ), fieldNamingStrategy( ) );
            beanDefinition
                    .setAttribute( "fieldNamingStrategy", fieldNamingStrategy( ) );
            mongoMappingContext
                    .setFieldNamingStrategy( fieldNamingStrategy( ) );

        } catch ( ClassNotFoundException e ) {
            _LOG.error( e );
        }
        return null;
    }

    protected boolean abbreviateFieldNames( )
        { return ABREVIATE_FIELD_NAMES; }

    protected FieldNamingStrategy fieldNamingStrategy( ) {
        return abbreviateFieldNames( )
                ? new CamelCaseAbbreviatingFieldNamingStrategy( )
                : PropertyNameFieldNamingStrategy.INSTANCE;
    }

    @NotNull
    protected static Set< Class< ? > > scanAnnotatedEntities(
            @Nullable final Collection< String > basePackages )
            throws ClassNotFoundException
        { return DataProvider.scanAnnotatedEntities( basePackages, Document.class, Persistent.class ); }

    //@formatter:on
    /**
     * Mongo connection Management Configurer.
     */
    public static class MongoConnectionManagementConfigurer
            extends ConnectionManagementConfigurer {
        /**
         * The Server addresses.
         */
        List< com.mongodb.ServerAddress > serverAddresses;

        /**
         * Instantiates a new Mongo Connection Management Configurer.
         */
        public MongoConnectionManagementConfigurer( ) {
            super( null, DEFAULT_DATABASE, DEFAULT_USERNAME, DEFAULT_PASSWORD );
            serverAddresses = new ArrayList< >( );
        }

        @Override
        public MongoConnectionManagementConfigurer identifiedBy( final String id ) {
            super.identifiedBy( id );
            return this;
        }

        @Override
        public MongoServerAddress addServerAddress( )
            { return new MongoServerAddress( ); }

        @Override
        public MongoConnectionManagementConfigurer addAllServerAddresses(
                final Collection serverAddresses ) {
            super.addAllServerAddresses( serverAddresses );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer removeServerAddress(
                final ServerAddress serverAddress ) {
            super.removeServerAddress( serverAddress );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer useDataBase(
                final String database ) {
            super.useDataBase( database );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer authenticateWithUsername(
                final @NotNull String username ) {
            super.authenticateWithUsername( username );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer authenticateWithPassword(
                @NotNull final char[ ] password ) {
            super.authenticateWithPassword( password );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer withoutAuthentication( ) {
            super.withoutAuthentication( );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer withAuthentication(
                final @NotNull String username,
                @NotNull final char[ ] password ) {
            super.withAuthentication( username, password );
            return this;
        }

        @Override
        @SuppressWarnings( { "unchecked", "null" } )
        public MongoDataProvider configure( ) {
            if ( id == null || id.isEmpty( ) )
                throw new RuntimeException( "Attribute 'ID' can not be empty and must have a valid value." );
            // If not already added any server address, adds default.
            if ( super.serverAddresses == null || super.serverAddresses.isEmpty( ) )
                super.serverAddresses.add(
                        addServerAddress( )
                                .withHost( DEFAULT_HOST )
                                .withPort( DEFAULT_PORT ) );
            for ( MongoServerAddress serverAddres : ( Collection< MongoServerAddress > ) super.serverAddresses )
                this.serverAddresses.add( serverAddres.parse( ) );
            try {
                return new MongoDataProvider( this );
            } catch (
                    NoSuchMethodException |
                            InstantiationException |
                            IllegalAccessException |
                            InvocationTargetException e ) {
                _LOG.error( e );
                return null;
            }
        }
        //@formatter:off
        @Override
        protected MongoConnectionManagementConfigurer instance( )
            { return this; }
        //@formatter:on
        /**
         * Mongo Server Address.
         *
         * @author Rubén García Ríos
         */
        public class MongoServerAddress
                extends ServerAddress {
            //@formatter:off
            /**
             * Instantiates a new Mongo Server Address.
             */
            public MongoServerAddress( )
                { super( DEFAULT_HOST, DEFAULT_PORT ); }

            /**
             * Instantiates a new Mongo Server Address.
             *
             * @param host the host
             */
            public MongoServerAddress( final String host )
                { super( host, DEFAULT_PORT ); }

            /**
             * Instantiates a new Mongo Server Address.
             *
             * @param port the port
             */
            public MongoServerAddress( final int port )
                { super( DEFAULT_HOST, port ); }

            /**
             * Instantiates a new Mongo Server Address.
             *
             * @param host the host
             * @param port the port
             */
            public MongoServerAddress( final String host, final int port )
                { super( host, port ); }

            @Override
            protected MongoServerAddress instance( )
                { return this; }

            private com.mongodb.ServerAddress parse( )
                { return new com.mongodb.ServerAddress( super.host, super.port ); }
            //@formatter:on
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof MongoConnectionManagementConfigurer ) ) return false;
            if ( !super.equals( o ) ) return false;
            final MongoConnectionManagementConfigurer that = ( MongoConnectionManagementConfigurer ) o;
            return Objects.equals( serverAddresses, that.serverAddresses );
        }
        //@formatter:off
        @Override
        public int hashCode( )
            { return Objects.hash( super.hashCode( ), serverAddresses ); }
        //@formatter:on
    }
    //@formatter:off

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoDataProvider ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoDataProvider that = ( MongoDataProvider ) o;
        return Objects.equals( SERVER_ADDRESSES, that.SERVER_ADDRESSES ) &&
               Arrays.equals( BEAN_DEFINITIONS, that.BEAN_DEFINITIONS );
    }

    @Override
    public int hashCode( ) {
        int result = Objects.hash( super.hashCode( ), SERVER_ADDRESSES );
        result = 31 * result + Arrays.hashCode( BEAN_DEFINITIONS );
        return result;
    }

    public static void main( String[ ] args ) {
        MongoDataProvider.connectionManagement( )
                         .identifiedBy( "id" )
                         .useDataBase( "TESTING" )
                         .configure( );
    }
}
