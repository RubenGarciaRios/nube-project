/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.code.domain;

import org.nube.core.security.oauth2.provider.code.OAuth2AuthorizationCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Objects;

/**
 * The type OAuth 2 authorization code.
 *
 * @see OAuth2AuthorizationCode
 */
@Document( collection = "oauth2AuthorizationCodes" )
public class MongoOAuth2AuthorizationCode implements OAuth2AuthorizationCode {
    private static final long serialVersionUID = 2254437867106564785L;

    /**
     * The constant CODE.
     */
    public static final String CODE = "code";
    /**
     * The constant AUTHENTICATION.
     */
    public static final String AUTHENTICATION = "authentication";
    //////////////////
    @Indexed
    private String id;
    //////////////////
    private String code;
    private OAuth2Authentication authentication;

    /**
     * Instantiates a new O auth 2 authorization code.
     */
    public MongoOAuth2AuthorizationCode( ) {
    }

    /**
     * Instantiates a new O auth 2 authorization code.
     *
     * @param code           the code
     * @param authentication the authentication
     */
    public MongoOAuth2AuthorizationCode( String code, OAuth2Authentication authentication ) {
        this.code = code;
        this.authentication = authentication;
    }

    /**
     * Instantiates a new O auth 2 authorization code.
     *
     * @param oAuth2AuthorizationCode the o auth 2 authorization code
     */
    public MongoOAuth2AuthorizationCode( OAuth2AuthorizationCode oAuth2AuthorizationCode ) {
        this.code = oAuth2AuthorizationCode.getCode( );
        this.authentication = oAuth2AuthorizationCode.getAuthentication( );
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
    public String getCode( ) {
        return code;
    }

    @Override
    public void setCode( String code ) {
        this.code = code;
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
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoOAuth2AuthorizationCode ) ) return false;
        MongoOAuth2AuthorizationCode that = ( MongoOAuth2AuthorizationCode ) o;
        return Objects.equals( getId( ), that.getId( ) ) &&
                Objects.equals( getCode( ), that.getCode( ) ) &&
                Objects.equals( getAuthentication( ), that.getAuthentication( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( getId( ), getCode( ), getAuthentication( ) );
    }

    @Override
    public String toString( ) {
        return "MongoOAuth2AuthorizationCode{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", authentication=" + authentication +
                '}';
    }
}
