/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 19:43
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.v1.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The type User profile.
 *
 * @see NubeDomainObject
 */
@Document
public class UserProfile implements NubeDomainObject {
    private static final long serialVersionUID = -2715192768592256173L;
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;
    private String phone;
    private String mobile;

    /**
     * Instantiates a new User profile.
     */
    public UserProfile( ) {
    }

    /**
     * Instantiates a new User profile.
     *
     * @param name     the name
     * @param lastName the last name
     */
    public UserProfile(
            @NotNull @NotBlank @NotEmpty String name,
            @NotNull @NotBlank @NotEmpty String lastName ) {
        this.name = name;
        this.lastName = lastName;
    }

    /**
     * Instantiates a new User profile.
     *
     * @param name     the name
     * @param lastName the last name
     * @param phone    the phone
     * @param mobile   the mobile
     */
    @PersistenceConstructor
    public UserProfile(
            @NotNull @NotBlank @NotEmpty final String name,
            @NotNull @NotBlank @NotEmpty final String lastName,
            final String phone,
            final String mobile ) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.mobile = mobile;
    }

    /**
     * Instantiates a new User profile.
     *
     * @param userProfile the user profile
     */
    public UserProfile( UserProfile userProfile ) {
        this.name = userProfile.name;
        this.lastName = userProfile.lastName;
        this.phone = userProfile.phone;
        this.mobile = userProfile.mobile;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName( ) {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName( ) {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone( ) {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone( String phone ) {
        this.phone = phone;
    }

    /**
     * Gets mobile.
     *
     * @return the mobile
     */
    public String getMobile( ) {
        return mobile;
    }

    /**
     * Sets mobile.
     *
     * @param mobile the mobile
     */
    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof UserProfile ) ) return false;
        UserProfile that = ( UserProfile ) o;
        return Objects.equals( getName( ), that.getName( ) ) &&
                Objects.equals( getLastName( ), that.getLastName( ) );
    }

    @Override
    protected Object clone( ) throws CloneNotSupportedException {
        return super.clone( );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( getName( ), getLastName( ) );
    }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) +
               "{name='" + name + '\'' +
               ", lastName='" + lastName + '\'' +
               ", phone='" + phone + '\'' +
               ", mobile='" + mobile + "'}" +
               META_DATA;
    }
}
