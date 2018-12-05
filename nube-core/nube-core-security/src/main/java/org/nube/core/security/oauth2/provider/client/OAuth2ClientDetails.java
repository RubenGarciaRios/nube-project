/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:08
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client;

import org.nube.core.base.security.NubeSecurityObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * OAuth2 Client Details.
 *
 * @author Rubén García Ríos
 */
public abstract class OAuth2ClientDetails
        extends NubeSecurityObject
        implements ClientDetails {
    private static final long serialVersionUID = -3226366807864411528L;
    // ATTRIBUTES.
    private String clientId;
    private Set< String > resourceIds;
    private boolean secretRequired;
    private String clientSecret;
    private boolean scoped;
    private Set< String > scope;
    private Set< String > authorizedGrantTypes;
    private Set< String > registeredRedirectUris;
    private Collection< GrantedAuthority > authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Set< String > autoApproveScopes;
    private Map< String, Object > additionalInformation;

    /**
     * Instantiates a new OAuth2 Client Details.
     */
    public OAuth2ClientDetails( ) {
        this.scope = Collections.emptySet( );
        this.resourceIds = Collections.emptySet( );
        this.authorizedGrantTypes = Collections.emptySet( );
        this.authorities = Collections.emptyList( );
        this.additionalInformation = new LinkedHashMap( );
    }

    /**
     * Instantiates a new OAuth2 Client Details.
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
    public OAuth2ClientDetails(
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
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secretRequired = secretRequired;
        this.clientSecret = clientSecret;
        this.scoped = scoped;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.autoApproveScopes = autoApproveScopes;
        this.additionalInformation = additionalInformation;
    }

    /**
     * Instantiates a new OAuth2 Client Details.
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
    public OAuth2ClientDetails(
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
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secretRequired = clientSecret != null && !clientSecret.isEmpty( );
        this.clientSecret = clientSecret;
        this.scoped = scope != null && !scope.isEmpty( );
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.autoApproveScopes = autoApproveScopes;
        this.additionalInformation = additionalInformation;
    }

    /**
     * Instantiates a new OAuth2 Client Details.
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
    public OAuth2ClientDetails(
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
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secretRequired = clientSecret != null && !clientSecret.isEmpty( );
        this.clientSecret = clientSecret;
        this.scoped = scope != null && !scope.isEmpty( );
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.autoApproveScopes = autoApproveScopes;
        this.additionalInformation = new LinkedHashMap( );
    }

    /**
     * Instantiates a new OAuth2 Client Details.
     *
     * @param clientDetails the client details
     */
    public OAuth2ClientDetails( ClientDetails clientDetails ) {
        this( );
        this.setAccessTokenValiditySeconds( clientDetails.getAccessTokenValiditySeconds( ) );
        this.setRefreshTokenValiditySeconds( clientDetails.getRefreshTokenValiditySeconds( ) );
        this.setAuthorities( clientDetails.getAuthorities( ) );
        this.setAuthorizedGrantTypes( clientDetails.getAuthorizedGrantTypes( ) );
        this.setClientId( clientDetails.getClientId( ) );
        this.setClientSecret( clientDetails.getClientSecret( ) );
        this.setRegisteredRedirectUris( clientDetails.getRegisteredRedirectUri( ) );
        this.setScope( clientDetails.getScope( ) );
        this.setResourceIds( clientDetails.getResourceIds( ) );
    }

    /**
     * Instantiates a new OAuth2 Client Details.
     *
     * @param clientId     the client id
     * @param resourceIds  the resource ids
     * @param scopes       the scopes
     * @param grantTypes   the grant types
     * @param authorities  the authorities
     * @param redirectUris the redirect uris
     */
    public OAuth2ClientDetails(
            String clientId,
            String resourceIds,
            String scopes,
            String grantTypes,
            String authorities,
            String redirectUris ) {
        this( );
        this.clientId = clientId;
        Set scopeList;
        if ( StringUtils.hasText( resourceIds ) ) {
            scopeList = StringUtils.commaDelimitedListToSet( resourceIds );
            if ( !scopeList.isEmpty( ) ) {
                this.resourceIds = scopeList;
            }
        }

        if ( StringUtils.hasText( scopes ) ) {
            scopeList = StringUtils.commaDelimitedListToSet( scopes );
            if ( !scopeList.isEmpty( ) ) {
                this.scope = scopeList;
            }
        }

        if ( StringUtils.hasText( grantTypes ) ) {
            this.authorizedGrantTypes = StringUtils.commaDelimitedListToSet( grantTypes );
        } else {
            this.authorizedGrantTypes = new HashSet( Arrays.asList( "authorization_code", "refresh_token" ) );
        }

        if ( StringUtils.hasText( authorities ) ) {
            this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList( authorities );
        }

        if ( StringUtils.hasText( redirectUris ) ) {
            this.registeredRedirectUris = StringUtils.commaDelimitedListToSet( redirectUris );
        }
    }
    //@formatter:off
    @Override
    public String getClientId( )
        { return clientId; }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId( String clientId )
        { this.clientId = clientId; }

    @Override
    public Set< String > getResourceIds( )
        { return resourceIds; }

    /**
     * Sets resource ids.
     *
     * @param resourceIds the resource ids
     */
    public void setResourceIds( Set< String > resourceIds )
        { this.resourceIds = resourceIds; }

    @Override
    public boolean isSecretRequired( )
        { return secretRequired; }

    /**
     * Sets secret required.
     *
     * @param secretRequired the secret required
     */
    public void setSecretRequired( boolean secretRequired )
        { this.secretRequired = secretRequired; }

    @Override
    public String getClientSecret( )
        { return clientSecret; }

    /**
     * Sets client secret.
     *
     * @param clientSecret the client secret
     */
    public void setClientSecret( String clientSecret )
        { this.clientSecret = clientSecret; }

    @Override
    public boolean isScoped( )
        { return scoped; }

    /**
     * Sets scoped.
     *
     * @param scoped the scoped
     */
    public void setScoped( boolean scoped )
        { this.scoped = scoped; }

    @Override
    public Set< String > getScope( )
        { return scope; }

    /**
     * Sets scope.
     *
     * @param scope the scope
     */
    public void setScope( Set< String > scope )
        { this.scope = scope; }

    @Override
    public Set< String > getAuthorizedGrantTypes( )
        { return authorizedGrantTypes; }

    /**
     * Sets authorized grant types.
     *
     * @param authorizedGrantTypes the authorized grant types
     */
    public void setAuthorizedGrantTypes( Set< String > authorizedGrantTypes )
        { this.authorizedGrantTypes = authorizedGrantTypes; }

    @Override
    public Set< String > getRegisteredRedirectUri( )
        { return registeredRedirectUris; }

    /**
     * Sets registered redirect uris.
     *
     * @param registeredRedirectUris the registered redirect uris
     */
    public void setRegisteredRedirectUris( Set< String > registeredRedirectUris )
        { this.registeredRedirectUris = registeredRedirectUris; }

    @Override
    public Collection< GrantedAuthority > getAuthorities( )
        { return authorities; }

    /**
     * Sets authorities.
     *
     * @param authorities the authorities
     */
    public void setAuthorities( Collection< GrantedAuthority > authorities )
        { this.authorities = authorities; }

    @Override
    public Integer getAccessTokenValiditySeconds( )
        { return accessTokenValiditySeconds; }

    /**
     * Sets access token validity seconds.
     *
     * @param accessTokenValiditySeconds the access token validity seconds
     */
    public void setAccessTokenValiditySeconds( Integer accessTokenValiditySeconds )
        { this.accessTokenValiditySeconds = accessTokenValiditySeconds; }

    @Override
    public Integer getRefreshTokenValiditySeconds( )
        { return refreshTokenValiditySeconds; }

    /**
     * Sets refresh token validity seconds.
     *
     * @param refreshTokenValiditySeconds the refresh token validity seconds
     */
    public void setRefreshTokenValiditySeconds( Integer refreshTokenValiditySeconds )
        { this.refreshTokenValiditySeconds = refreshTokenValiditySeconds; }

    /**
     * Gets auto approve scopes.
     *
     * @return the auto approve scopes
     */
    public Set< String > getAutoApproveScopes( )
        { return autoApproveScopes; }

    /**
     * Sets auto approve scopes.
     *
     * @param autoApproveScopes the auto approve scopes
     */
    public void setAutoApproveScopes( Set< String > autoApproveScopes )
        { this.autoApproveScopes = autoApproveScopes; }

    /**
     * Add auto approve scopes.
     *
     * @param autoApproveScope the auto approve scope
     */
    public void addAutoApproveScopes( String autoApproveScope )
        { this.autoApproveScopes.add( autoApproveScope ); }

    @Override
    public boolean isAutoApprove( String scope ) {
        if ( this.autoApproveScopes == null ) {
            return false;
        } else {
            Iterator iterator = this.autoApproveScopes.iterator( );
            String auto;
            do {
                if ( !iterator.hasNext( ) )
                    return false;
                auto = ( String ) iterator.next( );
            } while ( !auto.equals( "true" ) && !scope.matches( auto ) );
            return true;
        }
    }

    @Override
    public Map< String, Object > getAdditionalInformation( )
        { return additionalInformation; }

    /**
     * Sets additional information.
     *
     * @param additionalInformation the additional information
     */
    public void setAdditionalInformation( Map< String, Object > additionalInformation )
        { this.additionalInformation = additionalInformation; }

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
        if ( !( o instanceof OAuth2ClientDetails ) ) return false;
        final OAuth2ClientDetails that = ( OAuth2ClientDetails ) o;
        return isSecretRequired( ) == that.isSecretRequired( ) &&
               isScoped( ) == that.isScoped( ) &&
               Objects.equals( getClientId( ), that.getClientId( ) ) &&
               Objects.equals( getResourceIds( ), that.getResourceIds( ) ) &&
               Objects.equals( getClientSecret( ), that.getClientSecret( ) ) &&
               Objects.equals( getScope( ), that.getScope( ) ) &&
               Objects.equals( getAuthorizedGrantTypes( ), that.getAuthorizedGrantTypes( ) ) &&
               Objects.equals( registeredRedirectUris, that.registeredRedirectUris ) &&
               Objects.equals( getAuthorities( ), that.getAuthorities( ) ) &&
               Objects.equals( getAccessTokenValiditySeconds( ), that.getAccessTokenValiditySeconds( ) ) &&
               Objects.equals( getRefreshTokenValiditySeconds( ), that.getRefreshTokenValiditySeconds( ) ) &&
               Objects.equals( getAutoApproveScopes( ), that.getAutoApproveScopes( ) ) &&
               Objects.equals( getAdditionalInformation( ), that.getAdditionalInformation( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash(
               getClientId( ), getResourceIds( ), isSecretRequired( ), getClientSecret( ), isScoped( ),
               getScope( ), getAuthorizedGrantTypes( ), registeredRedirectUris, getAuthorities( ),
               getAccessTokenValiditySeconds( ), getRefreshTokenValiditySeconds( ), getAutoApproveScopes( ), getAdditionalInformation( ) );
    }
    //@formatter:on
}
