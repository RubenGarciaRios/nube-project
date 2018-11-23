/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.provider.client.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.security.oauth2.provider.client.repository.OAuth2ClientDetailsMongoRepository;
import org.nube.core.security.oauth2.provider.token.service.OAuth2TokenStoreMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service( "oAuth2ClientDetailsMongoService" )
public class OAuth2ClientDetailsMongoService
        implements OAuth2ClientDetailsService {
    private static final Logger _LOG = LogManager.getLogger( OAuth2TokenStoreMongoService.class );

    @Value( "${logging.show-info:false}" )
    private boolean showInfo;

    @Autowired
    OAuth2ClientDetailsMongoRepository clientDetailsMongoRepository;

    public OAuth2ClientDetailsMongoService( ) {
        super( ); }

    @Override
    public UserDetails loadUserByUsername( String username )
            throws UsernameNotFoundException {
        if ( showInfo ) _LOG.info( "Authenticating user [" + username + "]" );
        return null;
    }
}
