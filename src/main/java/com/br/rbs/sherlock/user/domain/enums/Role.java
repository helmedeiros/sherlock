package com.br.rbs.sherlock.user.domain.enums;

/**
 * Represent
 */
public enum Role {
    SUPER_ADMIN, //somebody with access to the site network administration features and all other features. See the Create a Network article.
    ADMINISTRATOR, //somebody who has access to all the administration features within a single site.
    SUBSCRIBER //somebody who can only manage their profile.
}