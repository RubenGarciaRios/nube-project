/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:29
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.domain;

import org.nube.core.security.oauth2.provider.token.OAuth2Token;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * OAuth2 Access Token for management by MongoDB Provider.
 * <p>
 * MongoDB referenced document name: {@code oauth2AccessTokens}
 *
 * @author Rubén García Ríos
 */
@Document( collection = "oauth2AccessTokens" )
public class MongoOAuth2AccessToken
        extends OAuth2Token {
    private static final long serialVersionUID = -476646046483429970L;
    // CONSTANTS.
    /**
     * TOKEN_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String TOKEN_ID = "tokenId";
    /**
     * REFRESH_TOKEN constant, indicates the name of field in MongoDB document.
     */
    public static final String REFRESH_TOKEN = "refreshToken";
    /**
     * AUTHENTICATION_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHENTICATION_ID = "authenticationId";
    /**
     * CLIENT_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String CLIENT_ID = "clientId";
    /**
     * USERNAME constant, indicates the name of field in MongoDB document.
     */
    public static final String USERNAME = "userName";
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    //@formatter:off
    /**
     * Instantiates a new Mongo OAuth2 Access Token.
     */
    public MongoOAuth2AccessToken( )
        { super( ); }

    /**
     * Instantiates a new Mongo OAuth2 Access Token.
     *
     * @param token            the token
     * @param authenticationId the authentication id
     * @param authentication   the authentication
     */
    public MongoOAuth2AccessToken(
            final OAuth2AccessToken token,
            final String authenticationId,
            final OAuth2Authentication authentication )
        { super( token, authenticationId, authentication ); }

    /**
     * Instantiates a new Mongo OAuth2 Access Token.
     *
     * @param oAuth2AccessToken the o auth 2 access token
     */
    public MongoOAuth2AccessToken(
            final org.nube.core.security.oauth2.provider.token.OAuth2AccessToken oAuth2AccessToken )
        { super( oAuth2AccessToken ); }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId( )
        { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId( String id )
        { this.id = id; }

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
        if ( !( o instanceof MongoOAuth2AccessToken ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoOAuth2AccessToken that = ( MongoOAuth2AccessToken ) o;
        return Objects.equals( getId( ), that.getId( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( super.hashCode( ), getId( ) ); }
    //@formatter:on
}
