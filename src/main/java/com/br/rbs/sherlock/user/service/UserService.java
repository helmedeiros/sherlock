package com.br.rbs.sherlock.user.service;

/**
 * .
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 10:10 PM
 */
public interface UserService {
    public String createUser(final String customerName, String user);

    public String findUser(final String user);

    public String listUsers();

    public String createAnonymous(final String sessionId);

    public String listAnonymousUsers();
}
