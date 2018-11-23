/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.enums;

import org.nube.core.base.NubeObject;
import org.springframework.http.HttpStatus;

/**
 * The enum Error codes.
 *
 * @see NubeObject
 */
public enum ErrorCodes implements NubeObject {
    /**
     * The Unknown exception.
     */
    /* ============= */
    /* == GENERAL == */
    /* ============= */
    UNKNOWN_EXCEPTION( 0x0000,
            "An exception has occured while processing your request.",
            HttpStatus.INTERNAL_SERVER_ERROR ),
    /**
     * The Access denied.
     */
    /* ============== */
    /* == SECURITY == */
    /* ============== */
    ACCESS_DENIED( 0x0F00,
            "Don’t have permission to access this resource.",
            //"Unauthorized to perform the requested operation on the given resource.",
            HttpStatus.FORBIDDEN ),
    /**
     * The Persistence layer exception.
     */
    /* ======================= */
    /* == PERSISTENCE LAYER == */
    /* ======================= */
    PERSISTENCE_LAYER_EXCEPTION( 0x0A00,
            "The requested data is currently unavailable.",
            HttpStatus.SERVICE_UNAVAILABLE ),
    /**
     * The Entity not found.
     */
    ENTITY_NOT_FOUND( 0x0A01,
            "The requested entity could not be found",
            HttpStatus.BAD_REQUEST ),
    /**
     * The Entity allready exists.
     */
    ENTITY_ALLREADY_EXISTS( 0x0A02,
            "Entity already exists.",
            HttpStatus.BAD_REQUEST ),
    /**
     * The Bad request.
     */
    /* ============= */
    /* == REQUEST == */
    /* ============= */
    BAD_REQUEST( 0x0B00,
            "Bad request.",
            HttpStatus.BAD_REQUEST ),
    /**
     * The Invalid url.
     */
    INVALID_URL( 0x0B01,
            "Invalid url, resource not found",
            HttpStatus.NOT_FOUND ),
    /**
     * The Validation error.
     */
    VALIDATION_ERROR( 0x0B02,
            "There was one or more validation error(s).",
            HttpStatus.BAD_REQUEST );

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    ErrorCodes( final int code, final String description, final HttpStatus httpStatus ) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode( ) {
        return code;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription( ) {
        return description;
    }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus( ) {
        return httpStatus;
    }
}