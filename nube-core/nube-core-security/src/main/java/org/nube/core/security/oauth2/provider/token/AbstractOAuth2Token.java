/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * The type Mongo OAuth 2 token.
 *
 * @see OAuth2Token
 */
public abstract class AbstractOAuth2Token implements OAuth2Token {
    private static final long serialVersionUID = -476646046483429970L;

    protected String tokenId;
    protected OAuth2AccessToken token;
    protected String authenticationId;
    protected String userName;
    protected String clientId;
    protected OAuth2Authentication authentication;
    protected String refreshToken;

    /**
     * Instantiates a new Mongo OAuth 2 access token.
     */
    public AbstractOAuth2Token( ) {
    }

    /**
     * Instantiates a new Mongo OAuth 2 access token.
     *
     * @param token            the token
     * @param authenticationId the authentication id
     * @param authentication   the authentication
     */
    public AbstractOAuth2Token(
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
     * Instantiates a new Mongo OAuth 2 access token.
     *
     * @param oAuth2Token the OAuth 2 access token
     */
    public AbstractOAuth2Token(
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
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    @Override
    public String getTokenId( ) {
        return tokenId;
    }

    @Override
    public void setTokenId( String tokenId ) {
        this.tokenId = tokenId;
    }

    @Override
    public OAuth2AccessToken getToken( ) {
        return token;
    }

    @Override
    public void setToken( OAuth2AccessToken token ) {
        this.token = token;
    }

    @Override
    public String getAuthenticationId( ) {
        return authenticationId;
    }

    @Override
    public void setAuthenticationId( String authenticationId ) {
        this.authenticationId = authenticationId;
    }

    @Override
    public String getUserName( ) {
        return userName;
    }

    @Override
    public void setUserName( String userName ) {
        this.userName = userName;
    }

    @Override
    public String getClientId( ) {
        return clientId;
    }

    @Override
    public void setClientId( String clientId ) {
        this.clientId = clientId;
    }

    @Override
    public OAuth2Authentication getAuthentication( ) {
        return authentication;
    }

    @Override
    public void setAuthentication( OAuth2Authentication authentication ) {
        this.authentication = authentication;
    }

    @Override
    public String getRefreshToken( ) {
        return refreshToken;
    }

    @Override
    public void setRefreshToken( String refreshToken ) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof AbstractOAuth2Token ) ) return false;
        AbstractOAuth2Token that = ( AbstractOAuth2Token ) o;
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
                getTokenId( ),
                getToken( ),
                getAuthenticationId( ),
                getUserName( ),
                getClientId( ),
                getAuthentication( ),
                getRefreshToken( ) );
    }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) + "{" +
                "tokenId='" + tokenId + '\'' +
                ", token=" + token +
                ", authenticationId='" + authenticationId + '\'' +
                ", userName='" + userName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", authentication=" + authentication +
                ", refreshToken=" + refreshToken +
                '}';
    }
}
