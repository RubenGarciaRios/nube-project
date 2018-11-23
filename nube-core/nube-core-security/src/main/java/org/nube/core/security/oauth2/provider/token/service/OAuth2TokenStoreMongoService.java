/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.token.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2AccessToken;
import org.nube.core.security.oauth2.provider.token.domain.MongoOAuth2RefreshToken;
import org.nube.core.security.oauth2.provider.token.repository.OAuth2AccessTokenMongoRepository;
import org.nube.core.security.oauth2.provider.token.repository.OAuth2RefreshTokenMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The type OAuth 2 token store mongo service.
 * @see OAuth2TokenStoreService
 */
@Service( "oAuth2TokenStoreMongoService" )
public class OAuth2TokenStoreMongoService implements OAuth2TokenStoreService {
    private static final Logger _LOG = LogManager.getLogger( OAuth2TokenStoreMongoService.class );
    private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator( );

    @Autowired
    private OAuth2AccessTokenMongoRepository oAuth2AccessTokenRepository;

    @Autowired
    private OAuth2RefreshTokenMongoRepository oAuth2RefreshTokenMongoRepository;

    @Override
    public OAuth2Authentication readAuthentication( OAuth2AccessToken oAuth2AccessToken ) {
        return this.readAuthentication( oAuth2AccessToken.getValue( ) ); }

    @Override
    public OAuth2Authentication readAuthentication( String oAuth2AccessTokenValue ) {
        try {
            return oAuth2AccessTokenValue != null && !oAuth2AccessTokenValue.isEmpty( )
                    ? this.oAuth2AccessTokenRepository
                        .findByTokenId( oAuth2AccessTokenValue )
                        .map( MongoOAuth2AccessToken::getAuthentication ).orElse( null )
                    : null;
        } catch ( Exception e ) {
            _LOG.error( "Falied to read Authentication, with token key [" + oAuth2AccessTokenValue + "]" , e );
            this.removeAccessToken( oAuth2AccessTokenValue );
        }
        return null;
    }

    @Override
    public void storeAccessToken(
            OAuth2AccessToken oAuth2AccessToken,
            OAuth2Authentication oAuth2Authentication ) {
        this.oAuth2AccessTokenRepository.save(
            new MongoOAuth2AccessToken(
                oAuth2AccessToken,
                this.authenticationKeyGenerator.extractKey( oAuth2Authentication ),
                oAuth2Authentication ) );
    }

    @Override
    public OAuth2AccessToken readAccessToken( OAuth2AccessToken oAuth2AccessToken ) {
        return this.readAccessToken( oAuth2AccessToken.getValue( ) ); }

    @Override
    public OAuth2AccessToken readAccessToken( String oAuth2AccessTokenValue ) {
        try {
            return oAuth2AccessTokenValue != null && !oAuth2AccessTokenValue.isEmpty( )
                    ? this.oAuth2AccessTokenRepository
                        .findByTokenId( oAuth2AccessTokenValue )
                        .map( MongoOAuth2AccessToken::getToken ).orElse( null )
                    : null;
        } catch ( Exception e ) {
            _LOG.error( "Falied to read Access Token, with token key [" + oAuth2AccessTokenValue + "]" , e );
            this.removeAccessToken( oAuth2AccessTokenValue );
        }
        return null;
    }

    @Override
    public void removeAccessToken( OAuth2AccessToken oAuth2AccessToken ) {
        this.removeAccessToken( oAuth2AccessToken.getValue( ) ); }

    @Override
    public void removeAccessToken(String oAuth2AccessTokenValue) {
        this.oAuth2AccessTokenRepository.deletByTokenId( oAuth2AccessTokenValue ); }

