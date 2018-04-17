package com.revature.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A utility class possessing methods for performing reflection-based actions.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @author Ricky Baker (1802-Matt)
 * 
 * @version 2.0
 */
public final class ReflectionUtils {
    
    /** 
     * Generates a list of possible getter method names for the field {@code f}.<br>
     * <br>
     * Default names follow the convention: <br>
     * <ul>
     *  <li>For non-boolean fields:
     *      <ul>
     *          <li>{@code get*()}</li>        
     *      </ul>
     *  </li>
     *  <li>For boolean fields:
     *      <ul>
     *          <li>{@code is*()}</li>
     *          <li>{@code has*()}</li>
     *          <li>{@code get*()}</li>
     *      </ul>
     *  </li>
     * </ul>
     * 
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param f The field to generate default getter method names for.
     * @return A list of possible getter method names for {@code f}.
     * 
     * @author Ricky Baker (1802-Matt)
     */
    @SuppressWarnings("unused")
    private static List<String> generateDefaultGetterNames(Field f) {
        List<String> getterNameCandidates = new ArrayList<>();
        final String[] PREFIXES = 
            f.getType().equals(Boolean.class) 
            || f.getType().equals(boolean.class) ?
                new String[]{"has", "is", "get"} 
                : new String[]{"get"};
        
        final String SUFFIX = f.getName().replaceFirst(".", 
                "" + Character.toUpperCase(f.getName().charAt(0)));
        
        for(String prefix : PREFIXES) {
            getterNameCandidates.add(prefix + SUFFIX);
        }
        
        return getterNameCandidates;
    }
    
    
    /** 
     * Generates a list of possible setter method names for the field {@code f}.<br>
     * <br>
     * Default names follow the convention: <br>
     * <ul>
     *  <li>For non-boolean fields:
     *      <ul>
     *          <li>{@code set*()}</li>        
     *      </ul>
     *  </li>
     *  <li>For boolean fields:
     *      <ul>
     *          <li>{@code setIs*()}</li>
     *          <li>{@code setHas*()}</li>
     *          <li>{@code set*()}</li>
     *      </ul>
     *  </li>
     * </ul>
     * 
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param f The field to generate default setter method names for.
     * @return A list of possible setter method names for {@code f}.
     * 
     * @author Ricky Baker (1802-Matt)
     */
    private static List<String> generateDefaultSetterNames(Field f) {
        List<String> setterNameCandidates = new ArrayList<>();
        final String[] PREFIXES =
            f.getType().equals(Boolean.class)
            || f.getType().equals(boolean.class) ?
                new String[]{"setHas", "setIs", "set"}
                : new String[]{"set"};
        
        final String SUFFIX = f.getName().replaceFirst(".",
                "" + Character.toUpperCase(f.getName().charAt(0)));
        
        for(String prefix : PREFIXES) {
            setterNameCandidates.add(prefix + SUFFIX);
        }
        
        return setterNameCandidates;
    }
    
    
    /**
     * Retrieves the setter method for the field {@code field}.<br>
     * The setter method expected should have a parameter list consisting only
     * of the field type.
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param field The field to find a setter method for.
     * @return The setter method if one was found;
     *          otherwise, {@literal null} is returned.
     * 
     * @author Ricky Baker (1802-Matt)
     */
    public static Method findSetter(Field field) {
        Class<?> declaringClass = field.getDeclaringClass();
        
        Setter setterAnnotation = field.getAnnotation(Setter.class);
        String setterName = setterAnnotation == null ? "" : setterAnnotation.value();
        Method setterMethod;
        
        if(setterName.isEmpty()) {
            // Attempt to find a setter method using Java naming conventions.
            List<String> defaultSetterNames = generateDefaultSetterNames(field);
            
            Optional<String> defaultSetterName = 
                defaultSetterNames.stream().filter((String name) -> {
                    try {
                        return Objects.nonNull(declaringClass.getDeclaredMethod(name, 
                                field.getType()));
                    } catch (NoSuchMethodException ex) {
                        return false;
                    }
                }).findFirst();
            
            if(!defaultSetterName.isPresent())
                setterMethod = null;
            else {
                try {
                    setterMethod = declaringClass.getDeclaredMethod(defaultSetterName.get(), field.getType());
                } catch (NoSuchMethodException | SecurityException ex) {
                    setterMethod = null;
                }
            }
        } else { // Setter specified explicitly.
            try {
                setterMethod = declaringClass.getDeclaredMethod(
                        setterName, field.getType());
            } catch (NoSuchMethodException | SecurityException ex) {
                setterMethod = null;
            }
        }
        
        return setterMethod;
    }
    
    
    /**
     * Retrieves the getter method for the field {@code field}.<br>
     * The getter method expected should have an empty parameter list.
     * 
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param field The field to find a getter method for.
     * @return The getter me thod if one was found;
     *          otherwise, {@literal null} is returned.
     *          
     * @author Ricky Baker (1802-Matt)
     */
    public static Method findGetter(Field field) {
        Class<?> declaringClass = field.getDeclaringClass();
        
        Getter getterAnnotation = field.getAnnotation(Getter.class);
        String getterName = getterAnnotation == null ? "" : getterAnnotation.value();
        Method getterMethod;
        
        if(getterName.isEmpty()) { 
            // Attempt to find a getter method using Java naming conventions.
            List<String> defaultGetterNames = generateDefaultGetterNames(field);
            
            Optional<String> defaultGetterName = 
                defaultGetterNames.stream().filter((String name) -> {
                    try {
                        return Objects.nonNull(declaringClass.getDeclaredMethod(name));
                    } catch (NoSuchMethodException ex) {
                        return false;
                    }
                }).findFirst();
            
            if(!defaultGetterName.isPresent())
                getterMethod = null;
            else {
                try {
                    getterMethod = declaringClass.getDeclaredMethod(defaultGetterName.get());
                } catch (NoSuchMethodException | SecurityException ex) {
                    getterMethod = null;
                }
            }
        } else { // Getter specified explicitly.
            try {
                getterMethod = declaringClass.getDeclaredMethod(getterName);
            } catch (NoSuchMethodException | SecurityException ex) {
                getterMethod = null;
            }
        }
        
        return getterMethod;
    }
    
    
    /**
     * Gets {@code clazz} and all of its superclasses, excluding {@code Object}.
     * 
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param clazz The class to produce an inheritance list from.
     * @return A list of inherited classes starting from the class which 
     *          directly inherits {@code Object} to the class represented by 
     *          {@code clazz}.
     *          
     * @author Ricky Baker (1802-Matt)
     */
    public static List<Class<?>> getAllSuperclasses(Class<?> clazz) {
        List<Class<?>> clazzes = new ArrayList<>();
        
        if(clazz == null || clazz.isPrimitive()) {
            return clazzes;
        }
        
        while(!clazz.equals(Object.class)) {
            clazzes.add(clazz);
            clazz = clazz.getSuperclass();
        }
        
        Collections.reverse(clazzes);
        
        return clazzes;
    }
    
    
    /**
     * Copies all non-null fields from {@code src} to {@code target}.
     * Fields which do not have a valid getter and/or setter are ignored.
     * Fields annotated with {@link IgnoreField} are also ignored during the copy.
     * 
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param <T> The type of {@code src} and {@code target}.
     * 
     * @param target The target object to copy fields to.
     * @param src The source object to copy non-null fields from.
     * 
     * @return {@code src} if {@code target} is null; otherwise, {@code target}
     *          is returned.
     * 
     * @throws IllegalArgumentException {@code target} and {@code src} are not
     *          the same type.
     *          
     * @author Ricky Baker (1802-Matt)
     */
    public static <T> T deepCopyNonNull(T target, T src) throws IllegalArgumentException {
        
        if(target == null) {
            return src;
        } else if(src == null) {
            return target;
        }
        
        if(!target.getClass().equals(src.getClass())) {
            MessageFormat msg = new MessageFormat(
                    "{0} ({1}) must be the same class as {2} ({3}).");
            
            throw new IllegalArgumentException(msg.format(new String[]{
                "to", target.getClass().getName(),
                "from", src.getClass().getName()
            }));
        }
        
        List<Class<?>> clazzes = getAllSuperclasses(target.getClass());
        
        clazzes.forEach(clazz -> {
            List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
            fields.stream().filter(field -> { // Filter out ignored fields.
                IgnoreField ignoreAnnotation = field.getAnnotation(IgnoreField.class);
                
                if(ignoreAnnotation == null) {
                    return true;
                } else {
                    List<ReflectionOp> ops = Arrays.asList(ignoreAnnotation.value());
                    return ops.stream().noneMatch(op -> { // true if no ignore flag found.
                        switch(op) {
                        case ALL:
                        case COPY:
                            return true; // Ignore flag found.
                        default:
                            return false;
                        }
                    });
                }
            }).forEach(field -> {
                Method getter = findGetter(field);
                Method setter = findSetter(field);
                
                Object srcObj;
                
                try {
                    if(Objects.nonNull(srcObj = getter.invoke(src))) {
                        setter.invoke(target, srcObj);
                    }
                } catch (IllegalAccessException | IllegalArgumentException 
                        | InvocationTargetException ex) {}
            });
        });
        
        return target;
    }
    
    
    /**
     * Performs an overall equality test by testing each field of {@code a} to 
     * the respective field of {@code b}. The equality test is performed only on
     * fields that provide an accessible getter method to access the value.
     * Equality is checked using {@code equals(...)} method of the field.
     * 
     * <br>
     * <br>
     * <b>Last Modified:</b>
     * <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
     * 
     * @param <T> The type of {@code a} and {@code b}.
     * @param a The object to compare to {@code b}.
     * @param b The object to compare to {@code a}.
     * 
     * @return {@literal true} if all fields of {@code a} and {@code b} are equal. Otherwise, {@literal false}.
     *          Otherwise, {@literal false}.
     * @since 2.0
     * 
     * @author Ricky Baker (1802-Matt)
     */
    public static <T> boolean testEquality(final T a, final T b) {
        boolean isEqual;
        
        if(a == b) { // Both null or refer to the same item.
            isEqual = true;
        } else if(a == null || b == null) { // Either are null (cannot be both).
            isEqual = false;
        } else if(!a.getClass().equals(b.getClass())) { // Not of the same lowest-level class.
            isEqual = false;
        } else { // Test each field for equality via the field's equals() method.
            List<Field> fieldList = Arrays.asList(a.getClass().getFields());
            
            isEqual = fieldList.stream().filter(field -> { // Filter out ignored fields.
                IgnoreField ignoreAnnotation = field.getAnnotation(IgnoreField.class);
                
                if(ignoreAnnotation == null) {
                    return true;
                } else {
                    List<ReflectionOp> ops = Arrays.asList(ignoreAnnotation.value());
                    return ops.stream().noneMatch(op -> { // true if no ignore flag found.
                        switch(op) {
                        case ALL:
                        case EQUALITY:
                            return true; // Ignore flag found.
                        default:
                            return false;
                        }
                    });
                }
            }).allMatch(field -> {
                boolean fieldIsEqual = false;
                
                try {
                    Method getter = findGetter(field);
                    
                    if(getter == null) {
                        return true; // No available accessor -> ignore field.
                    }
                    
                    Object aField = getter.invoke(a);
                    Object bField = getter.invoke(b);
                    
                    if(aField == null) {
                        fieldIsEqual = (bField == null);
                    } else if(bField == null) { // a non-null, b null
                        fieldIsEqual = false;
                    } else { // Test equality with  aField's custom equals().
                        fieldIsEqual = aField.equals(bField);
                    }
                } catch (IllegalArgumentException | IllegalAccessException 
                        | InvocationTargetException ex) {
                    // Unable to access getter -> ignore field.
                    fieldIsEqual = true;
                }
                
                return fieldIsEqual;
            });
        }
        
        return isEqual;
    }
}
