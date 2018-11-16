/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.code;

import org.nube.core.base.NubeSecurityObject;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * The interface OAuth 2 authorization code.
 *
 * @see NubeSecurityObject
 */
public interface OAuth2AuthorizationCode extends NubeSecurityObject {
    /**
     * Gets code.
     *
     * @return the code
     */
    String getCode( );

    /**
     * Sets code.
     *
     * @param code the code
     */
    void setCode( String code );

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
}
