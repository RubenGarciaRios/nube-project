/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 10:54
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.v1.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * The type User accounts.
 */
@Document( collection = "userAccounts" )
public class UserAccounts
        implements NubeDomainObject {
    private static final long serialVersionUID = -344502756189120672L;
    @Id
    private String id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size( min = 8 )
    private String password;
    @NotNull
    private boolean enabled;
    @NotNull
    private boolean locked;
    @NotNull
    private boolean expired;
    @NotNull
    private boolean reset;
    @NotNull
    private Collection< Roles > roles;
    @NotNull
    private UserProfile userProfile;
    @NotNull
    @NotEmpty
    @NotBlank
    private Date creationDate;
    private Date expirationDate;

    /**
     * Instantiates a new User accounts.
     */
    public UserAccounts( ) {
    }

    /**
     * Instantiates a new User accounts.
     *
     * @param id           the id
     * @param email        the email
     * @param password     the password
     * @param enabled      the enabled
     * @param locked       the locked
     * @param expired      the expired
     * @param reset        the reset
     * @param roles        the roles
     * @param userProfile  the user profile
     * @param creationDate the creation date
     */
    public UserAccounts(
            final String id,
            @NotNull @NotBlank @NotEmpty @Email final String email,
            @NotNull @NotBlank @NotEmpty @Size( min = 8 ) final String password,
            @NotNull final boolean enabled,
            @NotNull final boolean locked,
            @NotNull final boolean expired,
            @NotNull final boolean reset,
            @NotNull final Collection< Roles > roles,
            @NotNull final UserProfile userProfile,
            @NotNull @NotEmpty @NotBlank final Date creationDate ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.locked = locked;
        this.expired = expired;
        this.reset = reset;
        this.roles = roles;
        this.userProfile = userProfile;
        this.creationDate = creationDate;
    }

    /**
     * Instantiates a new User accounts.
     *
     * @param id             the id
     * @param email          the email
     * @param password       the password
     * @param enabled        the enabled
     * @param locked         the locked
     * @param expired        the expired
     * @param reset          the reset
     * @param roles          the roles
     * @param userProfile    the user profile
     * @param creationDate   the creation date
     * @param expirationDate the expiration date
     */
    @PersistenceConstructor
    public UserAccounts(
            final String id,
            @NotNull @NotBlank @NotEmpty @Email final String email,
            @NotNull @NotBlank @NotEmpty @Size( min = 8 ) final String password,
            @NotNull final boolean enabled,
            @NotNull final boolean locked,
            @NotNull final boolean expired,
            @NotNull final boolean reset,
            @NotNull final Collection< Roles > roles,
            @NotNull final UserProfile userProfile,
            @NotNull @NotEmpty @NotBlank final Date creationDate,
            final Date expirationDate ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.locked = locked;
        this.expired = expired;
        this.reset = reset;
        this.roles = roles;
        this.userProfile = userProfile;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    /**
     * Instantiates a new User accounts.
     *
     * @param userAccount the user account
     */
    public UserAccounts( final UserAccounts userAccount ) {
        this.id = userAccount.id;
        this.email = userAccount.email;
        this.password = userAccount.password;
        this.enabled = userAccount.enabled;
        this.locked = userAccount.locked;
        this.expired = userAccount.expired;
        this.reset = userAccount.reset;
        this.roles = userAccount.roles;
        this.userProfile = userAccount.userProfile;
        this.creationDate = userAccount.creationDate;
        this.expirationDate = userAccount.expirationDate;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail( ) {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword( ) {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword( String password ) {
        this.password = password;
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
     * Is locked boolean.
     *
     * @return the boolean
     */
    public boolean isLocked( ) {
        return locked;
    }

    /**
     * Sets locked.
     *
     * @param locked the locked
     */
    public void setLocked( boolean locked ) {
        this.locked = locked;
    }

    /**
     * Is expired boolean.
     *
     * @return the boolean
     */
    public boolean isExpired( ) {
        return expired;
    }

    /**
     * Sets expired.
     *
     * @param expired the expired
     */
    public void setExpired( boolean expired ) {
        this.expired = expired;
    }

    /**
     * Is reset boolean.
     *
     * @return the boolean
     */
    public boolean isReset( ) {
        return reset;
    }

    /**
     * Sets reset.
     *
     * @param reset the reset
     */
    public void setReset( boolean reset ) {
        this.reset = reset;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Collection< Roles > getRoles( ) {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles( Collection< Roles > roles ) {
        this.roles = roles;
    }

    /**
     * Gets user profile.
     *
     * @return the user profile
     */
    public UserProfile getUserProfile( ) {
        return userProfile;
    }

    /**
     * Sets user profile.
     *
     * @param userProfile the user profile
     */
    public void setUserProfile( UserProfile userProfile ) {
        this.userProfile = userProfile;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate( ) {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate( Date creationDate ) {
        this.creationDate = creationDate;
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    public Date getExpirationDate( ) {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate( Date expirationDate ) {
        this.expirationDate = expirationDate;
    }

    /**
     * Update.
     *
     * @param userAccount the user account
     */
    public void update( final UserAccounts userAccount ) {
        this.password = userAccount.password;
        this.enabled = userAccount.enabled;
        this.locked = userAccount.locked;
        this.expired = userAccount.expired;
        this.reset = userAccount.reset;
        this.roles = userAccount.roles;
        this.userProfile = userAccount.userProfile;
        this.creationDate = userAccount.creationDate;
        this.expirationDate = userAccount.expirationDate;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof UserAccounts ) ) return false;
        UserAccounts that = ( UserAccounts ) o;
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
}
