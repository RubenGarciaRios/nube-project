/*
 *  Developed by Rubén García Ríos
 *  Last modified 13/12/18 19:03
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.data.provider.DataProvider;
import org.nube.core.base.data.provider.DataProviderType;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.constraints.NotNull;
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
    // ATTRIBUTES.
    private final List< com.mongodb.ServerAddress > SERVER_ADDRESES;
    private final BeanDefinition[ ] BEAN_DEFINITIONS;

    /**
     * Instantiates a new Mongo provider.
     *
     * @param mongoConnectionManagementConfigurer the mongo connection management configurer
     */
    protected MongoDataProvider(
            MongoConnectionManagementConfigurer mongoConnectionManagementConfigurer ) {
        super( mongoConnectionManagementConfigurer );
        SERVER_ADDRESES = mongoConnectionManagementConfigurer.serverAddresses;
        BEAN_DEFINITIONS = generateBeanDefinitions( );
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
    //@formatter:on
    /**
     * The type Mongo connection management configurer.
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
            super( DEFAULT_DATABASE, DEFAULT_USERNAME, DEFAULT_PASSWORD );
            serverAddresses = new ArrayList< >( );
        }

        @Override
        public MongoServerAddress addServerAddress( )
            { return new MongoServerAddress( ); }

        @Override
        public MongoConnectionManagementConfigurer addAllServerAddresses( final Collection serverAddresses ) {
            super.addAllServerAddresses( serverAddresses );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer removeServerAddress( final ServerAddress serverAddress ) {
            super.removeServerAddress( serverAddress );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer useDataBase( final String database ) {
            super.useDataBase( database );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer authenticateWithUsername( final @NotNull String username ) {
            super.authenticateWithUsername( username );
            return this;
        }

        @Override
        public MongoConnectionManagementConfigurer authenticateWithPassword( @NotNull final char[ ] password ) {
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
            // If not already added any server address, adds default.
            if ( super.serverAddresses == null || super.serverAddresses.isEmpty( ) )
                super.serverAddresses.add(
                        addServerAddress( )
                            .withHost( DEFAULT_HOST )
                            .withPort( DEFAULT_PORT ) );
            for ( MongoServerAddress serverAddres : ( Collection< MongoServerAddress > ) super.serverAddresses )
                this.serverAddresses.add( serverAddres.parse( ) );
            return new MongoDataProvider( this );
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
    private BeanDefinition[ ] generateBeanDefinitions( ) {
        _LOG.debug( "Defining a new Bean of [{}]", MongoTemplate.class.getName( ) );
        MongoClient mongoClient = authenticate( )
                ? new MongoClient(
                        SERVER_ADDRESES,
                        MongoCredential.createCredential( username( ), database( ), password( ) ),
                        MongoClientOptions.builder( )
                                          .connectTimeout( CONNECTION_TIMEOUT )
                                          .build( ) )
                : new MongoClient(
                        SERVER_ADDRESES,
                        MongoClientOptions.builder( )
                                          .connectTimeout( CONNECTION_TIMEOUT )
                                          .build( ) );
        // Mongo Template.
        _LOG.debug( "Defining a new Bean of [{}]", MongoTemplate.class.getName( ) );
        GenericBeanDefinition mongoTemplateBean = new GenericBeanDefinition( );
        ConstructorArgumentValues mongoTemplateArgs = new ConstructorArgumentValues( );
        mongoTemplateBean.setBeanClass( MongoTemplate.class );
        _LOG.debug( "Adding argument of type [{}], with value: {}",
                    mongoClient.getClass( ).getName( ), mongoClient );
        mongoTemplateArgs.addGenericArgumentValue( mongoClient );
        _LOG.debug( "Adding argument of type [{}], with value: {}",
                    database( ).getClass( ).getName( ), database( ) );
        mongoTemplateArgs.addGenericArgumentValue( database( ) );
        mongoTemplateBean.setConstructorArgumentValues( mongoTemplateArgs );
        _LOG.debug( "> Returning a new instance of [{}], with value: {}",
                    mongoTemplateBean.getClass( ).getName( ), mongoTemplateBean );
        return new BeanDefinition[ ] { mongoTemplateBean };
    }
    //@formatter:on
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoDataProvider ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoDataProvider that = ( MongoDataProvider ) o;
        return Objects.equals( SERVER_ADDRESES, that.SERVER_ADDRESES ) &&
               Arrays.equals( BEAN_DEFINITIONS, that.BEAN_DEFINITIONS );
    }

    @Override
    public int hashCode( ) {
        int result = Objects.hash( super.hashCode( ), SERVER_ADDRESES );
        result = 31 * result + Arrays.hashCode( BEAN_DEFINITIONS );
        return result;
    }
}
