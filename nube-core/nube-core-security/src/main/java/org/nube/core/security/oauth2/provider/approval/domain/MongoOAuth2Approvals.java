/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:30
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.approval.domain;

import org.nube.core.security.oauth2.provider.approval.OAuth2Approvals;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

/**
 * OAuth2 Approvals for management by MongoDB Provider.
 * <p>
 * MongoDB referenced document name: {@code oauth2Approvals}
 *
 * @author Rubén García Ríos
 */
@Document( collection = "oauth2Approvals" )
public class MongoOAuth2Approvals
        extends OAuth2Approvals {
    private static final long serialVersionUID = -4020704363869562539L;
    // CONSTANTS.
    /**
     * USER_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String USER_ID = "userId";
    /**
     * CLIENT_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String CLIENT_ID = "clientId";
    /**
     * SCOPE constant, indicates the name of field in MongoDB document.
     */
    public static final String SCOPE = "scope";
    /**
     * STATUS_CODE constant, indicates the name of field in MongoDB document.
     */
    public static final String STATUS_CODE = "statusCode";
    /**
     * EXPIRES_AT constant, indicates the name of field in MongoDB document.
     */
    public static final String EXPIRES_AT = "expiresAt";
    /**
     * LAST_MODIFIED_AT constant, indicates the name of field in MongoDB document.
     */
    public static final String LAST_MODIFIED_AT = "lastModifiedAt";
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    //@formatter:off
    /**
     * Instantiates a new Mongo OAuth2 Approvals.
     */
    public MongoOAuth2Approvals( )
        { super( ); }

    /**
     * Instantiates a new O auth 2 approvals.
     *
     * @param userId         the user id
     * @param clientId       the client id
     * @param scope          the scope
     * @param statusCode     the status code
     * @param expiresAt      the expires at
     * @param lastModifiedAt the last modified at
     */
    public MongoOAuth2Approvals(
            String userId,
            String clientId,
            String scope,
            String statusCode,
            Date expiresAt,
            Date lastModifiedAt )
        { super( userId, clientId, scope, statusCode, expiresAt, lastModifiedAt ); }

    /**
     * Instantiates a new Mongo OAuth2 Approvals.
     *
     * @param oAuth2Approvals the o auth 2 approvals
     */
    public MongoOAuth2Approvals(
            OAuth2Approvals oAuth2Approvals ) {
        super( oAuth2Approvals.getUserId( ),
               oAuth2Approvals.getClientId( ),
               oAuth2Approvals.getScope( ),
               oAuth2Approvals.getStatusCode( ),
               oAuth2Approvals.getExpiresAt( ),
               oAuth2Approvals.getLastModifiedAt( ) );
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

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoOAuth2Approvals ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoOAuth2Approvals that = ( MongoOAuth2Approvals ) o;
        return Objects.equals( getId( ), that.getId( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( super.hashCode( ), getId( ) ); }
    //@formatter:on
}
