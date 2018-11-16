/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client;

import org.nube.core.base.NubeSecurityObject;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * The interface OAuth 2 client details.
 *
 * @see NubeSecurityObject
 * @see org.springframework.security.oauth2.provider.ClientDetails
 */
public interface OAuth2ClientDetails extends NubeSecurityObject, ClientDetails {
}
