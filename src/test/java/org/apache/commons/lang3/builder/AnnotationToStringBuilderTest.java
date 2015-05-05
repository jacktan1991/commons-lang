package org.apache.commons.lang3.builder;

import static org.junit.Assert.*;

import org.apache.commons.lang3.builder.annotation.ExcludeFields;
import org.junit.Test;

public class AnnotationToStringBuilderTest {

    @Test(expected=UnsupportedOperationException.class)
    public void testSetExcludeFieldNames() {
        new AnnotationToStringBuilder(null).setExcludeFieldNames("");
    }
    
    @Test
    public void testGetExcludes() {
        assertNull(AnnotationToStringBuilder.getExcludes(NoAnnotatedClass.class));
        
        ExcludeFields anno_excludes = AnnotationToStringBuilder.getExcludes(Person.class);
        assertNotNull(anno_excludes);
        assertArrayEquals(AnnotationToStringBuilder.EMPTY_EXCLUDE_FIELD_NAMES, anno_excludes.value());
        
        anno_excludes = AnnotationToStringBuilder.getExcludes(Address.class);
        assertArrayEquals(new String[]{"excluded_f"}, anno_excludes.value());
    }
    
    @ExcludeFields
    static class Person {
        int id;
        String name;
        Address address;
    }
    
    @ExcludeFields({"excluded_f"})
    static class Address {
        String name;
        String zipcode;
        
        String excluded_f;  
    }
    
    static class NoAnnotatedClass {
        String field1;
    }

}
