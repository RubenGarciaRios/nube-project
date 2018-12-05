/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:30
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.approval;

import org.nube.core.base.security.NubeSecurityObject;

import java.util.Date;
import java.util.Objects;

/**
 * OAuth 2 Approvals.
 *
 * @author Rubén García Ríos
 */
public abstract class OAuth2Approvals
        extends NubeSecurityObject {
    private static final long serialVersionUID = -5457200537695759131L;
    // ATTRIBUTES.
    private String userId;
    private String clientId;
    private String scope;
    private String statusCode;
    private Date expiresAt;
    private Date lastModifiedAt;
    /**
     * Instantiates a new O auth 2 approvals.
     */
    public OAuth2Approvals( ) { }

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
    public OAuth2Approvals(
            final String userId,
            final String clientId,
            final String scope,
            final String statusCode,
            final Date expiresAt,
            final Date lastModifiedAt ) {
        this.userId = userId;
        this.clientId = clientId;
        this.scope = scope;
        this.statusCode = statusCode;
        this.expiresAt = expiresAt;
        this.lastModifiedAt = lastModifiedAt;
    }
    //@formatter:off
    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId( )
        { return userId; }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId( String userId )
        { this.userId = userId; }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public String getClientId( )
        { return clientId; }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId( String clientId )
        { this.clientId = clientId; }

    /**
     * Gets scope.
     *
     * @return the scope
     */
    public String getScope( )
        { return scope; }

    /**
     * Sets scope.
     *
     * @param scope the scope
     */
    public void setScope( String scope )
        { this.scope = scope; }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public String getStatusCode( )
        { return statusCode; }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode( String statusCode )
        { this.statusCode = statusCode; }

    /**
     * Gets expires at.
     *
     * @return the expires at
     */
    public Date getExpiresAt( )
        { return expiresAt; }

    /**
     * Sets expires at.
     *
     * @param expiresAt the expires at
     */
    public void setExpiresAt( Date expiresAt )
        { this.expiresAt = expiresAt; }

    /**
     * Gets last modified at.
     *
     * @return the last modified at
     */
    public Date getLastModifiedAt( )
        { return lastModifiedAt; }

    /**
     * Sets last modified at.
     *
     * @param lastModifiedAt the last modified at
     */
    public void setLastModifiedAt( Date lastModifiedAt )
        { this.lastModifiedAt = lastModifiedAt; }


    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof OAuth2Approvals ) ) return false;
        final OAuth2Approvals that = ( OAuth2Approvals ) o;
        return Objects.equals( getUserId( ), that.getUserId( ) ) &&
               Objects.equals( getClientId( ), that.getClientId( ) ) &&
               Objects.equals( getScope( ), that.getScope( ) ) &&
               Objects.equals( getStatusCode( ), that.getStatusCode( ) ) &&
               Objects.equals( getExpiresAt( ), that.getExpiresAt( ) ) &&
               Objects.equals( getLastModifiedAt( ), that.getLastModifiedAt( ) );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( getUserId( ), getClientId( ), getScope( ), getStatusCode( ), getExpiresAt( ), getLastModifiedAt( ) ); }
    //@formatter:on
}
