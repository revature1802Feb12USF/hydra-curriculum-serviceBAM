package com.revature.hydra.curriculum.util;

import java.lang.reflect.Field;

import org.springframework.util.ClassUtils;

/**
 * A utilities class for performing reflection-based actions.
 */
public class ReflectionUtils {
	
	/**
	 * Executes the getter method for the given field f of the object obj.
	 * 
	 * Runs the same as: {@code return obj.getX();}
	 * 
	 * If the type is a boolean or Boolean, then it attempts to call the following in order:
	 * {@code isX()}, {@code hasX()}, {@code getX()}.
	 * 
	 * 'X' is the field name converted into title case.
	 * 
	 * @param f	The field whose getter method to call.
	 * @param obj The object the getter method belongs to.
	 * @return The return of the getter method of the field f.
	 */
	public static <T> Object runGetter(Field f, T obj) {
		StringBuilder getterNameBuilder = new StringBuilder();
		List<String> names
		if(f.getType().equals(boolean.class) || f.getType().equals(Boolean.class)) {
			final String[] BOOL_PREFIXES = {
				"is", "has", "get"
			};
			
			
		} else {
			
		}
		
		
		ClassUtils.getMethod(obj.getClass(), methodName, paramTypes)
		return null;
	}
}
