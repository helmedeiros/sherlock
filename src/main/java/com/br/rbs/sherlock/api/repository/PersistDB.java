package com.br.rbs.sherlock.api.repository;

import java.io.Serializable;

/**
 * Interface that represents a object that will be persisted.
 * User: helmedeiros
 */
public interface PersistDB extends Serializable {
    public int getId();

    public void setId(int id);
}
