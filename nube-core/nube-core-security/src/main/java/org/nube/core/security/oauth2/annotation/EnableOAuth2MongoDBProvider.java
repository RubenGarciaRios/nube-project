/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.annotation;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * Enable OAuth2 with MongoDB for data storage.
 *
 * @author Rubén García Ríos.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
@Documented
@Inherited
@EnableMongoRepositories(
        basePackages = { "org.nube.core.security.oauth2.provider" },
        includeFilters = {
                @ComponentScan.Filter( MongoRepository.class ) },
        excludeFilters = {
                @ComponentScan.Filter( Repository.class ) } )
public @interface EnableOAuth2MongoDBProvider {
}
