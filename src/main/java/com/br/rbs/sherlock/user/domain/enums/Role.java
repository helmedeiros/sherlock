package com.br.rbs.sherlock.user.domain.enums;

/**
 * Represents the user existent roles in the system.
 */
public enum Role {
    SUPER_ADMIN(1), //somebody with access to the site network administration features and all other features. See the Create a Network article.
    ADMINISTRATOR(2), //somebody who has access to all the administration features within a single site.
    SUBSCRIBER(3), //somebody who can only manage their profile.
    ANONYMOUS(4); //somebody who can only log interactions.

    private final int id;

    Role(int id) {
        this.id = id;
    }
}