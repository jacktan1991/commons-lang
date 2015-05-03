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
    public void testGetAnnotatedExclusion() {
        assertArrayEquals(new String[]{"excluded_f"}, AnnotationToStringBuilder.getAnnotatedExclusion(Address.class));
        assertArrayEquals(AnnotationToStringBuilder.EMPTY_EXCLUDE_FIELD_NAMES, AnnotationToStringBuilder.getAnnotatedExclusion(Person.class));
        assertArrayEquals(AnnotationToStringBuilder.EMPTY_EXCLUDE_FIELD_NAMES, AnnotationToStringBuilder.getAnnotatedExclusion(NoAnnotatedClass.class));
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
