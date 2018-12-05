/*
 *  Developed by Rubén García Ríos
 *  Last modified 28/11/18 14:44
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data.provider.mongodb.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.config.DefaultRepositoryBaseClass;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * Annotation to activate MongoDB repositories.
 * Include all components that has {@code @MongoRepository {@link MongoRepository}} annotation
 * and exclude all components that has {@code @Repository {@link Repository}} annotation to prevent
 * JPA Repositories inclusions.
 *
 * @author Rubén García Ríos
 */
@Target( ElementType.TYPE)
@Retention( RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableMongoRepositories
public @interface ScanMongoRepositories {
    //@formmater:off

    /**
     * Alias for the {@link EnableMongoRepositories#value()} attribute.
     *
     * @return the string [ ]
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    String[ ] value( ) default { };

    /**
     * Alias for the {@link EnableMongoRepositories#basePackages()} attribute.
     *
     * @return the string [ ]
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    String[ ] basePackages( ) default { };

    /**
     * Alias for the {@link EnableMongoRepositories#basePackageClasses()} attribute.
     *
     * @return the class [ ]
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    Class<?>[ ] basePackageClasses( ) default { };

    /**
     * Alias for the {@link EnableMongoRepositories#includeFilters()} attribute.
     * Include all components that has {@code @MongoRepository {@link MongoRepository}} annotation.
     *
     * @return the component scan . filter [ ]
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    ComponentScan.Filter[ ] includeFilters( ) default { @ComponentScan.Filter( MongoRepository.class ) };

    /**
     * Alias for the {@link EnableMongoRepositories#excludeFilters()} attribute.
     * Exclude all components that has {@code @Repository {@link Repository}} annotation.
     *
     * @return the component scan . filter [ ]
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    ComponentScan.Filter[ ] excludeFilters( ) default { @ComponentScan.Filter( Repository.class ) };

    /**
     * Alias for the {@link EnableMongoRepositories#repositoryImplementationPostfix()} attribute.
     *
     * @return the string
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    String repositoryImplementationPostfix( ) default "Impl";

    /**
     * Alias for the {@link EnableMongoRepositories#namedQueriesLocation()} attribute.
     *
     * @return the string
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    String namedQueriesLocation( ) default "";

    /**
     * Alias for the {@link EnableMongoRepositories#queryLookupStrategy()} attribute.
     *
     * @return the query lookup strategy . key
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    QueryLookupStrategy.Key queryLookupStrategy( ) default QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND;

    /**
     * Alias for the {@link EnableMongoRepositories#repositoryFactoryBeanClass()} attribute.
     *
     * @return the class
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    Class< ? > repositoryFactoryBeanClass( ) default MongoRepositoryFactoryBean.class;

    /**
     * Alias for the {@link EnableMongoRepositories#repositoryBaseClass()} attribute.
     *
     * @return the class
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    Class< ? > repositoryBaseClass( ) default DefaultRepositoryBaseClass.class;

    /**
     * Alias for the {@link EnableMongoRepositories#mongoTemplateRef()} attribute.
     *
     * @return the string
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    String mongoTemplateRef( ) default "mongoTemplate";

    /**
     * Alias for the {@link EnableMongoRepositories#createIndexesForQueryMethods()} attribute.
     *
     * @return the boolean
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    boolean createIndexesForQueryMethods( ) default false;

    /**
     * Alias for the {@link EnableMongoRepositories#considerNestedRepositories()} attribute.
     *
     * @return the boolean
     */
    @AliasFor( annotation = EnableMongoRepositories.class )
    boolean considerNestedRepositories( ) default false;
    //@formmater:on
}
