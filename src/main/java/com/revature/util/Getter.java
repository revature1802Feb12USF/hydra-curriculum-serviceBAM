package com.revature.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used to specify the name of a field's getter method.
 * 
 * @author Ricky Baker (1802-Matt)
 * @see ReflectionUtils
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Getter {
    /**
     * Specifies the getter method's name. An empty String specifies a default getter
     * method name using Java naming conventions.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @return The name of the specified getter method. Default value is an 
     *          empty string.
     */
    String value() default "";
}
