/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:21
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token;

import org.nube.core.base.security.NubeSecurityObject;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * OAuth2 Token.
 *
 * @author Rubén García Ríos
 */
public abstract class OAuth2Token
        extends NubeSecurityObject {
    private static final long serialVersionUID = 4506311285035569844L;
    // ATTRIBUTES.
    /**
     * The Token id.
     */
    private String tokenId;
    /**
     * The Token.
     */
    private OAuth2AccessToken token;
    /**
     * The Authentication id.
     */
    private String authenticationId;
    /**
     * The User name.
     */
    private String userName;
    /**
     * The Client id.
     */
    private String clientId;
    /**
     * The Authentication.
     */
    private OAuth2Authentication authentication;
    /**
     * The Refresh token.
     */
    private String refreshToken;
    /**
     * Instantiates a new OAuth2 Token.
     */
    public OAuth2Token( ) { }

    /**
     * Instantiates a new OAuth2 Token.
     *
     * @param token            the token
     * @param authenticationId the authentication id
     * @param authentication   the authentication
     */
    public OAuth2Token(
            final OAuth2AccessToken token,
            final String authenticationId,
            final OAuth2Authentication authentication ) {
        this.tokenId = token.getValue( );
        this.token = token;
        this.authenticationId = authenticationId;
        this.userName = authentication.isClientOnly( ) ? null : authentication.getName( );
        this.clientId = authentication.getOAuth2Request( ).getClientId( );
        this.authentication = authentication;
        this.refreshToken = token.getRefreshToken( ).getValue( );
    }

    /**
     * Instantiates a new OAuth2 Token.
     *
     * @param oAuth2Token the OAuth2 Token
     */
    public OAuth2Token(
            final OAuth2Token oAuth2Token ) {
        this.tokenId = oAuth2Token.getTokenId( );
        this.token = oAuth2Token.getToken( );
        this.authenticationId = oAuth2Token.getAuthenticationId( );
        this.userName = oAuth2Token.getUserName( );
        this.clientId = oAuth2Token.getClientId( );
        this.authentication = oAuth2Token.getAuthentication( );
        this.refreshToken = oAuth2Token.getRefreshToken( );
    }

    /**
     * Gets token id.
     *
     * @return the token id
     */
    public String getTokenId( )
        { return tokenId; }

    /**
     * Sets token id.
     *
     * @param tokenId the token id
     */
    public void setTokenId( String tokenId )
        { this.tokenId = tokenId; }


    /**
     * Gets token.
     *
     * @return the token
     */
    public OAuth2AccessToken getToken( )
        { return token; }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken( OAuth2AccessToken token )
        { this.token = token; }

    /**
     * Gets authentication id.
     *
     * @return the authentication id
     */
    public String getAuthenticationId( )
        { return authenticationId; }

    /**
     * Sets authentication id.
     *
     * @param authenticationId the authentication id
     */
    public void setAuthenticationId( String authenticationId )
        { this.authenticationId = authenticationId; }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName( )
        { return userName; }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName( String userName )
        { this.userName = userName; }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public String getClientId( )
        { return clientId; }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId( String clientId )
        { this.clientId = clientId; }

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
     * Gets refresh token.
     *
     * @return the refresh token
     */
    public String getRefreshToken( )
        { return refreshToken; }

    /**
     * Sets refresh token.
     *
     * @param refreshToken the refresh token
     */
    public void setRefreshToken( String refreshToken )
        { this.refreshToken = refreshToken; }

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
        if ( !( o instanceof OAuth2Token ) ) return false;
        final OAuth2Token that = ( OAuth2Token ) o;
        return Objects.equals( getTokenId( ), that.getTokenId( ) ) &&
               Objects.equals( getToken( ), that.getToken( ) ) &&
               Objects.equals( getAuthenticationId( ), that.getAuthenticationId( ) ) &&
               Objects.equals( getUserName( ), that.getUserName( ) ) &&
               Objects.equals( getClientId( ), that.getClientId( ) ) &&
               Objects.equals( getAuthentication( ), that.getAuthentication( ) ) &&
               Objects.equals( getRefreshToken( ), that.getRefreshToken( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash(
               getTokenId( ), getToken( ), getAuthenticationId( ),
               getUserName( ), getClientId( ), getAuthentication( ), getRefreshToken( ) );
    }
}
