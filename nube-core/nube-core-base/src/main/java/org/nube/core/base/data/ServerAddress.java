/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 14:33
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Server address inteface that's define all basic neccessary methods
 * to provide a server connection.
 *
 * @author Rubén García Ríos
 */
public interface ServerAddress {

    @NotNull String getHost( );

    void setHost( @NotNull String host )
            throws IllegalArgumentException;

    @Nullable Integer getPort( );

    void setPort( @Nullable Integer port )
            throws IllegalArgumentException;

    ConnectionProtocol getConnectionProtocol( );

    void setConnectionProtocol( @NotNull ConnectionProtocol connectionProtocol )
            throws IllegalArgumentException;

    @NotNull String getUrl( );

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

        public String getUrlPrefix( )
            { return urlPrefix; }
    }
}
