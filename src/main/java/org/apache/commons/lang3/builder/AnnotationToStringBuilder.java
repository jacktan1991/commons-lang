package org.apache.commons.lang3.builder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.annotation.ExcludeFields;

public class AnnotationToStringBuilder extends ReflectionToStringBuilder {
    
    private static Map<Class<?>, String[]> classExcludeFields;
    public static final String[] EMPTY_EXCLUDE_FIELD_NAMES = new String[]{};
    
    static {
        classExcludeFields = new HashMap<Class<?>, String[]>();
    }
    
    {
        initExcludeFieldNames();
    }
    
    private void initExcludeFieldNames() {
        if (isPrimType(getObject())) {
            excludeFieldNames = EMPTY_EXCLUDE_FIELD_NAMES;
        }
        if (excludeFieldNames == null) {
            Class<?> thisClazz = getObject().getClass();
            String[] excludeFields = classExcludeFields.get(thisClazz);
            if (excludeFields == null) {
                excludeFields = getAnnotatedExclusion(thisClazz);
                classExcludeFields.put(thisClazz, excludeFields);
            }
            excludeFieldNames = excludeFields;
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

    public AnnotationToStringBuilder(Object object, ToStringStyle style) {
        super(object, style);
        // TODO Auto-generated constructor stub
    }

    public AnnotationToStringBuilder(Object object, ToStringStyle style,
            StringBuffer buffer) {
        super(object, style, buffer);
        // TODO Auto-generated constructor stub
    }

    public <T> AnnotationToStringBuilder(T object, ToStringStyle style,
            StringBuffer buffer, Class<? super T> reflectUpToClass,
            boolean outputTransients, boolean outputStatics) {
        super(object, style, buffer, reflectUpToClass, outputTransients,
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
    
    static String[] getAnnotatedExclusion (Class<?> annotatedClazz) {
        ExcludeFields excludeFields_a = null;
        
        try {
            excludeFields_a = annotatedClazz.getAnnotation(ExcludeFields.class);
        } catch (Exception ex) {
            excludeFields_a = null;
        }
        
        if(excludeFields_a == null){
            return EMPTY_EXCLUDE_FIELD_NAMES;
        }
        
        String[] annotated_excludeFields = excludeFields_a.value();
        return annotated_excludeFields;
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
