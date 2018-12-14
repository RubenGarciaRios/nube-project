/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 9:41
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import org.jetbrains.annotations.Contract;
import org.nube.core.base.data.NubeDataObject;
import org.nube.core.base.utils.PasswordUtilities;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.NotNull;
import java.util.*;

// https://github.com/ssinger/dynamic-beans-example
public abstract class DataProvider<
        C extends DataProvider.ConnectionManagementConfigurer >
        extends NubeDataObject {
    private static final long serialVersionUID = 3129762729209077235L;
    // DEFAULT VALUES.
    private static String DEFAULT_HOST = "localhost";
    private static int DEFAULT_PORT = 0;
    private static String DEFAULT_DATABASE = "nube";
    private static String DEFAULT_USERNAME = "";
    private static char[ ] DEFAULT_PASSWORD = { };
    // ATTRIBUTES.
    /**
     * Inmutable attribute used for Bean prefix identification in registration proccess.
     */
    public final String ID;
    private final String DATABASE;
    private final String USERNAME;
    private final char[ ] PASSWORD;
    private Map< String, String > beanIDs;

    private DataProvider(
            final String id,
            final String database,
            final String username,
            final char[ ] password ) {
        ID = id;
        DATABASE = database;
        USERNAME = username;
        PASSWORD = password;
        beanIDs = new HashMap< >( );
    }

    @Contract( "null -> fail" )
    @SuppressWarnings( "unchecked" )
    protected DataProvider( @NotNull final C connectionManagerConfigurer ) {
        this( connectionManagerConfigurer.id,
              connectionManagerConfigurer.database,
              connectionManagerConfigurer.username,
              connectionManagerConfigurer.password );
    }

    protected String database( )
        { return DATABASE; }

    protected String username( )
        { return USERNAME; }

    protected char[ ] password( )
        { return PASSWORD; }

    protected boolean authenticate( )
        { return USERNAME != null && !USERNAME.isEmpty( ); }

    @NotNull
    public abstract BeanDefinition[ ] beanDefinitions( );

    public final Map< String, String > getBeanIDs( )
        { return beanIDs; }

    public final void register( final ApplicationContext applicationContext ) {
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory( );
        //beanFactory.initializeBean(  )
        BeanDefinitionRegistry beanDefinitionRegistry = ( BeanDefinitionRegistry ) beanFactory;
        BeanDefinition[ ] beanDefinitions = beanDefinitions( );
        for ( BeanDefinition beanDefinition : beanDefinitions ) {
            String beanClassName = beanDefinition.getBeanClassName( );
            String beanID = ID +
                            Character.toLowerCase( beanClassName.charAt( 0 ) ) +
                            beanClassName.substring( 1 );
            beanIDs.put( beanID, beanDefinition.getBeanClassName( ) );
            beanDefinitionRegistry.registerBeanDefinition( beanID, beanDefinition );
        }
    }

    public static abstract class ConnectionManagementConfigurer<
            P extends DataProvider,
            T extends DataProvider.ConnectionManagementConfigurer,
            S extends DataProvider.ConnectionManagementConfigurer.ServerAddress >
            extends NubeDataObject {
        private static final long serialVersionUID = -3104671840993899407L;
        // ATTRIBUTES.
        protected String id;
        protected Collection< S > serverAddresses;
        protected String database;
        protected String username;
        protected char[ ] password;
        protected GenericBeanDefinition[ ] providerBeanComponents;
        private boolean authenticate;

        protected ConnectionManagementConfigurer( ) {
            this( null,
                  new ArrayList< >( ),
                  DEFAULT_DATABASE,
                  DEFAULT_USERNAME,
                  DEFAULT_PASSWORD );
        }

        protected ConnectionManagementConfigurer(
                final String id,
                final String database,
                final String username,
                final char[ ] password ) {
            this( id, new ArrayList< >( ), database, username, password );
        }

        public ConnectionManagementConfigurer(
                final String id,
                final Collection< S > serverAddresses,
                final String database,
                final String username,
                final char[ ] password ) {
            this.serverAddresses = serverAddresses == null
                    ? new ArrayList< >( )
                    : serverAddresses;
            this.id = id;
            this.database = database;
            this.username = username;
            this.password = password;
        }

        public T identifiedBy( final String id ) {
            this.id = id;
            return instance( );
        }

        @NotNull
        public abstract S addServerAddress( );

        @NotNull
        public T addAllServerAddresses( final Collection< S > serverAddresses ) {
            if ( serverAddresses != null && !serverAddresses.isEmpty( ) )
                this.serverAddresses.addAll( serverAddresses );
            return instance( );
        }

        @NotNull
        public T removeServerAddress( final S serverAddress ) {
            this.serverAddresses.remove( serverAddress );
            return instance( );
        }

        @NotNull
        public T useDataBase( final String database ) {
            this.database = database;
            return instance( );
        }

        @NotNull
        public T authenticateWithUsername(
                @NotNull final String username ) {
            this.username = username;
            return instance( );
        }

        @NotNull
        public T authenticateWithPassword(
                @NotNull final char[ ] password ) {
            if ( this.password != null )
                PasswordUtilities.erase( this.password );
            this.password = password;
            return instance( );
        }

        @NotNull
        public T withoutAuthentication(  ) {
            this.username = "";
            PasswordUtilities.erase( this.password );
            return instance( );
        }

        @NotNull
        public T withAuthentication(
                @NotNull final String username,
                @NotNull final char[ ] password ) {
            this.username = username;
            if ( this.password != null )
                PasswordUtilities.erase( this.password );
            this.password = password;
            return instance( );
        }

        public abstract P configure( );

        @NotNull
        protected abstract T instance( );

        public abstract class ServerAddress < A extends S >
                extends NubeDataObject {
            protected String host;
            protected int port;

            protected ServerAddress( )
                { this( DEFAULT_HOST, DEFAULT_PORT ); }

            protected ServerAddress( final String host )
                { this( host, DEFAULT_PORT ); }

            protected ServerAddress( final int port )
                { this( DEFAULT_HOST, port ); }

            protected ServerAddress( final String host, final int port ) {
                this.host = host;
                this.port = port;
            }

            @NotNull
            @Contract( "null -> fail" )
            public A withHost( @NotNull String host ) {
                if ( host == null || host.isEmpty( ) )
                    throw new IllegalArgumentException( "Argument 'host' must not be null." );
                this.host = host;
                return instance( );
            }

            @NotNull
            public A withPort( int port ) {
                if ( port < 1 )
                    throw new IllegalArgumentException( "Argument 'port' must be greater than zero." );
                this.port = port;
                return instance( );
            }

            @NotNull
            @SuppressWarnings( "unchecked" )
            public T and( ) {
                if ( !serverAddresses.contains( instance( ) ) )
                    serverAddresses.add( instance( ) );
                return ( T ) ConnectionManagementConfigurer.this;
            }

            @NotNull
            protected abstract A instance( );

            @Override
            @SuppressWarnings( "unchecked" )
            public boolean equals( final Object o ) {
                if ( this == o ) return true;
                if ( !( o instanceof ServerAddress ) ) return false;
                final ServerAddress that = ( ServerAddress ) o;
                return port == that.port && Objects.equals( host, that.host );
            }

            @Override
            public int hashCode( )
                { return Objects.hash( host, port ); }

            @Override
            public String toString( )
                { return super.toString( ); }
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof ConnectionManagementConfigurer ) ) return false;
            final ConnectionManagementConfigurer< ?, ?, ? > that = ( ConnectionManagementConfigurer< ?, ?, ? > ) o;
            return Objects.equals( serverAddresses, that.serverAddresses ) &&
                   Objects.equals( database, that.database ) &&
                   Objects.equals( username, that.username ) &&
                   Arrays.equals( password, that.password );
        }

        @Override
        public int hashCode( ) {
            int result = Objects.hash( serverAddresses, database, username );
            result = 31 * result + Arrays.hashCode( password );
            return result;
        }

        @Override
        public String toString( )
            { return super.toString( ); }
    }

    @Override
    public String toString( )
        { return super.toString( ); }
}
