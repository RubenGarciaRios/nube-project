/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token;

import org.nube.core.base.NubeSecurityObject;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * The interface OAuth 2 access token.
 *
 * @see NubeSecurityObject
 */
public interface OAuth2Token extends NubeSecurityObject {
    /**
     * Gets token id.
     *
     * @return the token id
     */
    String getTokenId( );

    /**
     * Sets token id.
     *
     * @param tokenId the token id
     */
    void setTokenId( String tokenId );

    /**
     * Gets token.
     *
     * @return the token
     */
    org.springframework.security.oauth2.common.OAuth2AccessToken getToken( );

    /**
     * Sets token.
     *
     * @param token the token
     */
    void setToken( org.springframework.security.oauth2.common.OAuth2AccessToken token );

    /**
     * Gets authentication id.
     *
     * @return the authentication id
     */
    String getAuthenticationId( );

    /**
     * Sets authentication id.
     *
     * @param authenticationId the authentication id
     */
    void setAuthenticationId( String authenticationId );

    /**
     * Gets userName.
     *
     * @return the userName
     */
    String getUserName( );

    /**
     * Sets userName.
     *
     * @param userName the userName
     */
    void setUserName( String userName );

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
     * Gets authentication.
     *
     * @return the authentication
     */
    OAuth2Authentication getAuthentication( );

    /**
     * Sets authentication.
     *
     * @param authentication the authentication
     */
    void setAuthentication( OAuth2Authentication authentication );

    /**
     * Gets refresh token.
     *
     * @return the refresh token
     */
    String getRefreshToken( );

    /**
     * Sets refresh token.
     *
     * @param refreshToken the refresh token
     */
    void setRefreshToken( String refreshToken );
}
