/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token;

import org.nube.core.base.NubeSecurityObject;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * The interface O auth 2 refresh token.
 *
 * @see NubeSecurityObject
 */
public interface OAuth2RefreshToken extends NubeSecurityObject {
    /**
     * Gets token id.
     *
     * @return the token id
     */
    String getTokenId( );

    /**
     * Gets token.
     *
     * @return the token
     */
    org.springframework.security.oauth2.common.OAuth2RefreshToken getToken( );

    /**
     * Gets authentication.
     *
     * @return the authentication
     */
    OAuth2Authentication getAuthentication( );
}
