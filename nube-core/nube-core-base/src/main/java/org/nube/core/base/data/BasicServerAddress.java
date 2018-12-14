/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 14:57
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

public class BasicServerAddress
        extends AbstractServerAddress {
    private static final long serialVersionUID = 3839710225185142786L;
    // CONSTANTS
    private static final Pattern IP_ADDRESS_PATTERN = Pattern.compile(
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$" );

    public BasicServerAddress( ) { }

    public BasicServerAddress( final @NotNull String url )
        { super( url ); }

    public BasicServerAddress( final @Nullable Integer port )
        { super( port ); }

    public BasicServerAddress(
            final @NotNull String host,
            final @Nullable Integer port )
        { super( host, port ); }

    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @NotNull String host )
        { super( connectionProtocol, host ); }

    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @Nullable Integer port )
        { super( connectionProtocol, port ); }

    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @NotNull String host,
            final @Nullable Integer port )
        { super( connectionProtocol, host, port ); }

    public boolean isSecureConnection( )
        { return getConnectionProtocol( ).equals( ConnectionProtocol.HTTPS ); }

    public boolean isHostAnIPAddress( )
        { return IP_ADDRESS_PATTERN.matcher( getHost( ) ).matches( ); }

    @Override
    public String toString( )
        { return super.toString( ); }

    public static void main( String args[ ] ) {
        ServerAddress serverAddress = new BasicServerAddress( );
        try {
            serverAddress.setUrl( "https://servicenetwork:AeFC3q27Lk01H9p3KDYzX@127.0.0.1:808111" );
        } catch ( Exception e ) {
            System.out.print( e.getMessage( ) );
        }
        System.out.print( serverAddress );
    }
}
