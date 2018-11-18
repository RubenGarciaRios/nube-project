/*
 *  Developed by Rubén García Ríos
 *  Last modified 16/11/18 15:02
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.config.annotation.Include;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractIncludeSelector
        implements IncludeSelector {
    private static final long serialVersionUID = -5453050090836567052L;
    private static final String[ ] NO_IMPORTS = { };
    protected final Logger _LOG = LogManager.getLogger( this.getClass( ) );
    protected List< String > defaultClasses = new ArrayList< >( );

    @Override
    public void initializer( ) { }

    @Override
    public String[ ] selectImports( AnnotationMetadata importingClassMetadata ) {
        // Gets annotation values.
        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(
                        importingClassMetadata.getAnnotationAttributes(
                                Include.class.getName( ),
                                false ) );
        if ( annotationAttributes != null ) {
            // Gets all included classes from annotated class.
            List< Class< ? > > includedClasses = Arrays
                    .asList( annotationAttributes.getClassArray( "include" ) );
            // All get class names of inclusion class list.
            defaultClasses.addAll(
                    includedClasses
                            .stream( )
                            .map( Class::getName )
                            .collect( Collectors.toList( ) ) );
            includedClasses.clear( );
            // Gets all included class names from annotated class.
            defaultClasses.addAll(
                    Arrays.asList( annotationAttributes.getStringArray( "includeName" ) ) );
            // Gets all excluded classes from annotated class.
            List< Class< ? > > excludedClasses = Arrays
                    .asList( annotationAttributes.getClassArray( "exclude" ) );
            // All get class names of exclusion class list.
            List< String > excludedClassNames =
                    excludedClasses
                            .stream( )
                            .map( Class::getName )
                            .collect( Collectors.toList( ) );
            excludedClasses.clear( );
            // Add all class names to exclusion class list.
            excludedClassNames.addAll(
                    Arrays.asList( annotationAttributes.getStringArray( "excludeName" ) ) );
            _LOG.info( "Excluded class list: {}",
                       excludedClassNames );
            // Prevent all excluded classes to be imported.
            defaultClasses.removeAll( excludedClassNames );
        }
        _LOG.info( "Included class list: {}",
                   defaultClasses );
        // Checks if class exists before include.
        for ( String className : defaultClasses ) {
            try {
                if ( Class.forName( className ) != null )
                    continue;
            } catch ( ClassNotFoundException e ) {
                _LOG.warn( "class [{}] not found: {}", className, e );
            }
            defaultClasses.remove( className );
        }
        _LOG.info( "Final class list for hook: {}",
                   defaultClasses );
        return defaultClasses.size( ) > 0
                ? defaultClasses.toArray( new String[ defaultClasses.size( ) ] )
                : NO_IMPORTS;
    }

    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof AbstractIncludeSelector ) ) return false;
        AbstractIncludeSelector that = ( AbstractIncludeSelector ) o;
        return Objects.equals( _LOG, that._LOG ) &&
                Objects.equals( defaultClasses, that.defaultClasses );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash( _LOG, defaultClasses );
    }
}
