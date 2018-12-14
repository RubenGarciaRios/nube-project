/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 14:10
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.nube.core.base.data.NubeDataObject;
import org.nube.core.base.data.ServerAddress;
import org.nube.core.base.data.provider.DataProvider;
import org.nube.core.base.data.provider.DataProviderFactory;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * Mongo Provider Factory.
 * <p>
 * Provide all MongoDB generic beans definitions used to realize MongoDB operations.
 *
 * @author Rubén García Ríos
 */
public class MongoDataProviderFactory
        extends NubeDataObject
        implements DataProviderFactory {
    private final static Logger _LOG = LogManager.getLogger( MongoDataProviderFactory.class );
    private static final long serialVersionUID = 9047669308884057538L;
    // ATTRIBUTES.
    private MongoDataProvider mongoDataProvider;
    //@formatter:off
    /**
     * Instantiates a new Mongo Provider Factory.
     *
     * @param id       the id
     * @param database the database
     */
    @Contract( "null, _ -> fail; _, null -> fail" )
    public MongoDataProviderFactory(
            @NotNull final String id,
            @NotNull final String database )
        { this( id, database, null ); }

    /**
     * Instantiates a new Mongo Provider Factory.
     *
     * @param id       the id
     * @param database the database
     * @param username the username
     */
    @Contract( "null, _, _ -> fail; _, null, _ -> fail" )
    public MongoDataProviderFactory(
            @NotNull final String id,
            @NotNull final String database,
            String username )
        { this( id, database, username, null ); }

    /**
     * Instantiates a new Mongo Provider Factory.
     *
     * @param id       the id
     * @param database the database
     * @param username the username
     * @param password the password
     */
    @Contract( "null, _, _, _ -> fail; _, null, _, _ -> fail" )
    public MongoDataProviderFactory(
            @NotNull final String id,
            @NotNull final String database,
            String username,
            char[ ] password )
        { this( id, database, username, password, ( ServerAddress[ ] ) null ); }

    /**
     * Instantiates a new Mongo Provider Factory.
     *
     * @param id              the id
     * @param database        the database
     * @param username        the username
     * @param password        the password
     * @param serverAddresses the server addresses
     */
    @Contract( "null, _, _, _, _ -> fail; _, null, _, _, _ -> fail" )
    public MongoDataProviderFactory(
            @NotNull final String id,
            @NotNull final String database,
            final String username,
            final char[ ] password,
            final ServerAddress... serverAddresses )
        { this( id, database, username, password, serverAddresses != null ? Arrays.asList( serverAddresses ) : null ); }

    /**
     * Instantiates a new Mongo Provider Factory.
     *
     * @param id              the id
     * @param database        the database
     * @param username        the username
     * @param password        the password
     * @param serverAddresses the server addresses
     */
    @Contract( "null, _, _, _, _ -> fail; _, null, _, _, _ -> fail" )
    public MongoDataProviderFactory(
            @NotNull final String id,
            @NotNull final String database,
            final String username,
            final char[ ] password,
            final Collection< ServerAddress > serverAddresses ) {
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    String.class.getName( ), id );
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    String.class.getName( ), database );
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    String.class.getName( ), username );
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    char[ ].class.getName( ), password != null && password.length > 0 ? "<hide value>" : null );
        _LOG.debug( "Recieved argument of type[{}], with value: {}",
                    Collection.class.getName( ), serverAddresses );
        _LOG.debug( "Initialization of new object of type [{}]", MongoDataProvider.class.getName( ) );
        if ( id == null || id.isEmpty( ) )
            throw new IllegalArgumentException( "Argument 'id' must not be null." );
        if ( database == null || database.isEmpty( ) )
            throw new IllegalArgumentException( "Argument 'database' must not be null." );
        mongoDataProvider = MongoDataProvider.connectionManagement( )
                .useDataBase( database )
                .addAllServerAddresses( serverAddresses )
                .authenticateWithUsername( username )
                .authenticateWithPassword( password )
                .configure( );
        _LOG.debug( "[{}] Object has been initialized successfully: {}",
                    mongoDataProvider.getClass( ).getName( ), mongoDataProvider );
    }

    /**
     * Instantiates a new Mongo Provider Factory.
     *
     * @param mongoDataProvider the Mongo Data Provider
     */
    public MongoDataProviderFactory( final MongoDataProvider mongoDataProvider )
        { this.mongoDataProvider = mongoDataProvider; }

    @NotNull
    @Override
    public DataProvider getDataProvider( )
        { return mongoDataProvider; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoDataProviderFactory ) ) return false;
        final MongoDataProviderFactory that = ( MongoDataProviderFactory ) o;
        return Objects.equals( mongoDataProvider, that.mongoDataProvider );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( mongoDataProvider ); }

    @Override
    public String toString( )
        { return super.toString( ); }
    //@formatter:on
}
