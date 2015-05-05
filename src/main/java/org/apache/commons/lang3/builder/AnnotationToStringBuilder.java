package org.apache.commons.lang3.builder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.annotation.ExcludeFields;
import org.apache.commons.lang3.builder.annotation.ToString;

public class AnnotationToStringBuilder extends ReflectionToStringBuilder {
    
    private static Map<Class<?>, ToString> classToStringConf;
    private static Map<Object, AnnotationToStringBuilder> buffers;
    
    static {
        classToStringConf = new HashMap<Class<?>, ToString>();
        buffers = new HashMap<Object, AnnotationToStringBuilder>();
    }
    
    static ToString getToStringConf(Class<?> annotatedClazz) {
        if (annotatedClazz == null) {
            throw new IllegalArgumentException("annotatedClazz should not be null.");
        }
        if (classToStringConf.containsKey(annotatedClazz)){
            return classToStringConf.get(annotatedClazz);
        }
        
        ToString toStringConf = annotatedClazz.getAnnotation(ToString.class);
        classToStringConf.put(annotatedClazz, toStringConf);
        
        return toStringConf;
    }
    
    private void init() {
        Class<?> thisClazz = getObject().getClass();
        ToString toStringConf = getToStringConf(thisClazz);
        
        if (toStringConf == null) {
            excludeFieldNames = ArrayUtils.EMPTY_STRING_ARRAY;
            return;
        }
        super.setExcludeFieldNames(toStringConf.excludes());
        super.setAppendStatics(toStringConf.outputStatics());
        super.setAppendTransients(toStringConf.outputTransients());
        super.setUpToClass(toStringConf.upToClass());
        
    }

    /**
    * @throws IllegalArgumentException
    *             if the Object passed in is <code>null</code>
    */
    private AnnotationToStringBuilder(Object object) {
        super(object, getToStringConf(object.getClass()) == null ? null : getToStringConf(object.getClass()).style().getStyle());
        init();
    }
    
    public static AnnotationToStringBuilder getInstance(final Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object should not be null.");
        }
        AnnotationToStringBuilder stringBuilder = buffers.get(obj);
        if (stringBuilder == null) {
            stringBuilder = new AnnotationToStringBuilder(obj);
        }
        return stringBuilder;
    }
    
    public static String toString(final Object object) {
        return new AnnotationToStringBuilder(object).toString();
    }
    
    @Override
    public AnnotationToStringBuilder setExcludeFieldNames(String... excludeFieldNamesParam) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("All exclude field names settings delegated to annotations.");
    }
    
    @Override
    public void setAppendStatics(boolean appendStatics) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("AppendStatics settings delegated to annotations.");
    }
    
    @Override
    public void setAppendTransients(boolean appendStatics) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("AppendTransients settings delegated to annotations.");
    }
    
    @Override
    public void setUpToClass(Class<?> clazz) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("UpToClass settings delegated to annotations.");
    }
        
    @Override
    public String toString()
    {
        return super.toString();
    }

}
