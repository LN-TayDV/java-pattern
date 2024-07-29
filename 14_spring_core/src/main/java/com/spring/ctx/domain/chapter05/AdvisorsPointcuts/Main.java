package com.spring.ctx.domain.chapter05.AdvisorsPointcuts;

import java.lang.reflect.Method;

public class Main {

    public interface Pointcut {
        ClassFilter getClassFilter();
        MethodMatcher getMethodMatcher();
    }

    @FunctionalInterface
    public interface ClassFilter {
        boolean matches(Class<?> clazz);
    }

    public interface MethodMatcher {
        boolean matches(Method method, Class<?> targetClass);
        boolean isRuntime();
        boolean matches(Method method, Class<?> targetClass, Object... args);
    }
}
