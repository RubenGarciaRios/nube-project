/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:44
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2AccessToken;

import java.util.Collection;
import java.util.Optional;


/**
 * OAuth2 Access Token MongoDB repository.
 *
 * @author Rubén García Ríos
 */
@MongoRepository
public interface OAuth2AccessTokenMongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository< MongoOAuth2AccessToken, String > {
    /**
     * Find by token id optional.
     *
     * @param tokenId the token id
     * @return the optional
     */
    Optional< MongoOAuth2AccessToken > findByTokenId( String tokenId );

    /**
     * Find by refresh token optional.
     *
     * @param refreshToken the refresh token
     * @return the optional
     */
    Optional< MongoOAuth2AccessToken > findByRefreshToken( String refreshToken );

    /**
     * Find by authentication id optional.
     *
     * @param authenticationId the authentication id
     * @return the optional
     */
    Optional< MongoOAuth2AccessToken > findByAuthenticationId( String authenticationId );

    /**
     * Find by client id collection.
     *
     * @param clientId the client id
     * @return the collection
     */
    Collection< MongoOAuth2AccessToken > findByClientId( String clientId );

    /**
     * Find by username collection.
     *
     * @param username the username
     * @return the collection
     */
    Collection< MongoOAuth2AccessToken > findByUsername( String username );

    /**
     * Find by client id and username collection.
     *
     * @param clientId the client id
     * @param username the username
     * @return the collection
     */
    Collection< MongoOAuth2AccessToken > findByClientIdAndUsername( String clientId, String username );

    /**
     * Delet by token id.
     *
     * @param tokenId the token id
     */
    void deletByTokenId( String tokenId );

    /**
     * Delete by refresh token.
     *
     * @param refreshToken the refresh token
     */
    void deleteByRefreshToken( String refreshToken );
}
