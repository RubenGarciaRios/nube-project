/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.approval;

import org.nube.core.base.security.NubeSecurityObject;

import java.util.Date;

/**
 * The interface OAuth 2 approvals.
 *
 * @see NubeSecurityObject
 */
public interface OAuth2Approvals extends NubeSecurityObject {

    /**
     * Gets user id.
     *
     * @return the user id
     */
    String getUserId( );

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    void setUserId( String userId );

    /**
     * Gets client id.
     *
     * @return the client id
     */
    String getClientId( );

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    void setClientId( String clientId );

    /**
     * Gets scope.
     *
     * @return the scope
     */
    String getScope( );

    /**
     * Sets scope.
     *
     * @param scope the scope
     */
    void setScope( String scope );

    /**
     * Gets status code.
     *
     * @return the status code
     */
    String getStatusCode( );

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    void setStatusCode( String statusCode );

    /**
     * Gets expires at.
     *
     * @return the expires at
     */
    Date getExpiresAt( );

    /**
     * Sets expires at.
     *
     * @param expiresAt the expires at
     */
    void setExpiresAt( Date expiresAt );

    /**
     * Gets last modified at.
     *
     * @return the last modified at
     */
    Date getLastModifiedAt( );

    /**
     * Sets last modified at.
     *
     * @param lastModifiedAt the last modified at
     */
    void setLastModifiedAt( Date lastModifiedAt );
}
