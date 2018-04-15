package com.revature.hydra.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used to specify fields which should be ignored.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @author Ricky Baker (1802-Matt)
 * @see ReflectionUtils
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface IgnoreField {
    
    /**
     * Specify which fields should be ignored. When no explicit values are
     * specified, it defaults to {@link ReflectionOp#ALL}. If an empty array is
     * provided, then it is equivalent to not having the tag.
     * 
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @return The list of types of reflection utility operations that should
     *          ignore this field.
     *          
     * @see ReflectionOp
     * @see Reflectionutils
     * 
     * @author Ricky Baker (1802-Matt)
     */
    ReflectionOp[] value() default {ReflectionOp.ALL}; 
}
