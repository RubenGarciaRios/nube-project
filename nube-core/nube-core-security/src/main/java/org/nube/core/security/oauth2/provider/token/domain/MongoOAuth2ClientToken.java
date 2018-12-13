/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 12:30
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
 * OAuth2 Client Token for management by MongoDB DataProviderType.
 * <p>
 * MongoDB referenced document name: {@code oauth2ClientTokens}
 *
 * @author Rubén García Ríos
 */
@Document( collection = "oauth2ClientTokens" )
public class MongoOAuth2ClientToken
        extends OAuth2Token {
    private static final long serialVersionUID = 7313275873324236187L;

    /**
     * TOKEN constant, indicates the name of field in MongoDB document.
     */
    public static final String TOKEN = "token";
    /**
     * AUTHENTICATION_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHENTICATION_ID = "authenticationId";
    /**
     * USER_NAME constant, indicates the name of field in MongoDB document.
     */
    public static final String USER_NAME = "userName";
    /**
     * CLIENT_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String CLIENT_ID = "clientId";
    /**
     * AUTHENTICATION constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHENTICATION = "authentication";
    /**
     * REFRESH_TOKEN constant, indicates the name of field in MongoDB document.
     */
    public static final String REFRESH_TOKEN = "refreshToken";
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    /**
     * Instantiates a new Mongo OAuth2 Client Token.
     */
    public MongoOAuth2ClientToken( )
        { super( ); }

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
            final OAuth2Authentication authentication )
        { super( token, authenticationId, authentication ); }

    /**
     * Instantiates a new Mongo OAuth2 Client Token.
     *
     * @param oAuth2Token the OAuth2 Token
     */
    public MongoOAuth2ClientToken(
            final OAuth2Token oAuth2Token )
        { super( oAuth2Token ); }

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
        if ( !( o instanceof MongoOAuth2ClientToken ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoOAuth2ClientToken that = ( MongoOAuth2ClientToken ) o;
        return Objects.equals( getId( ), that.getId( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( super.hashCode( ), getId( ) );
    }
}
