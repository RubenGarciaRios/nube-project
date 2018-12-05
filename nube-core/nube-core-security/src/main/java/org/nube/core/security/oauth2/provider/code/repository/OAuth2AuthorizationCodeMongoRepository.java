/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 0:56
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.code.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.code.domain.MongoOAuth2AuthorizationCode;

/**
 * OAuth2 Authorization Code MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @see MongoOAuth2AuthorizationCode
 */
@MongoRepository
public interface OAuth2AuthorizationCodeMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository < MongoOAuth2AuthorizationCode, String > {
}
