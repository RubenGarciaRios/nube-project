/*
 *  Developed by Rubén García Ríos
 *  Last modified 27/11/18 20:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data;

/**
 * Provider enumeration list.
 *
 * @author Rubén García Ríos
 */
public enum Provider {
    /**
     * Mongodb provider.
     */
    MONGODB( "mongodb" ),
    /**
     * Redis provider.
     */
    REDIS( "redis" ),
    /**
     * H 2 provider.
     */
    H2( "h2" ),
    /**
     * Mariadb provider.
     */
    MARIADB( "mariadb" ),
    /**
     * Mysql provider.
     */
    MYSQL( "mysql" ),
    /**
     * Postgre provider.
     */
    POSTGRE( "postgre" ),
    /**
     * Oracle provider.
     */
    ORACLE( "oravle" ),
    /**
     * Mssql provider.
     */
    MSSQL( "mssql" );

    private final String value;

    Provider( String value ) {
        this.value = value;
    }

    /**
     * Provider value string.
     *
     * @return the string
     */
    public String value( ) { return this.value; }
}
