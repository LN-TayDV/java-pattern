package com.iluwatar.algorithm.practices.control.structures;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        getOddNumbers();

    }

    /**
     * 94. Viết chương trình in ra tất cả các số lẻ nhỏ hơn 100 trừ các số 5, 7, 93.
    */
    public static void getOddNumbers () {
        var result = new ArrayList<>();
        for (int i = 0 ; i < 100; i++ ) {
            if(i % 2 == 1) {
                if( i == 5 ||  i == 7 || i == 93) {
                    continue;
                } else {
                    result.add(i);
                }
            }
        }

        result.forEach(i -> LOGGER.info("Sổ lẻ  là : {}", i));
    }
}
