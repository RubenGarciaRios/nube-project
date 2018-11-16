/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.domain;

import org.nube.core.security.oauth2.provider.token.AbstractOAuth2Token;
import org.nube.core.security.oauth2.provider.token.OAuth2ClientToken;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * The type OAuth 2 client token.
 *
 * @see OAuth2ClientToken
 * @see AbstractOAuth2Token
 */
@Document( collection = "oauth2ClientTokens" )
public class MongoOAuth2ClientToken extends AbstractOAuth2Token
        implements OAuth2ClientToken {
    private static final long serialVersionUID = 7313275873324236187L;

    private static final String TOKEN = "token";
    private static final String AUTHENTICATION_ID = "authenticationId";
    private static final String USER_NAME = "userName";
    private static final String CLIENT_ID = "clientId";
    private static final String AUTHENTICATION = "authentication";
    private static final String REFRESH_TOKEN = "refreshToken";
    //////////////////
    @Indexed
    private String id;
    //////////////////

    /**
     * Instantiates a new Mongo OAuth 2 client token.
     */
    public MongoOAuth2ClientToken( ) {
        super( );
    }

    /**
     * Instantiates a new Mongo OAuth 2 client token.
     *
     * @param token            the token
     * @param authenticationId the authentication id
     * @param authentication   the authentication
     */
    public MongoOAuth2ClientToken(
            final OAuth2AccessToken token,
            final String authenticationId,
            final OAuth2Authentication authentication ) {
        super( token, authenticationId, authentication );
    }

    /**
     * Instantiates a new Mongo OAuth 2 client token.
     *
     * @param oAuth2ClientToken the OAuth 2 client token
     */
    public MongoOAuth2ClientToken(
            final OAuth2ClientToken oAuth2ClientToken ) {
        super( oAuth2ClientToken );
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
        if ( !( o instanceof MongoOAuth2ClientToken ) ) return false;
        MongoOAuth2ClientToken that = ( MongoOAuth2ClientToken ) o;
        return Objects.equals( getId( ), that.getId( ) ) &&
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
                getToken( ),
                getAuthenticationId( ),
                getUserName( ),
                getClientId( ),
                getAuthentication( ),
                getRefreshToken( ) );
    }

    @Override
    public String toString( ) {
        return "MongoOAuth2ClientToken{" +
                "id='" + id + '\'' +
                ", token=" + token +
                ", authenticationId='" + authenticationId + '\'' +
                ", userName='" + userName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", authentication=" + authentication +
                ", refreshToken=" + refreshToken +
                '}';
    }
}
