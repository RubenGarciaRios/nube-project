/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * The type OAuth 2 refresh token.
 *
 * @see org.nube.core.security.oauth2.provider.token.OAuth2RefreshToken
 */
@Document( collection = "oauth2RefreshTokens" )
public class MongoOAuth2RefreshToken implements org.nube.core.security.oauth2.provider.token.OAuth2RefreshToken {
    private static final long serialVersionUID = -1554139895650993739L;

    /**
     * The constant TOKEN_ID.
     */
    public static final String TOKEN_ID = "tokenId";
    /**
     * The constant TOKEN.
     */
    public static final String TOKEN = "token";
    /**
     * The constant AUTHENTICATION.
     */
    public static final String AUTHENTICATION = "authentication";
    ////////////////////////
    @Indexed
    private String id;
    ////////////////////////
    private final String tokenId;
    private final OAuth2RefreshToken token;
    private final OAuth2Authentication authentication;

    /**
     * Instantiates a new O auth 2 refresh token.
     *
     * @param token          the token
     * @param authentication the authentication
     */
    public MongoOAuth2RefreshToken(
            OAuth2RefreshToken token,
            OAuth2Authentication authentication ) {
        this.tokenId = token.getValue( );
        this.token = token;
        this.authentication = authentication;
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
    public String getTokenId( ) {
        return tokenId;
    }

    @Override
    public OAuth2RefreshToken getToken( ) {
        return token;
    }

    @Override
    public OAuth2Authentication getAuthentication( ) {
        return authentication;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoOAuth2RefreshToken ) ) return false;
        MongoOAuth2RefreshToken that = ( MongoOAuth2RefreshToken ) o;
        return Objects.equals( getId( ), that.getId( ) ) &&
                Objects.equals( getTokenId( ), that.getTokenId( ) ) &&
                Objects.equals( getToken( ), that.getToken( ) ) &&
                Objects.equals( getAuthentication( ), that.getAuthentication( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( getId( ), getTokenId( ), getToken( ), getAuthentication( ) );
    }

    @Override
    public String toString( ) {
        return "MongoOAuth2RefreshToken{" +
                "id='" + id + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", token=" + token +
                ", authentication=" + authentication +
                '}';
    }
}
