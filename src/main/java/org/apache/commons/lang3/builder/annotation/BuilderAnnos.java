package org.apache.commons.lang3.builder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BuilderAnnos {
    String value() default "DEFAULT_STYLE";
    String[] excludes() default {};
}
