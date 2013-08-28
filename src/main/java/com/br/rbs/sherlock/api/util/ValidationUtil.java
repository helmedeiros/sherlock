package com.br.rbs.sherlock.api.util;

import java.util.Collection;
import java.util.Map;

/**
 * Utility class to validate objects
 * @author helio_medeiros
 */
public class ValidationUtil {
	
	/**
	 * Validate if a {@link String} is not null or empty.
	 * @return True when the passed {@link String} is null or empty.
	 */
	public static boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}
	
	/**
	 * Validate an object based on its type
	 * @param o- The {@link Object} to be checked if is empty or not.
	 * @return True when the passed {@link Object} is empty based on its own rules.
	 */
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return isEmpty( (String) o);
		if (o instanceof Number) {
			Number i = (Number) o;
			return (i.intValue() == 0);
		}
		if (o instanceof Object[])
			return ((Object[]) o).length == 0;
		if (o instanceof int[])
			return ((int[]) o).length == 0;
		if (o instanceof Collection<?>)
			return ((Collection<?>) o).size() == 0;
        if (o instanceof Map<?, ?>)
            return ((Map<?, ?>) o).size() == 0;
        return false;
	}		
	
	
	
	
}
