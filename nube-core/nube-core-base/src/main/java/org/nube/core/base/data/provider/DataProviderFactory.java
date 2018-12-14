/*
 *  Developed by Rubén García Ríos
 *  Last modified 14/12/18 14:57
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.data.provider;

import javax.validation.constraints.NotNull;

public interface DataProviderFactory {
    @NotNull
    DataProvider getDataProvider( );
}
