/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.approval.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.approval.domain.MongoOAuth2Approvals;


/**
 * OAuth 2 Approvals MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @see MongoOAuth2Approvals
 */
@MongoRepository
public interface OAuth2ApprovalsMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository { }
