/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.domain;

import org.nube.core.security.oauth2.provider.token.AbstractOAuth2Token;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * The type Mongo OAuth 2 access token.
 *
 * @see org.nube.core.security.oauth2.provider.token.OAuth2AccessToken
 * @see AbstractOAuth2Token
 */
@Document( collection = "oauth2AccessTokens" )
public class MongoOAuth2AccessToken extends AbstractOAuth2Token
        implements org.nube.core.security.oauth2.provider.token.OAuth2AccessToken {
    private static final long serialVersionUID = -476646046483429970L;

    /**
     * The constant TOKEN_ID.
     */
    public static final String TOKEN_ID = "tokenId";
    /**
     * The constant REFRESH_TOKEN.
     */
    public static final String REFRESH_TOKEN = "refreshToken";
    /**
     * The constant AUTHENTICATION_ID.
     */
    public static final String AUTHENTICATION_ID = "authenticationId";
    /**
     * The constant CLIENT_ID.
     */
    public static final String CLIENT_ID = "clientId";
    /**
     * The constant USERNAME.
     */
    public static final String USERNAME = "userName";
    //////////////////
    @Indexed
    private String id;
    //////////////////

    /**
     * Instantiates a new Mongo o auth 2 access token.
     */
    public MongoOAuth2AccessToken( ) {
        super( );
    }

    /**
     * Instantiates a new Mongo o auth 2 access token.
     *
     * @param token            the token
     * @param authenticationId the authentication id
     * @param authentication   the authentication
     */
    public MongoOAuth2AccessToken(
            final OAuth2AccessToken token,
            final String authenticationId,
            final OAuth2Authentication authentication ) {
        super( token, authenticationId, authentication );
    }

    /**
     * Instantiates a new Mongo o auth 2 access token.
     *
     * @param oAuth2AccessToken the o auth 2 access token
     */
    public MongoOAuth2AccessToken(
            final org.nube.core.security.oauth2.provider.token.OAuth2AccessToken oAuth2AccessToken ) {
        super( oAuth2AccessToken );
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoOAuth2AccessToken ) ) return false;
        MongoOAuth2AccessToken that = ( MongoOAuth2AccessToken ) o;
        return Objects.equals( getId( ), that.getId( ) ) &&
                Objects.equals( getTokenId( ), that.getTokenId( ) ) &&
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
                getId( ),
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
        return "MongoOAuth2AccessToken{" +
                "id='" + id + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", token=" + token +
                ", authenticationId='" + authenticationId + '\'' +
                ", userName='" + userName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", authentication=" + authentication +
                ", refreshToken=" + refreshToken +
                '}';
    }
}