    @Override
    public void storeRefreshToken(
            OAuth2RefreshToken oAuth2RefreshToken,
            OAuth2Authentication oAuth2Authentication ) {
        this.oAuth2RefreshTokenMongoRepository.save(
            new MongoOAuth2RefreshToken(
                oAuth2RefreshToken,
                oAuth2Authentication ) );
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(OAuth2RefreshToken oAuth2RefreshToken ) {
        return this.readRefreshToken( oAuth2RefreshToken.getValue( ) ); }

    @Override
    public OAuth2RefreshToken readRefreshToken( String oAuth2RefreshTokenValue ) {
        try {
            return oAuth2RefreshTokenValue != null && !oAuth2RefreshTokenValue.isEmpty( )
                    ? this.oAuth2RefreshTokenMongoRepository
                        .findByTokenId( oAuth2RefreshTokenValue )
                        .map( MongoOAuth2RefreshToken::getToken ).orElse( null )
                    : null;
        } catch ( Exception e ) {
            _LOG.error( "Falied to read Refresh Token, with token key [" + oAuth2RefreshTokenValue + "]" , e );
            this.removeRefreshToken( oAuth2RefreshTokenValue );
        }
        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(
            OAuth2RefreshToken oAuth2RefreshToken ) {
        return this.readAuthenticationForRefreshToken( oAuth2RefreshToken.getValue( ) ); }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(
            String oAuth2RefreshTokenValue ) {
        try {
            return oAuth2RefreshTokenValue != null && !oAuth2RefreshTokenValue.isEmpty( )
                    ? this.oAuth2RefreshTokenMongoRepository
                        .findByTokenId( oAuth2RefreshTokenValue )
                        .map( MongoOAuth2RefreshToken::getAuthentication ).orElse( null )
                    : null;
        } catch ( Exception e ) {
            _LOG.error( "Falied to read Authentication for Refresh Token, with token key [" + oAuth2RefreshTokenValue + "]" , e );
            this.removeRefreshToken( oAuth2RefreshTokenValue );
        }
        return null;
    }

    @Override
    public void removeRefreshToken( OAuth2RefreshToken oAuth2RefreshToken ) {
        this.removeRefreshToken( oAuth2RefreshToken.getValue( ) ); }

    @Override
    public void removeRefreshToken(String oAuth2RefreshTokenValue) {
        this.oAuth2RefreshTokenMongoRepository.removeByTokenId( oAuth2RefreshTokenValue ); }

    @Override
    public void removeAccessTokenUsingRefreshToken( OAuth2RefreshToken oAuth2RefreshToken ) {
        this.removeAccessTokenUsingRefreshToken( oAuth2RefreshToken.getValue( ) ); }

    @Override
    public void removeAccessTokenUsingRefreshToken( String oAuth2RefreshTokenValue ) {
        this.oAuth2AccessTokenRepository.deleteByRefreshToken( oAuth2RefreshTokenValue ); }

    @Override
    public OAuth2AccessToken getAccessToken( OAuth2Authentication oAuth2Authentication ) {
        return this.getAccessToken( authenticationKeyGenerator.extractKey( oAuth2Authentication ) ); }

    @Override
    public OAuth2AccessToken getAccessToken( String oAuth2AuthenticationValue ) {
        try {
            return oAuth2AuthenticationValue != null && !oAuth2AuthenticationValue.isEmpty( )
                    ? ( OAuth2AccessToken ) this.oAuth2AccessTokenRepository
                        .findByAuthenticationId( oAuth2AuthenticationValue )
                        .orElse( null )
                    : null;
        } catch ( Exception e ) {
            _LOG.error( "Falied to get Access Token, with Authentication key [" + oAuth2AuthenticationValue + "]" , e );
        }
        return null;
    }

    @Override
    public Collection< OAuth2AccessToken >  findTokensByClientIdAndUserName( String clientId, String username ) {
        return this.oAuth2AccessTokenRepository
                .findByClientIdAndUsername( clientId, username )
                .stream( ).map( MongoOAuth2AccessToken::getToken ).collect( Collectors.toList( ) );
    }

    @Override
    public Collection< OAuth2AccessToken > findTokensByClientId( String clientId ) {
        return this.oAuth2AccessTokenRepository
                .findByClientId( clientId )
                .stream( ).map( MongoOAuth2AccessToken::getToken ).collect( Collectors.toList( ) );
    }

    @Override
    public Collection< OAuth2AccessToken > findTokensByUserName( String username) {
        return this.oAuth2AccessTokenRepository
                .findByUsername( username )
                .stream( ).map( MongoOAuth2AccessToken::getToken ).collect( Collectors.toList( ) );
    }
}
