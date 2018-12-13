/*
 *  Developed by Rubén García Ríos
 *  Last modified 5/12/18 11:00
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Password operations for common usage.
 *
 * @author Rubén García Ríos
 */
public class PasswordUtilities
        implements Serializable, Cloneable {
    private static final long serialVersionUID = 8575010547535017748L;

    /**
     * Erase in memory stored passord to reduces the window of opportunity for an attacker.
     * <p>
     * For more info:
     * @see <a href="https://stackoverflow.com/questions/8881291/why-is-char-preferred-over-string-for-passwords">
     *     Why is char preferred over string for passwords.</a>
     *
     * @param password the password
     */
    @Contract( mutates = "param1",
               value = "null -> fail" )
    public static void erase( @NotNull char[ ] password ) {
        Arrays.fill( password, '*' );
        password = null;
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( )
        { return serialVersionUID; }
}
