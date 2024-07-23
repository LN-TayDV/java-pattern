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

        Map<String, Optional<Integer>> targetValueMap = testRepoList.stream()
            .collect(Collectors.toMap(
                e -> "set" + codeName + String.format("%02d", testRepoList.indexOf(e) + 1),
                Optional::of
            ));

        TestImpl entity = new TestImpl();

        Stream.of(entity.getClass().getMethods())
            .filter(mt -> mt.getName().startsWith("set") && targetValueMap.containsKey(mt.getName()))
            .forEach(mt -> {
                targetValueMap.getOrDefault(mt.getName(), Optional.empty()).ifPresent(targetValue -> {
                    try {
                        mt.invoke(entity, targetValue);  // Truyền đối tượng test vào đây
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            });

        System.out.println(entity);

    }
}
