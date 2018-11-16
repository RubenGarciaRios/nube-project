/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.repository;

import org.nube.core.base.data.provider.nosql.mongodb.annotation.MongoDBRepository;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * OAuth2 Refresh Token MongoDB repository.
 *
 * @author Rubén García Ríos
 * @see MongoRepository
 * @see MongoOAuth2RefreshToken
 */
@MongoDBRepository
public interface OAuth2RefreshTokenMongoRepository
        extends MongoRepository< MongoOAuth2RefreshToken, String > {
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
