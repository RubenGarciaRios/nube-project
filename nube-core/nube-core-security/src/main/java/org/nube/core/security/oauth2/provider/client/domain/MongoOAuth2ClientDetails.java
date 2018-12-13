/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 12:30
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client.domain;

import org.nube.core.security.oauth2.provider.client.OAuth2ClientDetails;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * OAuth2 Client Details for management by MongoDB DataProviderType.
 * <p>
 * MongoDB referenced document name: {@code oauth2ClientDetails}
 * @author Rubén García Ríos
 */
@Document( collection = "oauth2ClientDetails" )
public class MongoOAuth2ClientDetails
        extends OAuth2ClientDetails {
    private static final long serialVersionUID = -1393977342514410417L;
    // CONSTANTS.
    /**
     * CLIENT_ID constant, indicates the name of field in MongoDB document.
     */
    public static final String CLIENT_ID = "clientId";
    /**
     * CLIENT_SECRET constant, indicates the name of field in MongoDB document.
     */
    public static final String CLIENT_SECRET = "clientSecret";
    /**
     * RESOURCE_IDS constant, indicates the name of field in MongoDB document.
     */
    public static final String RESOURCE_IDS = "resourceIds";
    /**
     * SCOPE constant, indicates the name of field in MongoDB document.
     */
    public static final String SCOPE = "scope";
    /**
     * AUTHORIZED_GRANT_TYPES constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHORIZED_GRANT_TYPES = "authorizedGrantTypes";
    /**
     * REGISTERED_REDIRECT_URIS constant, indicates the name of field in MongoDB document.
     */
    public static final String REGISTERED_REDIRECT_URIS = "registeredRedirectUris";
    /**
     * AUTHORITIES constant, indicates the name of field in MongoDB document.
     */
    public static final String AUTHORITIES = "authorities";
    /**
     * ACCESS_TOKEN_VALIDITY_SECONDS constant, indicates the name of field in MongoDB document.
     */
    public static final String ACCESS_TOKEN_VALIDITY_SECONDS = "accessTokenValiditySeconds";
    /**
     * REFRESH_TOKEN_VALIDITY_SECONDS constant, indicates the name of field in MongoDB document.
     */
    public static final String REFRESH_TOKEN_VALIDITY_SECONDS = "refreshTokenValiditySeconds";
    /**
     * ADDITIONAL_INFORMATION constant, indicates the name of field in MongoDB document.
     */
    public static final String ADDITIONAL_INFORMATION = "additionalInformation";
    // ATTRIBUTES.
    //////////////////
    @Indexed
    private String id;
    //////////////////
    //@formatter:off
    /**
     * Instantiates a new Mongo OAuth2 Client Details.
     */
    public MongoOAuth2ClientDetails( )
        { super( ); }

    /**
     * Instantiates a new OAuth 2 client details.
     *
     * @param clientId                    the client id
     * @param resourceIds                 the resource ids
     * @param secretRequired              the secret required
     * @param clientSecret                the client secret
     * @param scoped                      the scoped
     * @param scope                       the scope
     * @param authorizedGrantTypes        the authorized grant types
     * @param registeredRedirectUris      the registered redirect uris
     * @param authorities                 the authorities
     * @param accessTokenValiditySeconds  the access token validity seconds
     * @param refreshTokenValiditySeconds the refresh token validity seconds
     * @param autoApproveScopes           the auto approve scopes
     * @param additionalInformation       the additional information
     */
    public MongoOAuth2ClientDetails(
            String clientId,
            Set< String > resourceIds,
            boolean secretRequired,
            String clientSecret,
            boolean scoped,
            Set< String > scope,
            Set< String > authorizedGrantTypes,
            Set< String > registeredRedirectUris,
            Collection< GrantedAuthority > authorities,
            Integer accessTokenValiditySeconds,
            Integer refreshTokenValiditySeconds,
            Set< String > autoApproveScopes,
            Map< String, Object > additionalInformation ) {
        super( clientId,
               resourceIds,
               secretRequired,
               clientSecret,
               scoped,
               scope,
               authorizedGrantTypes,
               registeredRedirectUris,
               authorities,
               accessTokenValiditySeconds,
               refreshTokenValiditySeconds,
               autoApproveScopes,
               additionalInformation );
    }

    /**
     * Instantiates a new Mongo OAuth2 Client Details.
     *
     * @param clientId                    the client id
     * @param resourceIds                 the resource ids
     * @param clientSecret                the client secret
     * @param scope                       the scope
     * @param authorizedGrantTypes        the authorized grant types
     * @param registeredRedirectUris      the registered redirect uris
     * @param authorities                 the authorities
     * @param accessTokenValiditySeconds  the access token validity seconds
     * @param refreshTokenValiditySeconds the refresh token validity seconds
     * @param autoApproveScopes           the auto approve scopes
     * @param additionalInformation       the additional information
     */
    public MongoOAuth2ClientDetails(
            String clientId,
            Set< String > resourceIds,
            String clientSecret,
            Set< String > scope,
            Set< String > authorizedGrantTypes,
            Set< String > registeredRedirectUris,
            Collection< GrantedAuthority > authorities,
            Integer accessTokenValiditySeconds,
            Integer refreshTokenValiditySeconds,
            Set< String > autoApproveScopes,
            Map< String, Object > additionalInformation ) {
        super( clientId,
               resourceIds,
               clientSecret,
               scope,
               authorizedGrantTypes,
               registeredRedirectUris,
               authorities,
               accessTokenValiditySeconds,
               refreshTokenValiditySeconds,
               autoApproveScopes,
               additionalInformation );
    }

    /**
     * Instantiates a new Mongo OAuth2 Client Details.
     *
     * @param clientId                    the client id
     * @param resourceIds                 the resource ids
     * @param clientSecret                the client secret
     * @param scope                       the scope
     * @param authorizedGrantTypes        the authorized grant types
     * @param registeredRedirectUris      the registered redirect uris
     * @param authorities                 the authorities
     * @param accessTokenValiditySeconds  the access token validity seconds
     * @param refreshTokenValiditySeconds the refresh token validity seconds
     * @param autoApproveScopes           the auto approve scopes
     */
    public MongoOAuth2ClientDetails(
            String clientId,
            Set< String > resourceIds,
            String clientSecret,
            Set< String > scope,
            Set< String > authorizedGrantTypes,
            Set< String > registeredRedirectUris,
            Collection< GrantedAuthority > authorities,
            Integer accessTokenValiditySeconds,
            Integer refreshTokenValiditySeconds,
            Set< String > autoApproveScopes ) {
        super( clientId,
               resourceIds,
               clientSecret,
               scope,
               authorizedGrantTypes,
               registeredRedirectUris,
               authorities,
               accessTokenValiditySeconds,
               refreshTokenValiditySeconds,
               autoApproveScopes  );
    }

    /**
     * Instantiates a new Mongo OAuth2 Client Details.
     *
     * @param clientDetails the client details
     */
    public MongoOAuth2ClientDetails( ClientDetails clientDetails )
        { super( clientDetails ); }

    /**
     * Instantiates a new OAuth 2 client details.
     *
     * @param clientId     the client id
     * @param resourceIds  the resource ids
     * @param scopes       the scopes
     * @param grantTypes   the grant types
     * @param authorities  the authorities
     * @param redirectUris the redirect uris
     */
    public MongoOAuth2ClientDetails(
            String clientId,
            String resourceIds,
            String scopes,
            String grantTypes,
            String authorities,
            String redirectUris ) {
        super( clientId,
               resourceIds,
               scopes,
               grantTypes,
               authorities,
               redirectUris );
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId( )
        { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId( final String id )
        { this.id = id; }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MongoOAuth2ClientDetails ) ) return false;
        if ( !super.equals( o ) ) return false;
        final MongoOAuth2ClientDetails that = ( MongoOAuth2ClientDetails ) o;
        return Objects.equals( id, that.id );
    }

    @Override
    public int hashCode( )
        { return Objects.hash( super.hashCode( ), getId( ) ); }
    //@formatter:on
}
