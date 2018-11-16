/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.v1.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * The type Privileges.
 *
 * @see NubeDomainObject
 */
@Document
public class Privileges implements NubeDomainObject {
    private static final long serialVersionUID = 5481367912266630913L;
    @Id
    private String id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size( min = 1 )
    private String resource;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size( min = 1 )
    private String operation;

    /**
     * Instantiates a new Privileges.
     */
    public Privileges( ) {
    }

    /**
     * Instantiates a new Privileges.
     *
     * @param id        the id
     * @param resource  the resource
     * @param operation the operation
     */
    @PersistenceConstructor
    public Privileges(
            final String id,
            @NotNull @NotBlank @NotEmpty final String resource,
            @NotNull @NotBlank @NotEmpty final String operation ) {
        this.id = id;
        this.resource = resource;
        this.operation = operation;
    }

    /**
     * Instantiates a new Privileges.
     *
     * @param privileges the privileges
     */
    public Privileges( Privileges privileges ) {
        this.id = privileges.id;
        this.resource = privileges.resource;
        this.operation = privileges.operation;
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId( ) {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId( String id ) {
        this.id = id;
    }

    /**
     * Gets resource.
     *
     * @return the resource
     */
    public String getResource( ) {
        return resource;
    }

    /**
     * Sets resource.
     *
     * @param resource the resource
     */
    public void setResource( String resource ) {
        this.resource = resource;
    }

    /**
     * Gets operation.
     *
     * @return the operation
     */
    public String getOperation( ) {
        return operation;
    }

    /**
     * Sets operation.
     *
     * @param operation the operation
     */
    public void setOperation( String operation ) {
        this.operation = operation;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Privileges ) ) return false;
        Privileges that = ( Privileges ) o;
        return Objects.equals( getId( ), that.getId( ) );
    }

    @Override
    protected Object clone( ) throws CloneNotSupportedException {
        return super.clone( );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( getId( ) );
    }

    @Override
    public String toString( ) {
        return "Privileges{" +
                "id='" + id + '\'' +
                ", resource='" + resource + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
