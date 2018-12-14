/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 14:56
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractServerAddress
        extends NubeDataObject
        implements ServerAddress {
    private static final long serialVersionUID = -5578877182639697554L;
    // CONSTANTS.
    public static final String DEFAULT_HOST = "localhost";
    public static final Integer DEFAULT_PORT = null;
    public static final ConnectionProtocol DEFAULT_PROTOCOL = ConnectionProtocol.HTTP;
    private static final Pattern CREDENTIALS_PATTERN = Pattern.compile(
            "(((\\w*\\%*\\$*\\-*\\_*\\&*\\+*){1,}(\\:)" +
            "(\\w*\\%*\\$*\\-*\\_*\\&*\\+*){1,})(\\@))?" );
    // Port regex validation.
    private static final Pattern PORT_PATTERN = Pattern.compile(
            "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|" +
            "65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$" );
    // URL regex validation.
    private static final Pattern URL_PATTERN = Pattern.compile(
            "^((?:(https?):\\/\\/)?((\\w*\\%*\\$*\\-*\\_*\\&*\\+*){1,}\\:(\\w*\\%*\\$*" +
            "\\-*\\_*\\&*\\+*){1,}\\@)?((?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]|" +
            "[0-9])\\.(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.)(?:(?" +
            ":25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.)(?:(?:25[0-5]|2[0-4" +
            "][0-9]|1[0-9][0-9]|[0-9][0-9]|[0-9]))|(?:(?:(?:\\w+\\.){1,2}[\\w]{2,3})))" +
            "(?::(\\d+))?((?:\\/[\\w]+)*)(?:\\/|(\\/[\\w]+\\.[\\w]{3,4})|(\\?(?:([\\w]" +
            "+=[\\w]+)&)*([\\w]+=[\\w]+))?|\\?(?:(wsdl|wadl))))$" );
    // ATTRIBUTES.
    private String host;
    private Integer port;
    private String username;
    private char[ ] password;
    private String url;
    private ConnectionProtocol connectionProtocol;

    public AbstractServerAddress( )
        { this( DEFAULT_HOST, DEFAULT_PORT ); }

    @Contract( "null -> fail" )
    public AbstractServerAddress( @NotNull final String url )
        { setUrl( url ); }

    public AbstractServerAddress( @Nullable final Integer port )
        { this( DEFAULT_HOST, port ); }

    @Contract( "null, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final String host,
            @Nullable final Integer port )
        { this( DEFAULT_PROTOCOL, host, port ); }

    @Contract( "null, _ -> fail; _, null -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @NotNull final String host )
        { this( connectionProtocol, host, DEFAULT_PORT ); }

    @Contract( "null, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @Nullable final Integer port )
        { this( connectionProtocol, DEFAULT_HOST, port ); }

    @Contract( "null, _, _ -> fail; _, null, _ -> fail" )
    public AbstractServerAddress(
            @NotNull final ConnectionProtocol connectionProtocol,
            @NotNull final String host,
            @Nullable final Integer port ) {
        setConnectionProtocol( connectionProtocol );
        setHost( host );
        setPort( port );
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

    public static long getSerialVersionUID( )
        { return serialVersionUID; }


    private void refreshURL( )
        { this.url = connectionProtocol.getUrlPrefix( ) + host + ( port != null ? ":" + port : "" ); }

    private void refresData( ) {
        connectionProtocol = url.startsWith( ConnectionProtocol.HTTPS.getUrlPrefix( ) )
                ? ConnectionProtocol.HTTPS
                : ConnectionProtocol.HTTP;
        String remainURL = url.replaceFirst( connectionProtocol.getUrlPrefix( ), "" );
        if ( remainURL.contains( ":" ) ) {
            Matcher matcher = CREDENTIALS_PATTERN.matcher( remainURL );
            String[ ] remainURLParts = remainURL.split( ":" );
            while ( matcher.find( ) ) {
                System.out.println("Full match: " + matcher.group(0));
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    System.out.println("Group " + i + ": " + matcher.group(i));
                }
            }

            port = PORT_PATTERN.matcher( remainURLParts[ remainURLParts.length-1 ] ).matches( )
                    ? Integer.parseInt( remainURLParts[ remainURLParts.length-1 ] )
                    : null;
            if ( port != null )
                host = remainURL.substring( 0, remainURL.lastIndexOf( ":" ) );
            else
                host = remainURL;
        } else
            host = remainURL;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof AbstractServerAddress ) ) return false;
        final AbstractServerAddress that = ( AbstractServerAddress ) o;
        return Objects.equals( getHost( ), that.getHost( ) ) &&
               Objects.equals( getPort( ), that.getPort( ) ) &&
               Objects.equals( getUrl( ), that.getUrl( ) ) &&
               getConnectionProtocol( ) == that.getConnectionProtocol( );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getHost( ), getPort( ), getUrl( ), getConnectionProtocol( ) ); }

    @Override
    public String toString( )
        { return super.toString( ); }
}
