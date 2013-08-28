package com.br.rbs.sherlock.api.domain.enums;

/**
 * Represents all Cookies of application.
 * User: helmedeiros
 */
public enum Cookie {
    SESSION("sh_s"), USER("sh_u");

    private final String name;

    Cookie(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
