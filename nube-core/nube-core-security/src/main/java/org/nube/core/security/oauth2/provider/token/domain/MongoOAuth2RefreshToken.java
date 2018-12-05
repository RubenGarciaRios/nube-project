/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:43
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.domain;

import org.nube.core.security.oauth2.provider.token.OAuth2RefreshToken;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * OAuth2 Refresh Token for management by MongoDB Provider.
 * <p>
 * MongoDB referenced document name: {@code oauth2RefreshTokens}
 *
 * @author Rubén García Ríos
 */
@Document( collection = "oauth2RefreshTokens" )
public class MongoOAuth2RefreshToken
        extends OAuth2RefreshToken {
    private static final long serialVersionUID = -1554139895650993739L;
    // CONSTANTS.
    /**
     * TOKEN_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String TOKEN_ID = "tokenId";
    /**
     * TOKEN constant, indicates the name of field in MongoDB document.
     */
    public static final String TOKEN = "token";
    /**
     * AUTHENTICATION constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHENTICATION = "authentication";
    // ATTRIBUTES.
    ////////////////////////
    @Indexed
    private String id;
    ////////////////////////
    //@formatter:off
    /**
     * Instantiates a new Mongo OAuth2 Refresh Token.
     *
     * @param token          the token
     * @param authentication the authentication
     */
    public MongoOAuth2RefreshToken(
            org.springframework.security.oauth2.common.OAuth2RefreshToken token,
            OAuth2Authentication authentication )
        { super( token, authentication ); }

    public String getId( )
        { return id; }

    public void setId( final String id )
        { this.id = id; }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

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
    public int hashCode( )
        { return Objects.hash( getId( ), getTokenId( ), getToken( ), getAuthentication( ) ); }
    //@formatter:on
}
