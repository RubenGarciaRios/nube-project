/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 12:04
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.security.oauth2.annotation;

import org.nube.core.base.data.provider.nosql.mongodb.annotation.MongoDBRepository;
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
                @ComponentScan.Filter( MongoDBRepository.class ) },
        excludeFilters = {
                @ComponentScan.Filter( Repository.class ) } )
public @interface EnableOAuth2MongoDBProvider {
}
