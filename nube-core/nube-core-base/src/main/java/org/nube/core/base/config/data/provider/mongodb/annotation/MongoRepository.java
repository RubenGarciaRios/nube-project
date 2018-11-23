/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config.data.provider.mongodb.annotation;

import java.lang.annotation.*;

/**
 * Indicates that an annotated class is a "MongoDB Repository"
 * Used for scan MongoDB repositories in packages.
 *
 * @author Rubén García Ríos.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
@Documented
@Inherited
public @interface MongoRepository {
}
