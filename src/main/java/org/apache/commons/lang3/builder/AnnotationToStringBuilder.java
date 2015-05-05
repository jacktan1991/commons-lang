package org.apache.commons.lang3.builder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.annotation.ExcludeFields;

public class AnnotationToStringBuilder extends ReflectionToStringBuilder {
    
    public static final String[] EMPTY_EXCLUDE_FIELD_NAMES = new String[]{};
    private static Map<Class<?>, ExcludeFields> classExcludeFields;
    
    static {
        classExcludeFields = new HashMap<Class<?>, ExcludeFields>();
    }
    
    {
        initExcludeFieldNames();
    }
    
    static ExcludeFields getExcludes(Class<?> annotatedClazz){
        
        if (classExcludeFields.containsKey(annotatedClazz)){
            return classExcludeFields.get(annotatedClazz);
        }
        
        ExcludeFields excludeFields_a = null;
        
        try {
            excludeFields_a = annotatedClazz.getAnnotation(ExcludeFields.class);
        } catch (Exception ex) {
            excludeFields_a = null;
        }
        
        classExcludeFields.put(annotatedClazz, excludeFields_a);
        
        return excludeFields_a;
    }
    
    private void initExcludeFieldNames() {
        if (isPrimType(getObject())) {
            excludeFieldNames = EMPTY_EXCLUDE_FIELD_NAMES;
        }
        if (excludeFieldNames == null) {
            Class<?> thisClazz = getObject().getClass();
            ExcludeFields classExcludes = getExcludes(thisClazz);
            if (classExcludes == null) {
                excludeFieldNames = EMPTY_EXCLUDE_FIELD_NAMES;
            } else {
                excludeFieldNames = classExcludes.value();
            }
        }
    }
    
    private static boolean isPrimType(Object obj) {
        if (obj == null) {
            return true;
        }
        
        if (obj instanceof String || obj instanceof Number || obj instanceof Boolean || obj instanceof Character) {
            return true;
        }
        
        return false;
    }

    public AnnotationToStringBuilder(Object object) {
        super(object);
        // TODO Auto-generated constructor stub
    }

    public AnnotationToStringBuilder(Object object, StringBuffer buffer) {
        super(object, null, buffer);
        // TODO Auto-generated constructor stub
    }

    public <T> AnnotationToStringBuilder(T object, StringBuffer buffer, Class<? super T> reflectUpToClass,
            boolean outputTransients, boolean outputStatics) {
        super(object, null, buffer, reflectUpToClass, outputTransients,
                outputStatics);
        // TODO Auto-generated constructor stub
    }
    
    public static <T> String annotatedToString(
        final T object,
        final ToStringStyle style,
        final boolean outputTransients,
        final Class<? super T> reflectUpToClass) {
        return null;
    }
    
    public static <T> String toString(
            final T object, final ToStringStyle style, final boolean outputTransients,
            final boolean outputStatics, final Class<? super T> reflectUpToClass) {
        return null;
    }
    
    @Override
    public AnnotationToStringBuilder setExcludeFieldNames(String... excludeFieldNamesParam) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("All exclude field names settings delegated to annotations.");
    }
        
    @Override
    public String toString()
    {
        return super.toString();
    }

}
