/*
 *  Developed by Rubén García Ríos
 *  Last modified 15/11/18 18:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.approval.repository;

import org.nube.core.base.data.provider.nosql.mongodb.annotation.MongoDBRepository;
import org.nube.core.security.oauth2.provider.approval.domain.MongoOAuth2Approvals;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * OAuth 2 Approvals MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see MongoRepository
 * @see MongoOAuth2Approvals
 */
@MongoDBRepository
public interface OAuth2ApprovalsMongoRepository
        extends MongoRepository< MongoOAuth2Approvals, String > { }
