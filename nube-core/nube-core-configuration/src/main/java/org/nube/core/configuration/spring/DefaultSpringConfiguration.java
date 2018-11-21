package org.nube.core.configuration.spring;

import org.nube.core.configuration.spring.cloud.DefaultSpringCloudConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources( {
        @PropertySource( "classpath:default-application.properties" ) } )
@Import( DefaultSpringCloudConfiguration.class )
public class DefaultSpringConfiguration
        implements SpringConfiguration {
}
