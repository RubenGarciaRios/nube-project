/*
 *  Developed by Rubén García Ríos
 *  Last modified 18/12/18 18:17
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.nube.core.base.data.NubeDataObject;
import org.nube.core.base.utils.PasswordUtilities;
import org.nube.core.base.utils.RandomStringGenerator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// https://github.com/ssinger/dynamic-beans-example
public abstract class DataProvider<
        C extends DataProvider.ConnectionManagementConfigurer >
        extends NubeDataObject {
    private static final long serialVersionUID = 3129762729209077235L;
    // DEFAULT VALUES.
    private static final String DEFAULT_HOST = "localhost";
    private static final Set< String > DEFAULT_BASE_PACKAGES = Collections.singleton( "org.nube" );
    private static final int DEFAULT_PORT = 0;
    private static final String DEFAULT_DATABASE = "nube";
    private static final String DEFAULT_USERNAME = "";
    private static final char[ ] DEFAULT_PASSWORD = { };
    // ATTRIBUTES.
    /**
     * Inmutable attribute used for Bean prefix identification in registration proccess.
     */
    public final String ID;
    private final String DATABASE;
    private final String USERNAME;
    private final char[ ] PASSWORD;
    private final Set< String > BASE_PACKAGES;
    private Map< String, String > beanIDs;

    private DataProvider(
            final String id,
            final String database,
            final String username,
            final char[ ] password,
            final Set< String > basePackages ) {
        ID = id;
        DATABASE = database;
        USERNAME = username;
        PASSWORD = password;
        BASE_PACKAGES = basePackages;
        beanIDs = new HashMap< >( );
    }

    @Contract( "null -> fail" )
    @SuppressWarnings( "unchecked" )
    protected DataProvider( @NotNull final C connectionManagerConfigurer ) {
        this( connectionManagerConfigurer.id,
              connectionManagerConfigurer.database,
              connectionManagerConfigurer.username,
              connectionManagerConfigurer.password,
              connectionManagerConfigurer.basePackages );
    }

    protected String database( )
        { return DATABASE; }

    protected String username( )
        { return USERNAME; }

    protected char[ ] password( )
        { return PASSWORD; }

    protected boolean authenticate( )
        { return USERNAME != null && !USERNAME.isEmpty( ); }

    protected Set< String > basePackages( )
        { return BASE_PACKAGES; }

    @NotNull
    public abstract BeanDefinition[ ] beanDefinitions( );

    public final Map< String, String > getBeanIDs( )
        { return beanIDs; }

    @SafeVarargs
    @NotNull
    protected static Set< Class< ? > > scanAnnotatedEntities(
            @Nullable final Class< ? extends Annotation >... entityAnnotationsClasses )
            throws ClassNotFoundException {
        return scanAnnotatedEntities( DEFAULT_BASE_PACKAGES, entityAnnotationsClasses ); }

    @SafeVarargs
    @NotNull
    protected static Set< Class< ? > > scanAnnotatedEntities(
            @Nullable final Collection< String > basePackages,
            @Nullable final Class< ? extends Annotation >... entityAnnotationsClasses )
            throws ClassNotFoundException {
        if ( basePackages == null ||
             basePackages.isEmpty( ) ||
             entityAnnotationsClasses == null ||
             entityAnnotationsClasses.length == 0 )
            return Collections.emptySet( );
        // Scan for Entities with annotated classes.
        Set< Class< ? > > entities = new HashSet< >( );
        ClassPathScanningCandidateComponentProvider componentProvider =
                new ClassPathScanningCandidateComponentProvider( false);
        // Add filters to find by annotated classes.
        for ( Class< ? extends Annotation > annotationClass : entityAnnotationsClasses )
            if ( annotationClass != null )
                componentProvider.addIncludeFilter( new AnnotationTypeFilter( annotationClass ) );
        // Add all Beans Definitions candidates.
        for ( String basePackage : basePackages ) {
            if ( StringUtils.hasText( basePackage ) ) {
                Set< BeanDefinition > beanDefinitions = componentProvider.findCandidateComponents( basePackage );
                for ( BeanDefinition beanDefinition : beanDefinitions )
                    if ( beanDefinition != null && StringUtils.hasText( beanDefinition.getBeanClassName( ) ) )
                        entities.add( ClassUtils.forName(
                                beanDefinition.getBeanClassName( ),
                                DataProvider.class.getClassLoader( ) ) );
            }
        }
        return entities;
    }

    public final Map< String, String > register( final ApplicationContext applicationContext ) {
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory( );
        //beanFactory.initializeBean(  )
        BeanDefinitionRegistry beanDefinitionRegistry = ( BeanDefinitionRegistry ) beanFactory;
        BeanDefinition[ ] beanDefinitions = beanDefinitions( );
        for ( BeanDefinition beanDefinition : beanDefinitions ) {
            String beanClassName = beanDefinition.getBeanClassName( ) != null
                    ? beanDefinition.getBeanClassName( )
                    : "" ;
            String beanID = ID + ( StringUtils.hasText( beanClassName )
                    ? Character.toLowerCase( beanClassName.charAt( 0 ) ) + beanClassName.substring( 1 )
                    : RandomStringGenerator.builder( ).build( ) );
            beanIDs.put( beanID, beanDefinition.getBeanClassName( ) );
            beanDefinitionRegistry.registerBeanDefinition( beanID, beanDefinition );
        }
        return beanIDs;
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
        protected Set< String > basePackages;
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
                final char[ ] password )
            { this( id, serverAddresses, database, username, password, null ); }

        public ConnectionManagementConfigurer(
                final String id,
                final Collection< S > serverAddresses,
                final String database,
                final String username,
                final char[ ] password,
                final Set< String > basePackages ) {
            this.id = id;
            this.serverAddresses = serverAddresses == null
                    ? new ArrayList< >( )
                    : serverAddresses;
            this.database = database;
            this.username = username;
            this.password = password;
            this.basePackages = basePackages == null
                    ? new HashSet< >( )
                    : basePackages;
        }

        public T identifiedBy( final String id ) {
            this.id = id;
            return instance( );
        }

        @NotNull
        public abstract S addServerAddress( );

        @NotNull
        @SuppressWarnings( "unchecked" )
        public T addAllServerAddresses( final Collection serverAddresses ) {
            if ( serverAddresses != null && !serverAddresses.isEmpty( ) )
                try {
                    for ( Object serverAddress : serverAddresses )
                        if ( serverAddress instanceof ServerAddress )
                            this.serverAddresses.add( ( S ) serverAddress );
                        else if ( serverAddress instanceof org.nube.core.base.data.ServerAddress )
                            if ( ( ( org.nube.core.base.data.ServerAddress ) serverAddress ).getPort( ) != null )
                                this.addServerAddress( )
                                    .withHost( ( ( org.nube.core.base.data.ServerAddress ) serverAddress ).getHost( ) )
                                    .withPort( ( ( org.nube.core.base.data.ServerAddress ) serverAddress ).getPort( ) )
                                    .and( );
                            else
                                this.addServerAddress( )
                                    .withHost( ( ( org.nube.core.base.data.ServerAddress ) serverAddress ).getHost( ) )
                                    .and( );

                } catch ( Exception e ) {
                   //...
                }
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
        public T withoutAuthentication( ) {
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

        @NotNull
        public T scanBasePackages( @Nullable final Set< String > basePackages ) {
            this.basePackages = basePackages;
            return instance( );
        }

        @NotNull
        public T addBasePackages( @NotNull final Set< String > basePackages ) {
            if ( basePackages == null || basePackages.isEmpty( ) )
                return instance( );
            if ( this.basePackages == null )
                this.basePackages = new HashSet< >( );
            this.basePackages.addAll( basePackages );
            return instance( );
        }

        @NotNull
        public T addBasePackage( @NotNull final String basePackage ) {
            if ( basePackage == null || basePackages.isEmpty( ) )
                return instance( );
            if ( basePackages == null )
                this.basePackages = new HashSet< >( );
            basePackages.add( basePackage );
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

    protected final static class BeanDefinitionBuilder< G >
            extends NubeDataObject {
        private static final long serialVersionUID = -5436606366126756494L;
        private GenericBeanDefinition beanDefinition;
        private G object;

        @SuppressWarnings( "unchecked" )
        public BeanDefinitionBuilder( Class< G > clazz, Object... beanArgs )
                throws NoSuchMethodException,
                IllegalAccessException,
                InvocationTargetException,
                InstantiationException {
            if ( beanArgs == null ) {
                beanDefinition = new GenericBeanDefinition( );
                beanDefinition.setBeanClass( clazz );
                object = clazz.newInstance( );
                return;
            }
            // Checking of arguments types for get a candidate object constructor and constructor for Bean deficinition.
            ConstructorArgumentValues constructorArgs = new ConstructorArgumentValues( );
            List< Class< ? > > argumentTypes = new ArrayList< >( );
            for ( final Object beanArg : beanArgs ) {
                constructorArgs.addGenericArgumentValue( beanArg );
                argumentTypes.add( beanArg.getClass( ) );
            }
            // Search for candidate constructors.
            Constructor< ? > candidateConstructor = null;
            try {
                candidateConstructor = clazz.getConstructor(
                        argumentTypes.toArray( new Class< ? >[ argumentTypes.size( ) ] ) );
            } catch ( NoSuchMethodException e ) {
                Constructor< ? >[ ] constructors = clazz.getDeclaredConstructors( );
                for ( final Constructor< ? > constructor : constructors ) {
                    constructor.setAccessible( true );
                    Class< ? >[ ] params = constructor.getParameterTypes( );
                    if ( params.length == beanArgs.length ) {
                        int i = params.length;
                        for ( final Class< ? > param : params ) {
                            for ( final Class< ? > argumentType : argumentTypes ) {
                                if ( param.isAssignableFrom( argumentType ) || param.equals( argumentType ) ) {
                                    --i;
                                    break;
                                }
                            }
                        }
                        if ( i != 0 )
                            continue;
                        candidateConstructor = constructor;
                        break;
                    }
                }
            }
            if ( candidateConstructor == null )
                throw new NoSuchMethodException( "Class [" + clazz.getName( ) + "] don't have any know constructor with the given arguments." );
            // Attributes initialization.
            beanDefinition = new GenericBeanDefinition( );
            beanDefinition.setBeanClass( clazz );
            beanDefinition.setConstructorArgumentValues( constructorArgs );
            object = ( G ) candidateConstructor.newInstance( beanArgs );
        }

        public GenericBeanDefinition beanDefinition( )
            { return beanDefinition; }

        public G object( )
            { return object; }

        @Override
        public String toString( )
            { return super.toString( ); }
    }

    @Override
    public String toString( )
        { return super.toString( ); }
}
