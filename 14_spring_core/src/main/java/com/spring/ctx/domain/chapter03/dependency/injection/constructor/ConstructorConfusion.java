package com.spring.ctx.domain.chapter03.dependency.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ConstructorConfusion {


    private final String someValue;

    public ConstructorConfusion(String someValue) {
        System.out.println("ConstructorConfusion(String) called");
        this.someValue = someValue;
    }

    /**
     * Because the second constructor is annotated with @Autowired
     * This tells Spring to use this constructor to instantiate this bean. Without that
     * annotation, Spring cannot decide on its own which constructor to use, and running this class results in the
     * following exception being thrown.
     */
    @Autowired
    public ConstructorConfusion(@Value("90") int someValue) {
        System.out.println("ConstructorConfusion(int) called");

        this.someValue = "Number: " + someValue;
    }

    public
    static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext();

        ctx.register(ConstructorConfusion.class);

        ctx.refresh();

        var cc = ctx.getBean(ConstructorConfusion.class);

        System.out.println("Does this work? " + cc);
    }

    public String toString() {
        return someValue;
    }
}
