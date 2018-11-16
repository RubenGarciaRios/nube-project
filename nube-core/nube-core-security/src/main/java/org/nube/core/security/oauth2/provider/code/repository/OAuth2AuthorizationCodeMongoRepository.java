/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.code.repository;

import org.nube.core.base.data.provider.nosql.mongodb.annotation.MongoDBRepository;
import org.nube.core.security.oauth2.provider.code.domain.MongoOAuth2AuthorizationCode;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * OAuth2 Authorization Code MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see MongoRepository
 * @see MongoOAuth2AuthorizationCode
 */
@MongoDBRepository
public interface OAuth2AuthorizationCodeMongoRepository
        extends MongoRepository< MongoOAuth2AuthorizationCode, String > { }
