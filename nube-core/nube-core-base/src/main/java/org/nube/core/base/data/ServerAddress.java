/*
 *  Developed by Rubén García Ríos
 *  Last modified 17/12/18 10:41
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Server address interface that define all basic methods
 * to provide a server address data.
 *
 * @author Rubén García Ríos
 */
public interface ServerAddress {

    /**
     * Gets host.
     *
     * @return the host
     */
    @NotNull String getHost( );

    /**
     * Sets host.
     *
     * @param host the host
     *
     * @throws IllegalArgumentException the illegal argument exception
     */
    void setHost( @NotNull String host )
            throws IllegalArgumentException;

    /**
     * Gets port.
     *
     * @return the port
     */
    @Nullable Integer getPort( );

    /**
     * Sets port.
     *
     * @param port the port
     *
     * @throws IllegalArgumentException the illegal argument exception
     */
    void setPort( @Nullable Integer port )
            throws IllegalArgumentException;

    /**
     * Gets connection protocol.
     *
     * @return the connection protocol
     */
    ConnectionProtocol getConnectionProtocol( );

    /**
     * Sets connection protocol.
     *
     * @param connectionProtocol the connection protocol
     *
     * @throws IllegalArgumentException the illegal argument exception
     */
    void setConnectionProtocol( @NotNull ConnectionProtocol connectionProtocol )
            throws IllegalArgumentException;

    /**
     * Gets username.
     *
     * @return the username
     */
    String getUsername( );

    /**
     * Sets username.
     *
     * @param username the username
     */
    void setUsername( String username );

    /**
     * Get password char [ ].
     *
     * @return the char [ ]
     */
    char[ ] getPassword( );

    /**
     * Sets password.
     *
     * @param password the password
     */
    void setPassword( char[] password );

    /**
     * Gets url.
     *
     * @return the url
     */
    @NotNull String getUrl( );

    /**
     * Sets url.
     *
     * @param url the url
     *
     * @throws IllegalArgumentException the illegal argument exception
     */
    void setUrl( @NotNull String url )
            throws IllegalArgumentException;

    /**
     * Connection Protocol.
     */
    enum ConnectionProtocol {
        /**
         * Http protocol.
         */
        HTTP( "http://" ),
        /**
         * Https protocol.
         */
        HTTPS( "https://" );

        private String urlPrefix;

        ConnectionProtocol( String urlPrefix )
            { this.urlPrefix = urlPrefix; }

        /**
         * Gets url prefix.
         *
         * @return the url prefix
         */
        public String getUrlPrefix( )
            { return urlPrefix; }
    }
}
