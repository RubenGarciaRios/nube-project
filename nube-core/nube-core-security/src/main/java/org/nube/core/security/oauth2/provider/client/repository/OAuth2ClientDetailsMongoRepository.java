/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.client.domain.MongoOAuth2ClientDetails;

/**
 * OAuth2 Client Details MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @see MongoOAuth2ClientDetails
 */
@MongoRepository
public interface OAuth2ClientDetailsMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository { }
