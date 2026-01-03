package org.spjain.bds.oop;

import java.lang.annotation.Annotation;

public class AnnotationDemo {

    @MyAnnotation
    public void someMethod() {
        System.out.println("This method is annotated with @MyAnnotation");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationDemo demo = new AnnotationDemo();
        //demo.someMethod();
        // print out the annotations present on someMethod
        Annotation[] annotations = demo.getClass().getMethod("someMethod").getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("Found annotation: " + annotation);
        }
        // check if the method is annotated with MyAnnotation
        boolean isAnnotated = demo.getClass().getMethod("someMethod").isAnnotationPresent(MyAnnotation.class);
        System.out.println("Is someMethod annotated with @MyAnnotation? " + isAnnotated);

    }
}
