/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.configuration;

import org.nube.core.base.config.AbstractIncludeSelector;

/**
 * Default Configuration Include Selector.
 * Include the default NUBE Environment System configuration classes, that are defined
 * in class constructor.
 *
 * @author Rubén García Ríos.
 */
public class DefaultConfigurationIncludeSelector
        extends AbstractIncludeSelector {
    private static final String _BASE_PACKAGE = "org.nube.core.configuration";
    private static final long serialVersionUID = 384506978592727275L;

    public DefaultConfigurationIncludeSelector( ) {
        super.defaultClasses.add( _BASE_PACKAGE + ".spring.DefaultSpringConfiguration" );
    }

    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    @Override
    public String toString( ) {
        return "DefaultConfigurationIncludeSelector{" +
                "_LOG=" + _LOG +
                ", defaultClasses=" + defaultClasses +
                '}';
    }
}
