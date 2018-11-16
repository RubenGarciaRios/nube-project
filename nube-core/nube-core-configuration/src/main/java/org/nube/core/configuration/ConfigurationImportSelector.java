/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.configuration.annotation.NubeService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigurationImportSelector
        implements ImportSelector {
    private static final Logger _LOG = LogManager.getLogger( ConfigurationImportSelector.class );
    private static final String _BASE_PACKAGE = "org.nube.core.configuration";
    private static List< String > defaultConfigurationClasses = new ArrayList< String >( ) { {
        add(_BASE_PACKAGE + ".cloud.DefaultCloudConfiguration" );
    } };

    @Override
    public String[ ] selectImports( AnnotationMetadata importingClassMetadata ) {
        // Gets annotation values.
        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(
                        importingClassMetadata.getAnnotationAttributes(
                                NubeService.class.getName( ),
                                false ) );
        // Gets all included classes from annotated class.
        List< Class< ? > > includedClasses =
                Arrays.asList( annotationAttributes.getClassArray(
                        "include" ) );
        // All get class names of inclusion class list.
        defaultConfigurationClasses.addAll(
                includedClasses
                .stream( )
                .map( Class::getName )
                .collect( Collectors.toList( ) ) );
        includedClasses.clear( );
        _LOG.info( "Configuration classes for importation: {}",
                defaultConfigurationClasses );
        // Gets all excluded classes from annotated class.
        List< Class< ? > > excludedClasses =
                Arrays.asList( annotationAttributes.getClassArray(
                        "exclude" ) );
        // All get class names of exclusion class list.
        List< String > excludedClassNames =
                excludedClasses
                .stream( )
                .map( Class::getName )
                .collect( Collectors.toList( ) );
        excludedClasses.clear( );
        // Add all class names to exclusion class list.
        excludedClassNames.addAll( Arrays.asList( annotationAttributes.getStringArray(
                "excludeName" ) ) );
        _LOG.info( "Configuration class excluded: {}",
                excludedClassNames );
        // Prevent all excluded classes to be imported.
        defaultConfigurationClasses.removeAll( excludedClassNames );
        // Checks if class exists before include.
        for ( String className : defaultConfigurationClasses ) {
            try {
                if ( Class.forName( className ) != null )
                    continue;
            } catch ( ClassNotFoundException e ) {
                _LOG.warn( "Configuration class [{}] not exists: {}", className, e );
            }
            defaultConfigurationClasses.remove( className );
        }
        _LOG.info( "Final Configuration classess: {}",
                defaultConfigurationClasses );
        return defaultConfigurationClasses.toArray(
                new String[ defaultConfigurationClasses.size( ) ] );
    }
}
