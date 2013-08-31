package com.br.rbs.sherlock.api.util;

/**
 * Auxiliary class to deal with strings transformations.
 * User: helmedeiros
 * Date: 8/29/13
 * Time: 6:42 PM
 */
public class TextUtils {

    /**
     * Transforms every occurrence of a single quote in double quotes.
     * Used to construct SQL queries.
     * @param original - The {@link String} to be transformed.
     * @return A new double quoted text.
     */
    public static String doublequotes(final String original) {
        return original.replaceAll("'", "''");
    }
}
