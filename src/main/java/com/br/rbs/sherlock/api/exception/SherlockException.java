package com.br.rbs.sherlock.api.exception;

/**
 * General exception for Sherlock. This exception has a Code that can
 * encapsulate a type more Specific of error mapped by the same exception.
 * User: helmedeiros
 */
public class SherlockException extends Exception{
    private int errorCode;

    public SherlockException(Exception e) { super(e); }

    public SherlockException(final String msg) { super(msg); }

    public SherlockException(String msg, Exception e) { super(msg, e); }

    public SherlockException(int errorCode, String msg) {
        super(msg); this.errorCode = errorCode;
    }

    public int getErrorCode() { return errorCode; }

    public void setErrorCode(int errorCode) { this.errorCode = errorCode; }

    @Override
    public String toString() {
        return super.toString() + ((errorCode < 0) ? "\nError Cod.: " + errorCode : "");
    }
}
