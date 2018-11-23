/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.approval.domain;

import org.nube.core.security.oauth2.provider.approval.OAuth2Approvals;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

/**
 * The type OAuth 2 approvals.
 */
@Document( collection = "oauth2Approvals" )
public class MongoOAuth2Approvals implements OAuth2Approvals {
    private static final long serialVersionUID = -4020704363869562539L;

    /**
     * The constant USER_ID.
     */
    public static final String USER_ID = "userId";
    /**
     * The constant CLIENT_ID.
     */
    public static final String CLIENT_ID = "clientId";
    /**
     * The constant SCOPE.
     */
    public static final String SCOPE = "scope";
    /**
     * The constant STATUS_CODE.
     */
    public static final String STATUS_CODE = "statusCode";
    /**
     * The constant EXPIRES_AT.
     */
    public static final String EXPIRES_AT = "expiresAt";
    /**
     * The constant LAST_MODIFIED_AT.
     */
    public static final String LAST_MODIFIED_AT = "lastModifiedAt";
    //////////////////
    @Indexed
    private String id;
    //////////////////
    private String userId;
    private String clientId;
    private String scope;
    private String statusCode;
    private Date expiresAt;
    private Date lastModifiedAt;

    /**
     * Instantiates a new O auth 2 approvals.
     */
    public MongoOAuth2Approvals( ) {
    }

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
            Date lastModifiedAt ) {
        this.userId = userId;
        this.clientId = clientId;
        this.scope = scope;
        this.statusCode = statusCode;
        this.expiresAt = expiresAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    /**
     * Instantiates a new O auth 2 approvals.
     *
     * @param oAuth2Approvals the o auth 2 approvals
     */
    public MongoOAuth2Approvals(
            OAuth2Approvals oAuth2Approvals ) {
        this.userId = oAuth2Approvals.getUserId( );
        this.clientId = oAuth2Approvals.getClientId( );
        this.scope = oAuth2Approvals.getScope( );
        this.statusCode = oAuth2Approvals.getStatusCode( );
        this.expiresAt = oAuth2Approvals.getExpiresAt( );
        this.lastModifiedAt = oAuth2Approvals.getLastModifiedAt( );
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
    public String getUserId( ) {
        return userId;
    }

    @Override
    public void setUserId( String userId ) {
        this.userId = userId;
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
    public String getScope( ) {
        return scope;
    }

    @Override
    public void setScope( String scope ) {
        this.scope = scope;
    }

    @Override
    public String getStatusCode( ) {
        return statusCode;
    }

    @Override
    public void setStatusCode( String statusCode ) {
        this.statusCode = statusCode;
    }

    @Override
    public Date getExpiresAt( ) {
        return expiresAt;
    }

    @Override
    public void setExpiresAt( Date expiresAt ) {
        this.expiresAt = expiresAt;
    }

    @Override
    public Date getLastModifiedAt( ) {
        return lastModifiedAt;
    }

    @Override
    public void setLastModifiedAt( Date lastModifiedAt ) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoOAuth2Approvals ) ) return false;
        MongoOAuth2Approvals that = ( MongoOAuth2Approvals ) o;
        return Objects.equals( getId( ), that.getId( ) ) &&
                Objects.equals( getUserId( ), that.getUserId( ) ) &&
                Objects.equals( getClientId( ), that.getClientId( ) ) &&
                Objects.equals( getScope( ), that.getScope( ) ) &&
                Objects.equals( getStatusCode( ), that.getStatusCode( ) ) &&
                Objects.equals( getExpiresAt( ), that.getExpiresAt( ) ) &&
                Objects.equals( getLastModifiedAt( ), that.getLastModifiedAt( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash(
                getId( ),
                getUserId( ),
                getClientId( ),
                getScope( ),
                getStatusCode( ),
                getExpiresAt( ),
                getLastModifiedAt( ) );
    }

    @Override
    public String toString( ) {
        return "MongoOAuth2Approvals{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", scope='" + scope + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", expiresAt=" + expiresAt +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }
}
