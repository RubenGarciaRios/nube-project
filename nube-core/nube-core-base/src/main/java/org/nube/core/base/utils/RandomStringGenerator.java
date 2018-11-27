/*
 *  Developed by Rubén García Ríos
 *  Last modified 27/11/18 23:52
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Random String Generator.
 * Generates a random string based on the values ​​of the Builder.
 *
 * @author Rubén García Ríos
 */
public final class RandomStringGenerator
        implements Serializable {
    private final static Logger _LOG = LogManager.getLogger( RandomStringGenerator.class );
    private static final long serialVersionUID = -6851590086073781118L;
    // CONSTANTS.
    private final char[ ] CHARACTERS;
    private int LENGTH;
    private boolean GROUP_ENABLED;
    private char GROUP_SEPARATOR;
    private int GROUP_LENGTH;
    private final Random RANDOM;
    // CONSTRUCTORS.
    /**
     * Instantiates a new Random String Generator.
     *
     * @param builder the builder
     */
    public RandomStringGenerator( final Builder builder ) {
        _LOG.debug( "Instancing [{}] with builder values: {}",
                    this.getClass( ).getName( ),
                    builder );
        CHARACTERS = builder.acceptedCharacters;
        LENGTH = builder.length;
        GROUP_ENABLED = builder.groupEnabled;
        GROUP_SEPARATOR = builder.groupSeparator;
        GROUP_LENGTH = builder.groupLength;
        RANDOM = builder.random;
        _LOG.debug( "[{}] has been instanced successfully: {}",
                    this.getClass( ).getName( ),
                    this.toString( ) );
    }
    //formatter:off
    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder( )
        { return new Builder( ); }

    /**
     * Generate a random string.
     *
     * @return the random string
     */
    final public String generate( ) {
        _LOG.debug( "Generating new random string..." );
        StringBuilder stringBuilder = new StringBuilder( );
        if ( GROUP_ENABLED ) {
            for ( int i = 0; i < GROUP_LENGTH; i++ ) {
                if ( i != 0 )
                    stringBuilder.append( GROUP_SEPARATOR );
                stringBuilder.append( generate( LENGTH, CHARACTERS, RANDOM ) );
            }
        } else stringBuilder.append( generate( LENGTH, CHARACTERS, RANDOM ) );
        _LOG.debug( "Random string has been generated successfully: {}",
                    stringBuilder.toString( ) );
        return stringBuilder.toString( );
    }
    //formatter:on

    /**
     * Builder.
     * Used to create a new {@link RandomStringGenerator} instance with parameterized values.
     *
     * @author Rubén García Ríos.
     */
    public static class Builder
            implements Serializable {
        private final static Logger _LOG = LogManager.getLogger( Builder.class );
        private static final long serialVersionUID = -2289981810761652569L;
        // CONSTANTS.
        /**
         * DEFAULT_ACEPTED_LETTERS constant.
         */
        public static final String DEFAULT_ACEPTED_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        /**
         * DEFAULT_ACEPTED_DIGITS constant.
         */
        public static final String DEFAULT_ACEPTED_DIGITS = "0123456789";
        /**
         * ALPHANUMERIC constant.
         */
        public static final String ALPHANUMERIC =
                DEFAULT_ACEPTED_LETTERS +
                DEFAULT_ACEPTED_LETTERS.toLowerCase( Locale.ROOT ) +
                DEFAULT_ACEPTED_DIGITS;
        /**
         * DEFAULT_LENGTH constant.
         */
        public static final int DEFAULT_LENGTH = 5;
        /**
         * DEFAULT_GROUP_ENABLED constant.
         */
        public static final boolean DEFAULT_GROUP_ENABLED = false;
        /**
         * DEFAULT_GROUP_LENGTH constant.
         */
        public static final int DEFAULT_GROUP_LENGTH = 5;
        /**
         * DEFAULT_GROUP_SEPARATOR constant.
         */
        public static final char DEFAULT_GROUP_SEPARATOR = '-';
        /**
         * DEFAULT_RANDOM constant.
         */
        public static final Random DEFAULT_RANDOM = new SecureRandom( );
        // ATTRIBUTES.
        private char[ ] acceptedCharacters;
        private int length;
        private boolean groupEnabled;
        private char groupSeparator;
        private int groupLength;
        private Random random;
        // CONSTRUCTORS.
        /**
         * Instantiates a new Builder.
         */
        public Builder( ) {
            acceptedCharacters = ALPHANUMERIC.toCharArray( );
            length = DEFAULT_LENGTH;
            groupEnabled = DEFAULT_GROUP_ENABLED;
            groupLength = DEFAULT_GROUP_LENGTH;
            groupSeparator = DEFAULT_GROUP_SEPARATOR;
            random = DEFAULT_RANDOM;
            _LOG.debug( "Instancing [{}] with default values: {}",
                        this.getClass( ).getName( ),
                        this.toString( ) );
        }
        //formatter:off
        /**
         * Gets serial version uid.
         *
         * @return the serial version uid
         */
        public static long getSerialVersionUID( )
            { return serialVersionUID; }

        /**
         * Build random string generator with the parametrized values
         * used in Builder.
         *
         * @return the random string generator
         */
        public RandomStringGenerator build( )
            { return new RandomStringGenerator( this ); }

        /**
         * Length of random generated string.
         * If random generated string is not grouped {@link Builder#groupEnabled} is false,
         * indicates the final length of the random string, else indicates the length of
         * te unitary grouping string.
         * <p>
         *     Examples:
         *     <b>X4AdL0</b> <code>lenth = 6</code>
         *     <b>X4AdL0-J1Mr7k-2bZyTp</b> <code>lenth = 6, groupLength = 3</code>
         * </p>
         *
         * @param length the length
         * @return {@code this}
         */
        public Builder length( final int length ) {
            this.length = length < 2
                    ? DEFAULT_LENGTH
                    : length;
            _LOG.debug( "The attribute length has ben assigned succesfully with value: {}",
                        this.length );
            return this;
        }

        /**
         * Grouping enabled.
         * If this flag is enabled, indicates that string must have groupings.
         *
         * @param groupEnabled the group enabled
         * @return {@code this}
         */
        public Builder groupEnabled( final boolean groupEnabled ) {
            this.groupEnabled = groupEnabled;
            _LOG.debug( "The attribute groupEnabled has ben assigned succesfully with value: {}",
                        this.groupEnabled );
            return this;
        }

        /**
         * Grouping length.
         * Indicates the nuber of groupings of the random generated string, if flag groupEnable
         * {@link Builder#groupEnabled} is true.
         * <p>
         *     Examples:
         *     <b>X4AdL0-J1Mr7k-2bZyTp</b> <code>groupLength = 3</code>
         *     <b>X4AdL0-J1Mr7k-2bZyTp-dRfgK9-pHv8WS</b> <code>groupLength = 5</code>
         * </p>
         *
         * @param groupLength the group length
         * @return {@code this}
         */
        public Builder groupLength( final int groupLength ) {
            this.groupLength = groupLength < 1
                    ? DEFAULT_GROUP_LENGTH
                    : groupLength;
            _LOG.debug( "The attribute groupLength has ben assigned succesfully with value: {}",
                        this.groupLength );
            return this;
        }

        /**
         * Grouping separator.
         * Character grupings separator.
         * <p>
         *     Examples:
         *     <b>X4AdL0-J1Mr7k-2bZyTp</b> <code>groupSeparator = '-'</code>
         *     <b>X4AdL0_J1Mr7k_2bZyTp</b> <code>groupSeparator = '_'</code>
         *     <b>X4AdL0/J1Mr7k/2bZyTp</b> <code>groupSeparator = '/'</code>
         * </p>
         *
         * @param groupSeparator the group separator
         * @return {@code this}
         */
        public Builder groupSeparator( final char groupSeparator ) {
            this.groupSeparator = groupSeparator;
            _LOG.debug( "The attribute groupSeparator has ben assigned succesfully with value: {}",
                        this.groupSeparator );
            return this;
        }

        /**
         * Accepted characters builder.
         *
         * @param acceptedCharacters the accepted characters
         * @return {@code this}
         */
        public Builder acceptedCharacters( final char[ ] acceptedCharacters ) {
            this.acceptedCharacters = acceptedCharacters == null
                    ? ALPHANUMERIC.toCharArray( )
                    : acceptedCharacters;
            _LOG.debug( "The attribute acceptedCharacters has ben assigned succesfully with value: {}",
                        this.acceptedCharacters );
            return this;
        }

        /**
         * Random builder.
         *
         * @param random the random
         * @return {@code this}
         */
        public Builder random( final Random random ) {
            this.random = random == null
                    ? DEFAULT_RANDOM
                    : random;
            _LOG.debug( "The attribute random has ben assigned succesfully with value: {}",
                        this.random );
            return this;
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( !( o instanceof Builder ) ) return false;
            Builder builder = ( Builder ) o;
            return length == builder.length &&
                    groupEnabled == builder.groupEnabled &&
                    groupSeparator == builder.groupSeparator &&
                    groupLength == builder.groupLength &&
                    Arrays.equals( acceptedCharacters, builder.acceptedCharacters ) &&
                    Objects.equals( random, builder.random );
        }

        @Override
        public int hashCode( ) {
            int result = Objects.hash( length, groupEnabled, groupSeparator, groupLength, random );
            result = 31 * result + Arrays.hashCode( acceptedCharacters );
            return result;
        }

        @Override
        public String toString( ) {
            return this.getClass( ).getName( ) + "{" +
                    "acceptedCharacters=" + Arrays.toString( acceptedCharacters ) +
                    ", length=" + length +
                    ", groupEnabled=" + groupEnabled +
                    ", groupSeparator=" + groupSeparator +
                    ", groupLength=" + groupLength +
                    ", random=" + random + '}';
        }
        //formatter:on
    }

    //formatter:off
    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }

    private char[ ] generate( int length, char[ ] characters, Random random ) {
        char[ ] buffer = new char[ length ];
        for ( int i = 0; i < length; ++i )
            buffer[ i ] = characters[ random.nextInt( characters.length ) ];
        return  buffer;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof RandomStringGenerator ) ) return false;
        final RandomStringGenerator that = ( RandomStringGenerator ) o;
        return LENGTH == that.LENGTH &&
               GROUP_ENABLED == that.GROUP_ENABLED &&
               GROUP_SEPARATOR == that.GROUP_SEPARATOR &&
               GROUP_LENGTH == that.GROUP_LENGTH &&
               Arrays.equals( CHARACTERS, that.CHARACTERS ) &&
               Objects.equals( RANDOM, that.RANDOM );
    }

    @Override
    public int hashCode( ) {
        int result = Objects.hash( LENGTH, GROUP_ENABLED, GROUP_SEPARATOR, GROUP_LENGTH, RANDOM );
        result = 31 * result + Arrays.hashCode( CHARACTERS );
        return result;
    }

    @Override
    public String toString( ) {
        return this.getClass( ).getName( ) + "{" +
                "CHARACTERS=" + Arrays.toString( CHARACTERS ) +
                ", LENGTH=" + LENGTH +
                ", GROUP_ENABLED=" + GROUP_ENABLED +
                ", GROUP_SEPARATOR=" + GROUP_SEPARATOR +
                ", GROUP_LENGTH=" + GROUP_LENGTH +
                ", RANDOM=" + RANDOM + '}';
    }
    //formatter:on
}
