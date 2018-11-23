/*
 *  Developed by Rubén García Ríos
 *  Last modified 24/11/18 2:06
 *  Copyright (c) 2018 All rights reserved.
 */

package org.nube.core.base.v1.domain.messages;

import org.nube.core.base.v1.domain.NubeDomainObject;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

/**
 * The type Response message.
 *
 * @see NubeDomainObject
 */
public class ResponseMessage implements NubeDomainObject {
    private static final long serialVersionUID = -8111285253038572912L;
    /**
     * The Code.
     */
    int code;
    /**
     * The Status.
     */
    HttpStatus status;
    /**
     * The Message.
     */
    String message;
    /**
     * The Url.
     */
    String url;
    /**
     * The Errors.
     */
    List< String > errors;
    private final Date dateTime = new Date( );

    /**
     * Instantiates a new Response message.
     */
    public ResponseMessage( ) {
    }

    /**
     * Instantiates a new Response message.
     *
     * @param code    the code
     * @param status  the status
     * @param message the message
     * @param url     the url
     */
    public ResponseMessage( int code, HttpStatus status, String message, String url ) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.url = url;
    }

    /**
     * Instantiates a new Response message.
     *
     * @param code    the code
     * @param status  the status
     * @param message the message
     * @param url     the url
     * @param errors  the errors
     */
    public ResponseMessage( int code, HttpStatus status, String message, String url, List< String > errors ) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.url = url;
        this.errors = errors;
    }

    /**
     * Instantiates a new Response message.
     *
     * @param responseMessage the response message
     */
    public ResponseMessage( ResponseMessage responseMessage ) {
        this.code = responseMessage.code;
        this.status = responseMessage.status;
        this.message = responseMessage.message;
        this.url = responseMessage.url;
        this.errors = responseMessage.errors;
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID( ) {
        return serialVersionUID;
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
     * Sets code.
     *
     * @param code the code
     */
    public void setCode( int code ) {
        this.code = code;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus( ) {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus( HttpStatus status ) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage( ) {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage( String message ) {
        this.message = message;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl( ) {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl( String url ) {
        this.url = url;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public Date getDateTime( ) {
        return dateTime;
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List< String > getErrors( ) {
        return errors;
    }

    /**
     * Sets errors.
     *
     * @param errors the errors
     */
    public void setErrors( List< String > errors ) {
        this.errors = errors;
    }

    /**
     * Add error.
     *
     * @param error the error
     */
    public void addError( String error ) {
        this.errors.add( error );
    }

    /**
     * Has any error boolean.
     *
     * @return the boolean
     */
    public boolean hasAnyError( ) {
        return this.errors.size( ) > 0;
    }
}
