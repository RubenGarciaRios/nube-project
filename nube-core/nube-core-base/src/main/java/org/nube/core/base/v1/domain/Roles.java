/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.v1.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

/**
 * The type Roles.
 *
 * @see NubeDomainObject
 */
@Document
public class Roles implements NubeDomainObject {
    private static final long serialVersionUID = 3599486512069551406L;
    @Id
    private String id;
    @NotNull
    private boolean enabled;
    @NotNull
    private boolean active;
    @NotNull
    @Size( min = 1 )
    private Collection< Privileges > privileges;

    /**
     * Instantiates a new Roles.
     */
    public Roles( ) {
    }

    /**
     * Instantiates a new Roles.
     *
     * @param id         the id
     * @param enabled    the enabled
     * @param active     the active
     * @param privileges the privileges
     */
    @PersistenceConstructor
    public Roles(
            final String id,
            @NotNull final boolean enabled,
            @NotNull boolean active,
            @NotNull @Size( min = 1 ) final Collection< Privileges > privileges ) {
        this.id = id;
        this.enabled = enabled;
        this.active = active;
        this.privileges = privileges;
    }

    /**
     * Instantiates a new Roles.
     *
     * @param roles the roles
     */
    public Roles( Roles roles ) {
        this.id = roles.id;
        this.enabled = roles.enabled;
        this.active = roles.active;
        this.privileges = roles.privileges;
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
     * Is enabled boolean.
     *
     * @return the boolean
     */
    public boolean isEnabled( ) {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled( boolean enabled ) {
        this.enabled = enabled;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive( ) {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive( boolean active ) {
        this.active = active;
    }

    /**
     * Gets privileges.
     *
     * @return the privileges
     */
    public Collection< Privileges > getPrivileges( ) {
        return privileges;
    }

    /**
     * Sets privileges.
     *
     * @param privileges the privileges
     */
    public void setPrivileges( Collection< Privileges > privileges ) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Roles ) ) return false;
        Roles roles = ( Roles ) o;
        return Objects.equals( getId( ), roles.getId( ) );
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
        return "Roles{" +
                "id='" + id + '\'' +
                ", enabled=" + enabled +
                ", active=" + active +
                ", privileges=" + privileges +
                '}';
    }
}
