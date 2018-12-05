/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:44
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2ClientToken;


/**
 * OAuth2 Client Token MongoDB repository.
 *
 * @author Rubén García Ríos
 */
@MongoRepository
public interface OAuth2ClientTokenMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository< MongoOAuth2ClientToken, String > {
}
