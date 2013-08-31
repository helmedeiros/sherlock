package com.br.rbs.sherlock.api.exception;

/**
 * Main exception for architectural errors.
 * User: helmedeiros
 */
public class ArqException extends SherlockException {
    private boolean readable;

    public ArqException(String msg) {
        super(msg);
    }

    public ArqException(Exception e) {
        super(e);
    }

    public ArqException(String mensagem, Exception e) {
        super(mensagem, e);
    }

    public ArqException(int codErro, String msg) {
        super(codErro, msg);
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }
}
