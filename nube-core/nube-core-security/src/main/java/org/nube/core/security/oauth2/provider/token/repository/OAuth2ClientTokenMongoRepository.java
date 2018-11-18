/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.repository;

import org.nube.core.base.data.provider.mongodb.annotation.MongoDBRepository;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2ClientToken;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * OAuth2 Client Token MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see MongoRepository
 * @see MongoOAuth2ClientToken
 */
@MongoDBRepository
public interface OAuth2ClientTokenMongoRepository
        extends MongoRepository< MongoOAuth2ClientToken, String > { }
