/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 1:49
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nube.core.base.NubeObject;
import org.nube.core.base.data.provider.mongodb.MongoProvider;
import org.springframework.util.ClassUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

final public class StringParser
        implements Serializable {
    private final static Logger _LOG = LogManager.getLogger( StringParser.class );
    private static final long serialVersionUID = -9179159293588505607L;
    // ATTRIBUTES.
    private final Object OBJECT;
    private final boolean SHOW_ATTRIBUTES_TYPE;
    private final boolean SHOW_PACKAGES;
    private final char ASSIGNATION_CHARACTER;
    private final char DELIMITATION_CHARACTER;
    private final char[ ] AGGROUPATION_CHARACTERS;
    private final boolean LINE_BREAKS;
    private final char[ ] INDENT_CHARACTERS;

    private StringParser(
            final Object object,
            final boolean showAttributesType,
            final boolean showPackages,
            final char assignation,
            final char delimitation,
            final char[ ] aggroupation,
            final boolean lineBreaks,
            final char[ ] indentCharacters ) {
        OBJECT = object;
        SHOW_ATTRIBUTES_TYPE = showAttributesType;
        SHOW_PACKAGES = showPackages;
        ASSIGNATION_CHARACTER = assignation;
        DELIMITATION_CHARACTER = delimitation;
        AGGROUPATION_CHARACTERS = aggroupation;
        LINE_BREAKS = lineBreaks;
        INDENT_CHARACTERS = indentCharacters;
    }

    public StringParser( final Options options ) {
        this( options.object,
              options.showAttributesType,
              options.showPackages,
              options.statements.assignation,
              options.statements.delimitation,
              options.statements.aggroupation,
              options.beautifier.lineBreaks,
              options.beautifier.indentType != IndentType.NONE
                  ? new String( new char[ options.beautifier.indentSize ] )
                      .replace( "\0", String.valueOf( options.beautifier.indentType.value ) )
                      .toCharArray( )
                  : null );
    }

    final public static Options of( final Object object )
        { return new Options( object ); }

    final public String parse( )
        { return parse( OBJECT, 1 ); }

    public enum IndentType {
        NONE( '\0' ),
        TAB( '\t' ),
        SPACE( ' ' );
        private char value;

        IndentType( char value )
        { this.value = value; }

        public char value( )
        { return value; }
    }

    final public static class Options {
        private final boolean DEFAULT_SHOW_ATTRIBUTES_TYPE = true;
        private final boolean DEFAULT_SHOW_PACKAGES = false;
        // ATTRIBUTES.
        private final Object object;
        private boolean showAttributesType;
        private boolean showPackages;
        private Beautifier beautifier;
        private Statements statements;

        private Options( final Object object ) {
            this.object = object;
            showAttributesType = DEFAULT_SHOW_ATTRIBUTES_TYPE;
            showPackages = DEFAULT_SHOW_PACKAGES;
            beautifier = new Beautifier( );
            statements = new Statements( );
        }

        public static Options forObject( final Object object )
            { return new Options( object ); }

        private Options setShowAttributesType( final boolean showAttributesType ) {
            this.showAttributesType = showAttributesType;
            return this;
        }

        public Options showAttributesType(  )
            { return setShowAttributesType( true ); }

        public Options hideAttributesType(  ) {
            hidePackages( );
            return setShowAttributesType( false );
        }

        private Options setShowPackages( final boolean showPackages ) {
            if ( showPackages )
                showAttributesType( );
            this.showPackages = showPackages;
            return this;
        }

        public Options showPackages(  )
            { return setShowPackages( true ); }

        public Options hidePackages(  )
            { return setShowPackages( false ); }

        final public Beautifier beautify( )
            { return beautifier; }

        final public Statements useStatements( )
            { return statements; }

        final public StringParser apply( )
            { return new StringParser( this ); }

        final public class Beautifier
                implements Serializable {
            private static final long serialVersionUID = 1024536979730414580L;
            // CONSTANTS.
            private final boolean DEFAULT_LINE_BREAKS = false;
            private final IndentType DEFAULT_INDENT_TYPE = IndentType.NONE;
            private final int DEFAULT_INDENT_SIZE = 0;
            // ATTRIBUTES.
            private boolean lineBreaks;
            private IndentType indentType;
            private int indentSize;

            private Beautifier( ) {
                lineBreaks = DEFAULT_LINE_BREAKS;
                indentType = DEFAULT_INDENT_TYPE;
                indentSize = DEFAULT_INDENT_SIZE;
            }

            final public Beautifier lineBreaks( final boolean lineBreaks ) {
                this.lineBreaks = lineBreaks;
                return this;
            }

            final public Beautifier withoutLineBreaks( )
                { return lineBreaks( false ); }

            final public Beautifier withLineBreaks( )
                { return lineBreaks( true ); }

            final public Beautifier withIndentType( final IndentType indentType ) {
                if ( indentType == IndentType.NONE )
                    withIndentSizeOf( 0 );
                else if ( indentSize < 1 )
                    withIndentSizeOf( 1 );
                this.indentType = indentType;
                return this;
            }

            final public Beautifier withoutIndent( )
                { return withIndentType( IndentType.NONE ); }

            final public Beautifier withTabIndent( )
                { return withIndentType( IndentType.TAB ); }

            final public Beautifier withSpaceIndent( )
                { return withIndentType( IndentType.SPACE ); }

            final public Beautifier withIndentSizeOf( final int size ) {
                if ( size < 0 )
                    throw new IllegalArgumentException( "Argument 'size' must have a value greater than zero." );
                indentSize = size;
                return this;
            }

            final public Options and( )
                { return Options.this; }
        }

        final public class Statements {
            // CONSTANTS.
            private final char DEFAULT_ASSIGNATION = '=';
            private final char DEFAULT_DELIMITATION = ',';
            private final char[ ] DEFAULT_AGGROUPATION = { '(', ')' };
            // ATTRIBUTES.
            private char assignation;
            private char delimitation;
            private char[ ] aggroupation;

            private Statements( ) {
                assignation = DEFAULT_ASSIGNATION;
                delimitation = DEFAULT_DELIMITATION;
                aggroupation = DEFAULT_AGGROUPATION;
            }

            final public Statements forAssignation( final char assignation ) {
                this.assignation = assignation;
                return this;
            }

            final public Statements withEqualsForAssignment( )
                { return forAssignation( '=' ); }

            final public Statements withColonForAssignment( )
                { return forAssignation( ':' ); }

            final public Statements withGreaterThanForAssignment( )
                { return forAssignation( '>' ); }

            final public Statements forDelimitation( final char delimitation ) {
                this.delimitation = delimitation;
                return this;
            }

            final public Statements withCommaForDelimiter( )
                { return forDelimitation( ',' ); }

            final public Statements withSemicolonForDelimiter( )
                { return forDelimitation( ';' ); }

            final public Statements withDotForDelimiter( )
                { return forDelimitation( '.' ); }

            final public Statements forAggroupation( final char[ ] aggroupation ) {
                this.aggroupation[ 0 ] = aggroupation[ 0 ];
                this.aggroupation[ 1 ] = aggroupation[ 1 ];
                return this;
            }

            final public Statements withCurlyBracesForGrouping(  )
                { return forAggroupation( new char[ ] { '{', '}' } ); }

            final public Statements withBracketsForGrouping(  )
                { return forAggroupation( new char[ ] { '[', ']' } ); }

            final public Statements withParenthesesForGrouping(  )
                { return forAggroupation( new char[ ] { '(', ')' } ); }

            final public Statements jsonLikeStyle( ) {
                withColonForAssignment( );
                withCommaForDelimiter( );
                withCurlyBracesForGrouping( );
                return this;
            }

            final public Options and( )
                { return Options.this; }
        }
    }

    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    final private String parse( final Object object, int level ) {
        String indentCharacters = new String( new char[ level * INDENT_CHARACTERS.length ] )
                .replace( "\0", String.valueOf( INDENT_CHARACTERS[ 0 ] ) );
        StringBuilder stringBuilder = new StringBuilder(
                ( LINE_BREAKS && level == 1 ? '\n' : '\0' ) +
                        ( SHOW_ATTRIBUTES_TYPE
                                ? ( SHOW_PACKAGES
                                ? object.getClass( ).getName( )
                                : object.getClass( ).getSimpleName( ) )
                                : "" ) +
                        AGGROUPATION_CHARACTERS[ 0 ] );
        Field[ ] fields = getAllFields( null, object.getClass( ) );
        for ( short i = 0; i < fields.length; i++ ) {
            fields[ i ].setAccessible( true );
            String fieldClassName = (
                    SHOW_ATTRIBUTES_TYPE
                            ? ( SHOW_PACKAGES
                            ? fields[ i ].getType( ).getName( )
                            : fields[ i ].getType( ).getSimpleName( ) )
                            : "" );
            String fieldName = fields[ i ].getName( );
            if ( i > 0 )
                stringBuilder.append( DELIMITATION_CHARACTER );
            if ( LINE_BREAKS )
                stringBuilder.append( '\n' );
            stringBuilder
                    .append( indentCharacters )
                    .append( fieldClassName )
                    .append( ' ' )
                    .append( fieldName )
                    .append( ASSIGNATION_CHARACTER )
                    .append( ' ' );
            try {
                if ( fields[ i ].getType( ).getSimpleName( ).equalsIgnoreCase( "String" ) ) {
                    stringBuilder.append( "\"" + fields[ i ].get( object ) + "\"" );
                } else if ( ClassUtils.isPrimitiveOrWrapper( fields[ i ].getType( ) ) ) {
                    if ( fields[ i ].getType( ).getSimpleName( ).equalsIgnoreCase( "char" ) )
                        stringBuilder.append( "'" + fields[ i ].get( object ) + "'" );
                    else
                        stringBuilder.append( fields[ i ].get( object ) );
                } else if ( fields[ i ].get( object ) != null ) {
                    if ( !fields[ i ].getType( ).isEnum( ) && fields[ i ].get( object ) instanceof NubeObject  )
                        stringBuilder.append( parse( fields[ i ].get( object ), level + 1 ) );
                    else
                        stringBuilder.append( fields[ i ].get( object ) );
                } else {
                    stringBuilder.append( "null" );
                }

            } catch ( IllegalAccessException e ) {
                stringBuilder.append( "UNKNOWN" );
            }
        }
        if ( LINE_BREAKS )
            stringBuilder.append( '\n' );
        stringBuilder.append( indentCharacters.substring( 0, indentCharacters.length( ) - 1 * INDENT_CHARACTERS.length ) + AGGROUPATION_CHARACTERS[ 1 ] );
        return stringBuilder.toString( );
    }

    @NotNull
    private static Field[ ] getAllFields(
            Map< Integer, Field > fields,
            @NotNull final Class< ? > clazz ) {
        if ( fields == null )
            fields = new HashMap< >( );
        // Accesible fields.
        fieldsMapping( fields, clazz.getFields( ) );
        // Declared fields.
        fieldsMapping( fields, clazz.getDeclaredFields( ) );
        if ( clazz.getSuperclass( ) != null && !ClassUtils.isPrimitiveOrWrapper( clazz.getSuperclass( ) ) )
            getAllFields( fields, clazz.getSuperclass( ) );
        return fields.values( ).toArray( new Field[ fields.size( ) ] );
    }

    private static void fieldsMapping(
            Map< Integer, Field > fieldsMap,
            final Field[ ] fields ) {
        if ( fields != null )
            for ( final Field field : fields )
                fieldsMap.put( field.hashCode( ), field );
    }


    public static void main( String[ ] args ) {
        MongoProvider mongoProvider = MongoProvider.builder( ).build( );
        _LOG.info( "====== TEST ======" );
        _LOG.info(
                StringParser.of( mongoProvider )
                        .beautify( )
                        .withLineBreaks( )
                        .withSpaceIndent( )
                        .withIndentSizeOf( 4 )
                        .and( )
                        .useStatements( )
                        .jsonLikeStyle( )
                        .and( )
                        .apply( ).parse( ) );
        _LOG.info(
            StringParser.of( mongoProvider )
                    .beautify( )
                        .withLineBreaks( )
                        .withTabIndent( )
                        .and( )
                    .useStatements( )
                        .jsonLikeStyle( )
                        .and( )
                    .apply( ).parse( ) );
    }
}
