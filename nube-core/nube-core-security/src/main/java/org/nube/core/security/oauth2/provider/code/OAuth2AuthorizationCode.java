/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:07
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.code;

import org.nube.core.base.security.NubeSecurityObject;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * OAuth2 Authorization Code.
 *
 * @author Rubén García Ríos.
 */
public abstract class OAuth2AuthorizationCode
        extends NubeSecurityObject {
    private static final long serialVersionUID = -5335689427938648931L;
    // ATTRIBUTES.
    private String code;
    private OAuth2Authentication authentication;

    /**
     * Instantiates a new OAuth2 Authorization Code.
     */
    public OAuth2AuthorizationCode( ) { }

    /**
     * Instantiates a new OAuth2 Authorization Code.
     *
     * @param code           the code
     * @param authentication the authentication
     */
    public OAuth2AuthorizationCode( String code, OAuth2Authentication authentication ) {
        this.code = code;
        this.authentication = authentication;
    }

    /**
     * Instantiates a new OAuth2 Authorization Code.
     *
     * @param oAuth2AuthorizationCode the o auth 2 authorization code
     */
    public OAuth2AuthorizationCode( OAuth2AuthorizationCode oAuth2AuthorizationCode ) {
        this.code = oAuth2AuthorizationCode.getCode( );
        this.authentication = oAuth2AuthorizationCode.getAuthentication( );
    }
    //@formatter:off
    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode( )
        { return code; }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode( String code )
        { this.code = code; }

    /**
     * Gets authentication.
     *
     * @return the authentication
     */
    public OAuth2Authentication getAuthentication( )
        { return authentication; }

    /**
     * Sets authentication.
     *
     * @param authentication the authentication
     */
    public void setAuthentication( OAuth2Authentication authentication )
        { this.authentication = authentication; }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof OAuth2AuthorizationCode ) ) return false;
        final OAuth2AuthorizationCode that = ( OAuth2AuthorizationCode ) o;
        return Objects.equals( getCode( ), that.getCode( ) ) &&
               Objects.equals( getAuthentication( ), that.getAuthentication( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getCode( ), getAuthentication( ) ); }
    //@formatter:on
}
