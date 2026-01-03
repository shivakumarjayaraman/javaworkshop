package org.spjain.bds.oop;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// This is a static import
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;

// Annotation
@Retention(RUNTIME)
@Target(METHOD)
public @interface MyAnnotation {
}
