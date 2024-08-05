package com.iluwatar.java.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.lang.ref.Reference;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static char ch;
    static float f;
    static boolean bool;

    @RequiredArgsConstructor
    public enum Test03 {
        USE(0),
        NOT_USE(1),
        NOT_USE_2(2),
        NOT_USE_3(3),
        NOT_USE_4(4),
        NOT_USE_5(5)
        ;
        public final int value;

        public static Test03 of(int value) {
            return Stream.of(Test03.values()).filter(e -> e.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + value));
        }
    }

    @RequiredArgsConstructor
    public enum Test01 {

        LEFT(0, new AtomicReference<>(Test03.of(0))),

        RIGHT(1, new AtomicReference<>(Test03.of(0))),
        ;

        public final int value;
        public final AtomicReference<Test03> test03;

        public static Test01 of(int value, int test02Value) {
            var e = Test01.valueOf(Test01.class, value == 0 ? "LEFT" : "RIGHT");
            e.setValue(test02Value);
            return e;
        }

        public Test03 getValue() {
            return this.test03.get();
        }

        public void setValue (int value) {
            this.test03.set(Test03.of(value));
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Test02 {

        @Setter
        private int valueTest;
    }

    public static void main(String[] args) {
        var test = Test01.of(2, 3);
        System.out.println(test);
        System.out.println(test.getValue());
    }

    public  void main(String args) {
        System.out.print(f);
        System.out.print(" ");
        System.out.print(ch);
        System.out.print(" ");
        System.out.print(bool);

        List<Integer> testRepoList = List.of(1, 5, 4);

        String codeName = "Code";

        Map<String, Optional<Integer>> targetValueMap = testRepoList.stream()
            .collect(Collectors.toMap(
                e -> "set" + codeName + String.format("%02d", testRepoList.indexOf(e) + 1),
                Optional::of
            ));

        TestImpl entity = new TestImpl();

        Stream.of(entity.getClass().getMethods())
            .filter(mt -> mt.getName().startsWith("set") && targetValueMap.containsKey(mt.getName()))
            .forEach(mt -> {
                targetValueMap.getOrDefault(mt.getName(), Optional.empty())
                    .ifPresent(targetValue -> {
                        try {
                            mt.invoke(entity, targetValue);  // Truyền đối tượng test vào đây
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
            });

        System.out.println(entity);

        Long l = Long.valueOf(200);

    }
}
