package com.br.rbs.sherlock.api.exception;

/**
 * Base exception throw by any error in the persistence layer.
 * @author helmedeiros
 */
public class PersistenceException extends ArqException {

    public PersistenceException(final Exception e) { super(e); }

    public PersistenceException(final String message, final Exception e) {
        super(message, e);
    }

    public PersistenceException(final String message) {
        super(message); printStackTrace();
    }

    public PersistenceException(final int errorCode, final String message) {
        super(errorCode, message); printStackTrace();
    }
}