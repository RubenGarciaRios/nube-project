/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 11:03
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

/**
 * Basic server address
 *
 * @author Rubén García Ríos.
 */
public class BasicServerAddress
        extends AbstractServerAddress {
    private static final long serialVersionUID = 3839710225185142786L;
    // CONSTANTS
    private static final Pattern IP_ADDRESS_PATTERN = Pattern.compile(
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$" );

    /**
     * Instantiates a new Basic Server Address with default values.
     */
    public BasicServerAddress( ) { }

    /**
     * Instantiates a new Basic Server Address by url.
     *
     * @param url the url
     */
    public BasicServerAddress( final @NotNull String url )
        { super( url ); }

    /**
     * Instantiates a new Basic Server Address with default values and specified port.
     *
     * @param port the port
     */
    public BasicServerAddress( final @Nullable Integer port )
        { super( port ); }

    /**
     * Instantiates a new Basic Server Address with default values and specified host and port.
     *
     * @param host the host
     * @param port the port
     */
    public BasicServerAddress(
            final @NotNull String host,
            final @Nullable Integer port )
        { super( host, port ); }

    /**
     * Instantiates a new Basic Server Address with default values and specified connection protocol and host.
     *
     * @param connectionProtocol the connection protocol
     * @param host               the host
     */
    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @NotNull String host )
        { super( connectionProtocol, host ); }

    /**
     * Instantiates a new Basic Server Address with default values and specified connection protocol and port.
     *
     * @param connectionProtocol the connection protocol
     * @param port               the port
     */
    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @Nullable Integer port )
        { super( connectionProtocol, port ); }

    /**
     * Instantiates a new Basic Server Address without server authentication.
     *
     * @param connectionProtocol the connection protocol
     * @param host               the host
     * @param port               the port
     */
    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @NotNull String host,
            final @Nullable Integer port )
        { super( connectionProtocol, host, port ); }

    /**
     * Instantiates a new Basic Server Address with server authentication.
     *
     * @param connectionProtocol the connection protocol
     * @param host               the host
     * @param port               the port
     * @param username           the username
     * @param password           the password
     */
    public BasicServerAddress(
            final @NotNull ConnectionProtocol connectionProtocol,
            final @NotNull String host,
            final @NotNull Integer port,
            final @Nullable String username,
            @Nullable final char[ ] password )
        { super( connectionProtocol, host, port, username, password ); }

    /**
     * Is secure connection boolean.
     *
     * @return true or false
     */
    public boolean isSecureConnection( )
        { return getConnectionProtocol( ).equals( ConnectionProtocol.HTTPS ); }

    /**
     * Is host an ip address.
     *
     * @return true or false
     */
    public boolean isHostAnIPAddress( )
        { return IP_ADDRESS_PATTERN.matcher( getHost( ) ).matches( ); }

    /**
     * Is local host address.
     *
     * @return true or false
     */
    public boolean isLocalHostAddress( )
        { return "localhost".equalsIgnoreCase( getHost( ) ) || "127.0.0.1".equalsIgnoreCase( getHost( ) ); }

    @Override
    public String toString( )
        { return super.toString( ); }
}
