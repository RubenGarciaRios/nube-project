/*
 *  Developed by Rubén García Ríos
 *  Last modified 7/11/18 19:05
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.services.eureka.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import java.time.Duration;

@Configuration
@EnableMongoHttpSession
public class SessionConfiguration extends AbstractHttpSessionApplicationInitializer {
    @Bean
    public JdkMongoSessionConverter jdkMongoSessionConverter( ) {
        return new JdkMongoSessionConverter( Duration.ofMinutes( 5 ) );
    }
}
