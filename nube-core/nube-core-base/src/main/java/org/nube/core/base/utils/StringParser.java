/*
 *  Developed by Rubén García Ríos
 *  Last modified 13/12/18 18:33
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * String Parser.
 * Stringify any object with custom style.
 *
 * @author Rubén García Ríos
 */
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
    private final Class< ? >[ ] RECURSIVITY_CLASSES;

    private StringParser(
            final Object object,
            final boolean showAttributesType,
            final boolean showPackages,
            final char assignation,
            final char delimitation,
            final char[ ] aggroupation,
            final boolean lineBreaks,
            final char[ ] indentCharacters,
            final Class< ? >[ ] recursivityClasses ) {
        OBJECT = object;
        SHOW_ATTRIBUTES_TYPE = showAttributesType;
        SHOW_PACKAGES = showPackages;
        ASSIGNATION_CHARACTER = assignation;
        DELIMITATION_CHARACTER = delimitation;
        AGGROUPATION_CHARACTERS = aggroupation;
        LINE_BREAKS = lineBreaks;
        INDENT_CHARACTERS = indentCharacters;
        RECURSIVITY_CLASSES = recursivityClasses;
    }

    /**
     * Instantiates a new String Parser.
     *
     * @param options the options
     */
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
                  : new char[ ] { IndentType.NONE.value },
              options.recursivityClasses );
    }

    /**
     * Target object of parser.
     *
     * @param object the object
     *
     * @return the options
     */
    final public static Options forObject( final Object object )
        { return new Options( object ); }

    /**
     * Parse target object to string with the configured style.
     *
     * @return the string
     */
    final public String parse( )
        { return parse( OBJECT, 1 ); }

    /**
     * Indent type.
     */
    public enum IndentType {
        /**
         * None indent type.
         */
        NONE( '\0' ),
        /**
         * Tab indent type.
         */
        TAB( '\t' ),
        /**
         * Space indent type.
         */
        SPACE( ' ' );
        private char value;

        IndentType( char value )
        { this.value = value; }

        /**
         * Value character.
         *
         * @return the char
         */
        public char value( )
        { return value; }
    }

    /**
     * Options of String Parser for string customization.
     *
     * @author Rubén García Ríos
     */
    final public static class Options {
        private final boolean DEFAULT_SHOW_ATTRIBUTES_TYPE = true;
        private final boolean DEFAULT_SHOW_PACKAGES = false;
        // ATTRIBUTES.
        private final Object object;
        private boolean showAttributesType;
        private boolean showPackages;
        private Beautifier beautifier;
        private Statements statements;
        private Class< ? >[ ] recursivityClasses;

        private Options( final Object object ) {
            this.object = object;
            showAttributesType = DEFAULT_SHOW_ATTRIBUTES_TYPE;
            showPackages = DEFAULT_SHOW_PACKAGES;
            beautifier = new Beautifier( );
            statements = new Statements( );
            recursivityClasses = null;
        }

        /**
         * Options for target object of parser.
         *
         * @param object the object
         *
         * @return the options
         */
        public static Options forObject( final Object object )
            { return new Options( object ); }

        private Options setShowAttributesType( final boolean showAttributesType ) {
            this.showAttributesType = showAttributesType;
            return this;
        }

        /**
         * Show attributes data type.
         *
         * @return the options
         */
        public Options showAttributesType(  )
            { return setShowAttributesType( true ); }

        /**
         * Hide attributes data type.
         *
         * @return the options
         */
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

        /**
         * Show data type packages.
         *
         * @return the options
         */
        public Options showPackages(  )
            { return setShowPackages( true ); }

        /**
         * Hide data type packages.
         *
         * @return the options
         */
        public Options hidePackages(  )
            { return setShowPackages( false ); }

        /**
         * Beautifier options.
         *
         * @return the beautifier
         */
        final public Beautifier beautify( )
            { return beautifier; }

        /**
         * Statements to use.
         *
         * @return the statements
         */
        final public Statements useStatements( )
            { return statements; }

        /**
         * Do recursivity with instances of specified class.
         *
         * @param recursivityClasses the recursivity classes
         *
         * @return the options
         */
        final public Options doRecursivityWithInstancesOf(
                final Class< ? >... recursivityClasses ) {
            this.recursivityClasses = recursivityClasses;
            return this;
        }

        /**
         * Apply all configured options and generates the StringParser.
         *
         * @return the string parser
         */
        final public StringParser apply( )
            { return new StringParser( this ); }

        /**
         * Beautifier options.
         *
         * @author Rubén García Ríos
         */
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

            /**
             * Beutify with or without line breaks.
             *
             * @param lineBreaks the line breaks
             *
             * @return the beautifier
             */
            final public Beautifier lineBreaks( final boolean lineBreaks ) {
                this.lineBreaks = lineBreaks;
                if ( !lineBreaks )
                    withIndentType( IndentType.NONE );
                return this;
            }

            /**
             * Beutify without line breaks.
             *
             * @return the beautifier
             */
            final public Beautifier withoutLineBreaks( )
                { return lineBreaks( false ); }

            /**
             * Beutify with line breaks.
             *
             * @return the beautifier
             */
            final public Beautifier withLineBreaks( )
                { return lineBreaks( true ); }

            /**
             * Beutify with indent type.
             *
             * @param indentType the indent type
             *
             * @return the beautifier
             */
            final public Beautifier withIndentType( final IndentType indentType ) {
                if ( indentType == IndentType.NONE )
                    withIndentSizeOf( 0 );
                else if ( indentSize < 1 )
                    withIndentSizeOf( 1 );
                this.indentType = indentType;
                return this;
            }

            /**
             * Beutify without indent.
             *
             * @return the beautifier
             */
            final public Beautifier withoutIndent( )
                { return withIndentType( IndentType.NONE ); }

            /**
             * Beutify with tab indent.
             *
             * @return the beautifier
             */
            final public Beautifier withTabIndent( )
                { return withIndentType( IndentType.TAB ); }

            /**
             * Beutify with space indent.
             *
             * @return the beautifier
             */
            final public Beautifier withSpaceIndent( )
                { return withIndentType( IndentType.SPACE ); }

            /**
             * Indent size.
             *
             * @param size the size
             *
             * @return the beautifier
             */
            final public Beautifier withIndentSizeOf( final int size ) {
                if ( size < 0 )
                    throw new IllegalArgumentException( "Argument 'size' must have a value greater than zero." );
                indentSize = size;
                return this;
            }

            /**
             * Return to main options and apply beautifier changes.
             *
             * @return the options
             */
            final public Options and( )
                { return Options.this; }
        }

        /**
         * Symbol Statements to use.
         *
         * @author Rubén García Ríos
         */
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

            /**
             * Statement for assignation.
             *
             * @param assignation the assignation
             *
             * @return the statements
             */
            final public Statements forAssignation( final char assignation ) {
                this.assignation = assignation;
                return this;
            }

            /**
             * Use equals {@code =} statement for assignment.
             *
             * @return the statements
             */
            final public Statements withEqualsForAssignment( )
                { return forAssignation( '=' ); }

            /**
             * Use colon {@code :} statement for assignment.
             *
             * @return the statements
             */
            final public Statements withColonForAssignment( )
                { return forAssignation( ':' ); }

            /**
             * Use greather than {@code >} statement for assignment.
             *
             * @return the statements
             */
            final public Statements withGreaterThanForAssignment( )
                { return forAssignation( '>' ); }

            /**
             * Statements for delimitation.
             *
             * @param delimitation the delimitation
             *
             * @return the statements
             */
            final public Statements forDelimitation( final char delimitation ) {
                this.delimitation = delimitation;
                return this;
            }

            /**
             * Use comma {@code ,} statement for delimiter.
             *
             * @return the statements
             */
            final public Statements withCommaForDelimiter( )
                { return forDelimitation( ',' ); }

            /**
             * Use semicolon {@code ;} statement for delimiter.
             *
             * @return the statements
             */
            final public Statements withSemicolonForDelimiter( )
                { return forDelimitation( ';' ); }

            /**
             * Use dot {@code .} statement for delimiter.
             *
             * @return the statements
             */
            final public Statements withDotForDelimiter( )
                { return forDelimitation( '.' ); }

            /**
             * Statements for aggroupation.
             *
             * @param aggroupation the aggroupation
             *
             * @return the statements
             */
            final public Statements forAggroupation( final char[ ] aggroupation ) {
                if ( aggroupation == null )
                    throw new IllegalArgumentException( "Argument 'aggroupation' can not be null." );
                this.aggroupation[ 0 ] = aggroupation[ 0 ];
                this.aggroupation[ 1 ] = aggroupation[ 1 ];
                return this;
            }

            /**
             * Use curly braces {@code '{...}'} statements for grouping.
             *
             * @return the statements
             */
            final public Statements withCurlyBracesForGrouping(  )
                { return forAggroupation( new char[ ] { '{', '}' } ); }

            /**
             * Use brackets {@code '[...]'} statements for grouping.
             *
             * @return the statements
             */
            final public Statements withBracketsForGrouping(  )
                { return forAggroupation( new char[ ] { '[', ']' } ); }

            /**
             * Use parentheses {@code '(...)'} statements for grouping.
             *
             * @return the statements
             */
            final public Statements withParenthesesForGrouping(  )
                { return forAggroupation( new char[ ] { '(', ')' } ); }

            /**
             * Json like style statements.
             *
             * @return the statements
             */
            final public Statements jsonLikeStyle( ) {
                withColonForAssignment( );
                withCommaForDelimiter( );
                withCurlyBracesForGrouping( );
                return this;
            }

            /**
             * Return to main options and apply statements changes.
             *
             * @return the options
             */
            final public Options and( )
                { return Options.this; }
        }
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    private String parse( final Object object, int level ) {
        String indentCharacters = INDENT_CHARACTERS[ 0 ] != IndentType.NONE.value
                ? ( new String( new char[ level * INDENT_CHARACTERS.length ] )
                    .replace( "\0", String.valueOf( INDENT_CHARACTERS[ 0 ] ) ) )
                : new String( new char [ ] { IndentType.SPACE.value } );
        StringBuilder stringBuilder = new StringBuilder(
                ( LINE_BREAKS && level == 1 ? '\n' : '\0' ) +
                        ( SHOW_ATTRIBUTES_TYPE && level == 1
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
                    if ( doRecursivity( fields[ i ].get( object ) ) )
                        stringBuilder.append( parse( fields[ i ].get( object ), level + 1 ) );
                    else {
                        String stringParsedObject = fields[ i ].get( object ).toString( )
                                .replaceFirst( LINE_BREAKS ? "\n" : "", "" )
                                .replace( new String( INDENT_CHARACTERS ), INDENT_CHARACTERS[ 0 ] != IndentType.NONE.value
                                        ? indentCharacters + indentCharacters
                                        : "" );
                        if ( LINE_BREAKS && stringParsedObject.endsWith( "\n}" ) )
                            stringParsedObject = stringParsedObject.substring( 0, stringParsedObject.length( ) - 2 ) + '\n' + indentCharacters + '}';
                        if ( SHOW_ATTRIBUTES_TYPE && stringParsedObject.startsWith( fieldClassName ) )
                            stringParsedObject = stringParsedObject.substring( fieldClassName.length( ) );
                        stringBuilder.append( stringParsedObject );
                    }
                } else {
                    stringBuilder.append( "null" );
                }

            } catch ( IllegalAccessException e ) {
                stringBuilder.append( "UNKNOWN" );
            }
        }
        if ( LINE_BREAKS )
            stringBuilder.append( '\n' );
        stringBuilder.append( indentCharacters.substring( 0, indentCharacters.length( ) - INDENT_CHARACTERS.length ) + AGGROUPATION_CHARACTERS[ 1 ] );
        return stringBuilder.toString( );
    }

    private boolean doRecursivity( @Nullable Object object ){
        if ( RECURSIVITY_CLASSES == null || object == null || RECURSIVITY_CLASSES.length == 0 )
            return false;
        for ( Class< ? > clazz : RECURSIVITY_CLASSES )
            if ( clazz.isInstance( object ) )
                return true;
        return false;
    }

    @NotNull
    @Contract( "_, null -> fail" )
    private static Field[ ] getAllFields(
            Map< Integer, Field > fields,
            @NotNull final Class< ? > clazz ) {
        if ( fields == null )
            fields = new HashMap< >( );
        // Accesible fields.
        fieldsMapping( fields, clazz.getFields( ) );
        // Declared fields.
        fieldsMapping( fields, clazz.getDeclaredFields( ) );
        return fields.values( ).toArray( new Field[ fields.size( ) ] );
    }

    private static void fieldsMapping(
            Map< Integer, Field > fieldsMap,
            final Field[ ] fields ) {
        if ( fields != null )
            for ( final Field field : fields )
                fieldsMap.put( field.hashCode( ), field );
    }
}
