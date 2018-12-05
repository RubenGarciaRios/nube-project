/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:29
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.code.domain;

import org.nube.core.security.oauth2.provider.code.OAuth2AuthorizationCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * OAuth2 Authorization Code for management by MongoDB Provider.
 * <p>
 * MongoDB referenced document name: {@code oauth2AuthorizationCodes}
 *
 * @author Rubén García Ríos
 */
@Document( collection = "oauth2AuthorizationCodes" )
public class MongoOAuth2AuthorizationCode
        extends OAuth2AuthorizationCode {
    private static final long serialVersionUID = -3961644135258138139L;
    // CONSTANTS.
    /**
     * CODE constant, indicates the name of field in MongoDB document.
     */
    public static final String CODE = "code";
    /**
     * AUTHENTICATION constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHENTICATION = "authentication";
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    //@formatter:off
    /**
     * Instantiates a new Mongo OAuth2 Authorization Code.
     */
    public MongoOAuth2AuthorizationCode( ) { }

    /**
     * Instantiates a new Mongo OAuth2 Authorization Code.
     *
     * @param code           the code
     * @param authentication the authentication
     */
    public MongoOAuth2AuthorizationCode( String code, OAuth2Authentication authentication )
        { super( code, authentication ); }

    /**
     * Instantiates a new Mongo OAuth2 Authorization Code.
     *
     * @param oAuth2AuthorizationCode the o auth 2 authorization code
     */
    public MongoOAuth2AuthorizationCode( OAuth2AuthorizationCode oAuth2AuthorizationCode )
        { super( oAuth2AuthorizationCode ); }

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
        if ( !( o instanceof MongoOAuth2AuthorizationCode ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoOAuth2AuthorizationCode that = ( MongoOAuth2AuthorizationCode ) o;
        return Objects.equals( getId( ), that.getId( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( super.hashCode( ), getId( ) );
    }
    //@formatter:on
}
