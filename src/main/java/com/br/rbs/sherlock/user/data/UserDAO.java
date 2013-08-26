package com.br.rbs.sherlock.user.data;

import java.util.Map;
import java.util.Set;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 8:52 AM
 */
public interface UserDAO {
    public void save(final String customerName, final String user);

    public boolean find(final String user);

    public Set<Map.Entry<String, String>> findAll();
}
