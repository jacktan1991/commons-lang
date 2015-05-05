package org.apache.commons.lang3.builder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * params come over annotations
 * 
 * <pre>
 * 
 *@ToString(excludes={}, style=ToStringStyleEnum.JSON_STYLE, upToClass=Person.class)
 *class Person {
 *  
 *  public String toString(){
 *      return AnnotationToStringBuilder.toString(this);
 *  }
 *}
 * </pre>
 * 
 * */

@Retention(value=RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ToString {
    String[] excludes() default {};
    ToStringStyleEnum style() default ToStringStyleEnum.NULL_STYLE;
    boolean outputTransients() default false;
    boolean outputStatics() default false;
    Class<?> upToClass() default Object.class;
}

