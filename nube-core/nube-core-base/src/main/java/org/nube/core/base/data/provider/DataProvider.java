/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:49
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import org.nube.core.base.data.NubeDataObject;

import java.util.Collection;

/**
 * Data Provider Interface.
 *
 * @author Rubén García Ríos
 */
public interface DataProvider
        extends NubeDataObject {

    /**
     * Data Provider Builder.
     *
     * @author Rubén García Ríos
     */
    interface Builder
            extends NubeDataObject {

        /**
         * Build mongo provider.
         *
         * @return the data provider
         */
        DataProvider build( );

        /**
         * Server addresses builder.
         *
         * @param serverAddresses the server addresses
         * @return the builder
         */
        Builder serverAddresses(
                Collection< ServerAddress > serverAddresses );

        /**
         * Credential builder.
         *
         * @param credential the credential
         * @return the builder
         */
        Builder credential(
                Credential credential );

        /**
         * Add server adress builder.
         *
         * @param serverAddress the server address
         * @return the builder
         */
        Builder addServerAdress( ServerAddress serverAddress );

        /**
         * Remove server adress builder.
         *
         * @param serverAddress the server address
         * @return the builder
         */
        Builder removeServerAdress( ServerAddress serverAddress );

        /**
         * Add all server adress builder.
         *
         * @param serverAddresses the server addresses
         * @return the builder
         */
        Builder addAllServerAdress( Collection< ServerAddress > serverAddresses );

        /**
         * Remove all server adress builder.
         *
         * @param serverAddresses the server addresses
         * @return the builder
         */
        Builder removeAllServerAdress( Collection< ServerAddress > serverAddresses );

        /**
         * The interface Server address.
         */
        interface ServerAddress
                extends NubeDataObject {
            /**
             * Host server address.
             *
             * @param host the host
             * @return the server address
             */
            ServerAddress host( String host );

            /**
             * Port server address.
             *
             * @param port the port
             * @return the server address
             */
            ServerAddress port( int port );
        }

        /**
         * The interface Credential.
         */
        interface Credential
                extends NubeDataObject {
            /**
             * Database credential.
             *
             * @param database the database
             * @return the credential
             */
            Credential database( String database );

            /**
             * Username credential.
             *
             * @param username the username
             * @return the credential
             */
            Credential username( String username );

            /**
             * Password credential.
             *
             * @param password the password
             * @return the credential
             */
            Credential password( char[] password );
        }
    }
}
