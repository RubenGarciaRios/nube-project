/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2RefreshToken;

import java.util.Optional;

/**
 * OAuth2 Refresh Token MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @see MongoOAuth2RefreshToken
 */
@MongoRepository
public interface OAuth2RefreshTokenMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository {
    /**
     * Find by token id optional.
     *
     * @param tokenId the token id
     * @return the optional
     */
    Optional< MongoOAuth2RefreshToken > findByTokenId( String tokenId );

    /**
     * Remove by token id.
     *
     * @param tokenId the token id
     */
    void removeByTokenId( String tokenId );
}
