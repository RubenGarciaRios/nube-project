/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:43
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token;

import org.nube.core.base.security.NubeSecurityObject;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * OAuth2 Refresh Token.
 *
 * @author Rubén García Ríos
 */
public abstract class OAuth2RefreshToken
        extends NubeSecurityObject {
    private static final long serialVersionUID = -8858298508051681961L;
    // ATTRIBUTES.
    private final String tokenId;
    private final org.springframework.security.oauth2.common.OAuth2RefreshToken token;
    private final OAuth2Authentication authentication;

    /**
     * Instantiates a new OAuth2 Refresh Token.
     *
     * @param token          the token
     * @param authentication the authentication
     */
    public OAuth2RefreshToken(
            org.springframework.security.oauth2.common.OAuth2RefreshToken token,
            OAuth2Authentication authentication ) {
        this.tokenId = token.getValue( );
        this.token = token;
        this.authentication = authentication;
    }
    //@formatter:off
    /**
     * Gets token id.
     *
     * @return the token id
     */
    public String getTokenId( )
        { return tokenId; }

    /**
     * Gets token.
     *
     * @return the token
     */
    public org.springframework.security.oauth2.common.OAuth2RefreshToken getToken( )
        { return token; }

    /**
     * Gets authentication.
     *
     * @return the authentication
     */
    public OAuth2Authentication getAuthentication( )
        { return authentication; }

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
        if ( !( o instanceof OAuth2RefreshToken ) ) return false;
        final OAuth2RefreshToken that = ( OAuth2RefreshToken ) o;
        return Objects.equals( getTokenId( ), that.getTokenId( ) ) &&
               Objects.equals( getToken( ), that.getToken( ) ) &&
               Objects.equals( getAuthentication( ), that.getAuthentication( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getTokenId( ), getToken( ), getAuthentication( ) ); }
    //@formatter:on
}
