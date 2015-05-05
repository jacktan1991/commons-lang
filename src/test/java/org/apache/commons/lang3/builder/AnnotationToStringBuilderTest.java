package org.apache.commons.lang3.builder;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.annotation.ToString;
import org.apache.commons.lang3.builder.annotation.ToStringStyleEnum;
import org.junit.Test;

public class AnnotationToStringBuilderTest {

    @Test(expected=IllegalArgumentException.class)
    public void testObjectShouldNotNull() {
        AnnotationToStringBuilder.getInstance(null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testSetExcludeFieldNames() {
        AnnotationToStringBuilder.getInstance(new Person()).setExcludeFieldNames("id");
    }
    
    @Test
    public void testGetToStringConf() {
        assertNull(AnnotationToStringBuilder.getToStringConf(NoAnnotatedClass.class));
        
        ToString toStringConf = AnnotationToStringBuilder.getToStringConf(Person.class);
        assertNotNull(toStringConf);
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, toStringConf.excludes());
        
        toStringConf = AnnotationToStringBuilder.getToStringConf(Address.class);
        assertArrayEquals(new String[]{"excluded_f"}, toStringConf.excludes());
    }
    
    @Test
    public void testSmoke() {
        Person person = new Person();
        person.id = 104;
        person.name = "Peter";
        
        assertEquals(null, person.toString());
        
        
    }
    
    @ToString(style=ToStringStyleEnum.JSON_STYLE)
    static class Person {
        int id;
        String name;
        Address address;
        
        public String toString() {
            return AnnotationToStringBuilder.toString(this);
        }
    }
    
    @ToString(excludes={"excluded_f"}, style = ToStringStyleEnum.JSON_STYLE)
    static class Address {
        String name;
        String zipcode;
        
        String excluded_f;  
    }
    
    static class NoAnnotatedClass {
        String field1;
    }

}
