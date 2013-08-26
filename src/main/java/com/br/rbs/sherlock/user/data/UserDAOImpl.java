package com.br.rbs.sherlock.user.data;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UserDAOImpl implements UserDAO {
    public static Map<String, String> users = new TreeMap<String, String>();

    public UserDAOImpl() {
    }

    public void save(String customerName, String user) {
        users.put(user, customerName);
    }

    public boolean find(String user) {
        return users.containsKey(user);
    }

    public Set<Map.Entry<String, String>> findAll() {
        return users.entrySet();
    }
}