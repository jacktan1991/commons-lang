package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.builder.annotation.ExcludeFields;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class AnnotationToStringBuilder extends ReflectionToStringBuilder{
    static Map<Class<?>, String[]> classExcludeFields;
    
    static {
        classExcludeFields = new HashMap<Class<?>, String[]>();
    }
    
    public <T> AnnotationToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics) {
        super(object, style, null, reflectUpToClass, outputTransients, outputStatics);
    }
    
    public ReflectionToStringBuilder setExcludeFieldNames(String... excludeFieldNamesParam) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("All exclude field names settings delegated to annotations.");
    }
    
    public String toString()
    {
        if (getObject() == null) {
            return getStyle().getNullText();
        }
        Class<?> thisClazz = getObject().getClass();
        
        if (excludeFieldNames == null) {
            String[] excludeFields = classExcludeFields.get(thisClazz);
            if (excludeFields == null) {
                excludeFields = getAnnotatedExclusion(thisClazz);
                classExcludeFields.put(thisClazz, excludeFields);
            }
            excludeFieldNames = excludeFields;
        }
        return super.toString();
    }
    
    static String[] getAnnotatedExclusion (Class<?> annotatedClazz) {
    
        ExcludeFields excludeFields_a = null;
        
        try {
            excludeFields_a = annotatedClazz.getDeclaredMethod("toString").getAnnotation(ExcludeFields.class);
        } catch (Exception ex) {
            excludeFields_a = null;
        }
        
        if(excludeFields_a == null){
            return new String[0];
        }
        
        String[] annotated_excludeFields = excludeFields_a.value();
        return annotated_excludeFields;
    }
    
    public static String toString(Object object, ToStringStyle style) {
        return toString(object, style, false, false, null);
    }
    
    public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class<? super T> reflectUpToClass)
    {
        return new AnnotationToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics).toString();
    }
}