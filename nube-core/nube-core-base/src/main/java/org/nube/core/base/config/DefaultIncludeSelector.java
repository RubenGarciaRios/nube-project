package org.nube.core.base.config;

public class DefaultIncludeSelector
        extends AbstractIncludeSelector {
    private static final long serialVersionUID = -2625330094926074403L;

    public static long getSerialVersionUID( ) {
        return serialVersionUID;
    }

    @Override
    public String toString( ) {
        return "DefaultIncludeSelector{" +
                "_LOG=" + _LOG +
                ", defaultClasses=" + defaultClasses +
                '}';
    }
}
