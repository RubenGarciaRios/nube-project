/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 20:25
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider.cloud.discovery.instance.region.repository;

import org.nube.core.base.config.data.provider.mongodb.annotation.MongoRepository;
import org.nube.core.base.data.provider.cloud.discovery.instance.region.domain.MongoDiscoveryInstanceRegion;

import java.util.Collection;

/**
 * MongoDB Discovery Instance Region Repository.
 * All default querys for gets {@link MongoDiscoveryInstanceRegion} data from MongoDB.
 *
 * @author Rubén García Ríos
 */
@MongoRepository
public interface MongoDiscoveryInstanceRegionRepository
        extends org.springframework.data.mongodb.repository.MongoRepository {

    /**
     * Find all by Enabled MongoDB Discovery Instance Region.
     *
     * @param enabled the enabled
     * @return the mongo discovery instance region {@link MongoDiscoveryInstanceRegion}.
     */
    MongoDiscoveryInstanceRegion findAllByEnabled( boolean enabled );

    /**
     * Find all Regions that has any Zone enabled.
     *
     * @param enabled the enabled
     * @return the collection of {@link MongoDiscoveryInstanceRegion}.
     */
    Collection< MongoDiscoveryInstanceRegion > findByZones_Enabled( boolean enabled );
}
