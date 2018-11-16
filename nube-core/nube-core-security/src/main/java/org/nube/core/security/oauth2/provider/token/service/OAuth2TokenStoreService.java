/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collection;

/**
 * OAuth2 Token Store service.
 *
 * @author Rubén García Ríos
 * @see TokenStore
 */
public interface OAuth2TokenStoreService extends TokenStore {
    /**
     * Read access token o auth 2 access token.
     *
     * @param oAuth2AccessToken the o auth 2 access token
     * @return the o auth 2 access token
     */
    OAuth2AccessToken readAccessToken( OAuth2AccessToken oAuth2AccessToken );

    /**
     * Remove access token.
     *
     * @param oAuth2AccessTokenValue the o auth 2 access token value
     */
    void removeAccessToken( String oAuth2AccessTokenValue );

    /**
     * Read refresh token o auth 2 refresh token.
     *
     * @param oAuth2RefreshToken the o auth 2 refresh token
     * @return the o auth 2 refresh token
     */
    OAuth2RefreshToken readRefreshToken( OAuth2RefreshToken oAuth2RefreshToken );

    /**
     * Read authentication for refresh token o auth 2 authentication.
     *
     * @param oAuth2RefreshTokenValue the o auth 2 refresh token value
     * @return the o auth 2 authentication
     */
    OAuth2Authentication readAuthenticationForRefreshToken(
            String oAuth2RefreshTokenValue );

    /**
     * Remove refresh token.
     *
     * @param oAuth2RefreshTokenValue the o auth 2 refresh token value
     */
    void removeRefreshToken( String oAuth2RefreshTokenValue );

    /**
     * Remove access token using refresh token.
     *
     * @param oAuth2RefreshTokenValue the o auth 2 refresh token value
     */
    void removeAccessTokenUsingRefreshToken( String oAuth2RefreshTokenValue );

    /**
     * Gets access token.
     *
     * @param oAuth2AuthenticationValue the o auth 2 authentication value
     * @return the access token
     */
    OAuth2AccessToken getAccessToken( String oAuth2AuthenticationValue );

    /**
     * Find tokens by user name collection.
     *
     * @param username the username
     * @return the collection
     */
    Collection< OAuth2AccessToken > findTokensByUserName( String username );
}
