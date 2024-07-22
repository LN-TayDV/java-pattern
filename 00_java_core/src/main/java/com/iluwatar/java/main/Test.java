package com.iluwatar.java.main;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {

        List<Integer> testRepoList = List.of(1, 5, 4);

        String codeName = "Code";

        Map<String, Optional<Integer>> nameValue = testRepoList.stream()
            .collect(Collectors.toMap(
                e -> "set" + codeName + String.format("%02d", testRepoList.indexOf(e) + 1),
                Optional::of
            ));

        TestImpl test = new TestImpl();

        Stream.of(test.getClass().getMethods())
            .filter(mt -> mt.getName().startsWith("set"))
            .forEach(mt -> {
                nameValue.getOrDefault(mt.getName(), Optional.empty()).ifPresent(value -> {
                    try {
                        mt.invoke(test, value);  // Truyền đối tượng test vào đây
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            });

        System.out.println(test);

    }
}
