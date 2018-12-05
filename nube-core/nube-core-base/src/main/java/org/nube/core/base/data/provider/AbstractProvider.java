/*
 *  Developed by Rubén García Ríos
 *  Last modified 4/12/18 21:47
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class AbstractProvider {
    protected static final String DEFAULT_HOST = "localhost";
    protected static final int DEFAULT_PORT = 0;
    protected static final String DEFAULT_DATABASE = "nube";
    protected static final String DEFAULT_USERNAME = "";
    protected static final char[ ] DEFAULT_PASSWORD = { };

    public static ConnectionManagementConfigurer connectionManagement( ) {
        return new ConnectionManagementConfigurer( );
    }

    public static class ConnectionManagementConfigurer {
        private Collection< ServerAddress > serverAddresses;
        private String database;
        private String username;
        private char[ ] password;

        public ConnectionManagementConfigurer( ) {
            serverAddresses = new ArrayList< >( );
            database = DEFAULT_DATABASE;
            username = DEFAULT_USERNAME;
            password = DEFAULT_PASSWORD;
        }

        public ServerAddress addServerAddress( )
            { return new ServerAddress( ); }

        public ConnectionManagementConfigurer useDataBase( String database ) {
            this.database = database;
            return this;
        }

        public ConnectionManagementConfigurer authenticateWithUsername( String username ) {
            this.username = username;
            return this;
        }

        public ConnectionManagementConfigurer authenticateWithPassword( char[ ] password ) {
            this.password = password;
            return this;
        }

        public ConnectionManagementConfigurer withoutAuthentication(  ) {
            this.username = "";
            this.password = null;
            return this;
        }

        public ConnectionManagementConfigurer withAuthentication( String username, char[ ] password ) {
            this.username = username;
            this.password = password;
            return this;
        }

        private void passwordErase( ) {
            //if (  )
        }

        public class ServerAddress {
            private String host;
            private int port;

            private ServerAddress( ) {
                host = DEFAULT_HOST;
                port = DEFAULT_PORT;
            }

            public ServerAddress withHost( String host ) {
                this.host = host;
                return this;
            }

            public ServerAddress withPort( int port ) {
                this.port = port;
                return this;
            }

            public ConnectionManagementConfigurer and( ) {
                if ( !serverAddresses.contains( this ) )
                    serverAddresses.add( this );
                return ConnectionManagementConfigurer.this;
            }

            @Override
            public boolean equals( final Object o ) {
                if ( this == o ) return true;
                if ( !( o instanceof ServerAddress ) ) return false;
                final ServerAddress that = ( ServerAddress ) o;
                return port == that.port && Objects.equals( host, that.host );
            }

            @Override
            public int hashCode( ) {
                return Objects.hash( host, port );
            }
        }
    }

    public static void main( String args[ ] ) {
        AbstractProvider.connectionManagement( )
                        .addServerAddress( )
                            .withHost( "localhost" )
                            .withPort( 0 )
                            .and( )
                        .addServerAddress( )
                            .and( )
                        .addServerAddress( )
                            .withHost( "localhost" )
                            .withPort( 27021 )
                            .and( )
                        .useDataBase( "nube" )
                        .authenticateWithUsername( "user" );
    }
}
