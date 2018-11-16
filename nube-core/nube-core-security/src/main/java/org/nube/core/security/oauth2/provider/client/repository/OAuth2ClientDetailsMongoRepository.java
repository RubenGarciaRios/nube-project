/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client.repository;

import org.nube.core.base.data.provider.nosql.mongodb.annotation.MongoDBRepository;
import org.nube.core.security.oauth2.provider.client.domain.MongoOAuth2ClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * OAuth2 Client Details MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see MongoRepository
 * @see MongoOAuth2ClientDetails
 */
@MongoDBRepository
public interface OAuth2ClientDetailsMongoRepository
        extends MongoRepository< MongoOAuth2ClientDetails, String > { }
