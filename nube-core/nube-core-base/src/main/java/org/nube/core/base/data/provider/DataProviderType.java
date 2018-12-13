/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 12:30
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

/**
 * DataProviderType enumeration list.
 *
 * @author Rubén García Ríos
 */
public enum DataProviderType {
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

    DataProviderType( String value ) {
        this.value = value;
    }

    /**
     * DataProviderType value string.
     *
     * @return the string
     */
    public String value( ) { return this.value; }
}
