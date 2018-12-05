/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 0:15
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.client.domain.MongoOAuth2ClientDetails;

/**
 * OAuth2 Client Details MongoDB repository.
 *
 * @author Rubén García Ríos
 */
@MongoRepository
public interface OAuth2ClientDetailsMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository< MongoOAuth2ClientDetails, String > {
}
