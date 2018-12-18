/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 13:46
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.nube.core.base.utils.PasswordUtilities;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract Server Address.
 *
 * @author Rubén García Ríos
 */
public abstract class AbstractServerAddress
        extends NubeDataObject
        implements ServerAddress {
    private static final long serialVersionUID = -5578877182639697554L;
    /**
     * DEFAULT_HOST constant.
     */
// CONSTANTS.
    public static final String DEFAULT_HOST = "localhost";
    /**
     * DEFAULT_PORT constant.
     */
    public static final Integer DEFAULT_PORT = null;
    /**
     * DEFAULT_PROTOCOL constant.
     */
    public static final ConnectionProtocol DEFAULT_PROTOCOL = ConnectionProtocol.HTTP;
    // Credentials regex validation
    private static final Pattern CREDENTIALS_PATTERN = Pattern.compile(
            "(((\\w*\\%*\\$*\\-*\\_*\\&*\\+*){1,}(\\:)?" +
            "(\\w*\\%*\\$*\\-*\\_*\\&*\\+*){1,})(\\@))?" );
    // Port regex validation.
    private static final Pattern PORT_PATTERN = Pattern.compile(
            "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|" +
            "65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$" );
    // URL regex validation.
    private static final Pattern URL_PATTERN = Pattern.compile(
            "^((?:(https?):\\/\\/)?((\\w*\\%*\\$*\\-*\\_*\\&*\\+*){1,}(\\:)?(\\w*\\%*\\$*" +
            "\\-*\\_*\\&*\\+*){1,}(\\@))?((?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]|" +
            "[0-9])\\.(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.)(?:(?" +
            ":25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.)(?:(?:25[0-5]|2[0-4" +
            "][0-9]|1[0-9][0-9]|[0-9][0-9]|[0-9]))|(?:(?:(?:\\w+\\.){1,2}[\\w]{2,3}))|(?:localhost))" +
            "(?::(\\d+))?((?:\\/[\\w]+)*)(?:\\/|(\\/[\\w]+\\.[\\w]{3,4})|(\\?(?:([\\w]" +
            "+=[\\w]+)&)*([\\w]+=[\\w]+))?|\\?(?:(wsdl|wadl))))$" );
    // ATTRIBUTES.
    private String host;
    private Integer port;
    private String username;
    private char[ ] password;
    private String url;
    private ConnectionProtocol connectionProtocol;

    /**
     * Instantiates a new Abstract Server Address with default values.
     */
    public AbstractServerAddress( )
        { this( DEFAULT_HOST, DEFAULT_PORT ); }

    /**
     * Instantiates a new Abstract Server Address by url.
     *
     * @param url the url
     */
    @Contract( "null -> fail" )
    public AbstractServerAddress( @NotNull final String url )
        { setUrl( url ); }

    /**
     * Instantiates a new Abstract Server Address with default values and specified port.
     *
     * @param port the port
     */
    public AbstractServerAddress( @Nullable final Integer port )
        { this( DEFAULT_HOST, port ); }

    /**
     * Instantiates a new Abstract Server Address with default values and specified host and port.
     *
     * @param host the host
     * @param port the port
     */
    @Contract( "null, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final String host,
            @Nullable final Integer port )
        { this( DEFAULT_PROTOCOL, host, port ); }

    /**
     * Instantiates a new Abstract Server Address with default values and specified connection protocol and host.
     *
     * @param connectionProtocol the connection protocol
     * @param host               the host
     */
    @Contract( "null, _ -> fail; _, null -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @NotNull final String host )
        { this( connectionProtocol, host, DEFAULT_PORT ); }

    /**
     * Instantiates a new Abstract Server Address with default values and specified connection protocol and port.
     *
     * @param connectionProtocol the connection protocol
     * @param port               the port
     */
    @Contract( "null, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @Nullable final Integer port )
        { this( connectionProtocol, DEFAULT_HOST, port ); }

    /**
     * Instantiates a new Abstract Server Address without server authentication.
     *
     * @param connectionProtocol the connection protocol
     * @param host               the host
     * @param port               the port
     */
    @Contract( "null, _, _ -> fail; _, null, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @NotNull final String host,
            @Nullable final Integer port )
        { this( connectionProtocol, host, port, null, null ); }

    /**
     * Instantiates a new Abstract Server Address with server authentication.
     *
     * @param connectionProtocol the connection protocol
     * @param host               the host
     * @param port               the port
     * @param username           the username
     * @param password           the password
     */
    @Contract( "null, _, _, _, _ -> fail; _, null, _, _, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @NotNull final String host,
            @NotNull final Integer port,
            @Nullable final String username,
            @Nullable final char[ ] password ) {
        setConnectionProtocol( connectionProtocol );
        setHost( host );
        setPort( port );
        setUsername( username );
        setPassword( password );
    }

    @NotNull
    @Override
    public String getHost( )
        { return host; }

    @Override
    public void setHost( @NotNull final String host )
            throws IllegalArgumentException {
        if ( host == null || host.isEmpty( ) )
            throw new IllegalArgumentException( "Argument 'host' must be a valid IP or DNS." );
        this.host = host;
        refreshURL( );
    }

    @Nullable
    @Override
    public Integer getPort( )
        { return port; }

    @Override
    public void setPort( @Nullable final Integer port )
            throws IllegalArgumentException {
        if ( port != null && !PORT_PATTERN.matcher( Integer.toString( port ) ).matches( ) )
            throw new IllegalArgumentException( "Argument 'port' must be a valid port between 0 and 65535." );
        this.port = port;
        refreshURL( );
    }

    @Override
    public ConnectionProtocol getConnectionProtocol( )
        { return connectionProtocol; }

    @Override
    public void setConnectionProtocol( @NotNull final ConnectionProtocol connectionProtocol )
            throws IllegalArgumentException {
        if ( connectionProtocol == null )
            throw new IllegalArgumentException( "Argument 'connectionProtocol' can not be null." );
        this.connectionProtocol = connectionProtocol;
        refreshURL( );
    }

    @Override
    public String getUsername( )
        { return username; }

    @Override
    public void setUsername( final String username )
        { this.username = username; }

    @Override
    public char[ ] getPassword( )
        { return password; }

    @Override
    public void setPassword( final char[ ] password ) {
        if ( this.password != null )
            PasswordUtilities.erase( this.password );
        this.password = password;
    }

    @NotNull
    @Override
    public String getUrl( )
        { return url; }

    @Override
    public void setUrl( @NotNull final String url )
            throws IllegalArgumentException {
        if ( url == null || url.isEmpty( ) || !URL_PATTERN.matcher( url ).matches( ) )
            throw new IllegalArgumentException( "Argument 'url' must be a valid URL." );
        this.url = url;
        refresData( );
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    // Refresh URL with actual attribute values.
    private void refreshURL( ) {
        this.url = connectionProtocol.getUrlPrefix( ) +
                   ( username != null && !username.isEmpty( ) ? username : "" ) +
                   ( username != null && !username.isEmpty( ) && password != null && password.length > 0
                           ? ":" + password.toString( )
                           : "" ) +
                   host +
                   ( port != null ? ":" + port : "" );
    }

    // Refresh attribute values by url attribute value.
    private void refresData( )
            throws IllegalArgumentException {
        connectionProtocol = url.startsWith( ConnectionProtocol.HTTPS.getUrlPrefix( ) )
                ? ConnectionProtocol.HTTPS
                : ConnectionProtocol.HTTP;
        String remainURL = url.replaceFirst( connectionProtocol.getUrlPrefix( ), "" );
        if ( remainURL.contains( ":" ) ) {
            Matcher matcher = CREDENTIALS_PATTERN.matcher( remainURL );
            String[ ] remainURLParts = remainURL.split( ":" );
            if ( matcher.find( ) ) {
                String[ ] credentials = matcher
                        .group( 0 )
                        .substring( 0, matcher.group( 0 ).length( ) - 1 )
                        .split( ":" );
                if ( credentials.length == 2 ) {
                    setUsername( credentials[ 0 ] );
                    setPassword( credentials[ 1 ].toCharArray( ) );
                } else if ( credentials.length == 1 )
                    setUsername( credentials[ 0 ] );
            }
            setPort( Integer.parseInt( remainURLParts[ remainURLParts.length-1 ] ) );
            setHost( port != null ? remainURL.substring( 0, remainURL.lastIndexOf( ":" ) ) : remainURL );
        } else
            setHost( remainURL );
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof AbstractServerAddress ) ) return false;
        final AbstractServerAddress that = ( AbstractServerAddress ) o;
        return Objects.equals( getHost( ), that.getHost( ) ) &&
               Objects.equals( getPort( ), that.getPort( ) ) &&
               Objects.equals( getUsername( ), that.getUsername( ) ) &&
               Arrays.equals( getPassword( ), that.getPassword( ) ) &&
               Objects.equals( getUrl( ), that.getUrl( ) ) &&
               getConnectionProtocol( ) == that.getConnectionProtocol( );
    }

    @Override
    public int hashCode( ) {
        int result = Objects.hash( getHost( ), getPort( ), getUsername( ), getUrl( ), getConnectionProtocol( ) );
        result = 31 * result + Arrays.hashCode( getPassword( ) );
        return result;
    }

    @Override
    public String toString( )
        { return super.toString( ); }
}
