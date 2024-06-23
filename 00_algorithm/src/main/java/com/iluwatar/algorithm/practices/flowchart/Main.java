/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.algorithm.practices.flowchart;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class Main {

    public static final Function<Integer,String> logError = (functionNo) -> {
        return String.format("Test-%s : %s", String.format("%02d", functionNo), "Giá trị của n > 0");
    };

    public static void log (String format, Object... args) {
        LOGGER.info(format, args);
    }
    /**
     *
     * @param args
     */

    public static void print (Object... args) {
        List<Object> doubles = List.of(args);
        for (int i = 0; i < doubles.size(); i++) {
            log("Test-{} = {}", String.format("%02d", i + 1) , doubles.get(i));
        }
    }

    public static void main(String[] args) {
        /*print(
            test01(10),  test02(10),  test03(10), test04(10),  test05(10),
            test06(10),  test07(10),  test08(10), test09(10),  test10(2,4),
            test11(3),  test12(2,4), test13(2,4), test14(2,4),
            test15(3), test16(2,4), test17(2,4), test18(0,0), test19(0,0)
        );*/

        /*print(
            test33(4),  test34(4),  test36(5), test37(5), test38(4),
            test39(4),  test40(1, 4), test41(4)
        );*/

        print(
            test64(4.0, 5.0),
            test67(4.0, 5.0),
            test68(4.0, 5.0),
            test69(4.0, 5.0),
            test70(5.0),
            test71(4.0, 5.0),
            test72(4.0, 5.0),
            test73(4.0, 5.0),
            test74(4.0, 5.0)
        );

       /* test65(4.0,9.0, 4.0).forEach(result -> log(" X = {} ", result));

        test66(-4.0,20.0, -4.0).forEach(result -> log(" X = {} ", result));*/
    }

    /**
     * Tính S(n) = 1 + 2 + 3 + … + n.
     */
    public static Integer test01 (Integer n) {
        Function<Integer, Integer> function = (input) -> {
            int sum = 0;
            for (int i = 1 ; i <= input ; i++) {
                sum += i;
            }
            return sum;
        };
        return function.apply(n);
    }

    public static Double test02 (Integer n) {
        if(n == 1) {
            return Double.parseDouble("1");
        } else {
            return Math.pow(n, 2) + test02(n -1);
        }
    }

    public static Double test03 (Integer n) {
        if (n == 1) {
            return 1.0;
        } else {
            return (1.0 / n) + test03(n - 1);
        }
    }

    public static Double test04 (Integer n) {
        if (n == 1) {
            return 0.5;
        } else {
            return (1.0 / (2 * n)) + test04(n - 1);
        }
    }

    public static Double test05 (Integer n) {
        if(n == 0) {
            throw new IllegalArgumentException(logError.apply(5));
        }else  if (n == 1) {
            return 1.0;
        } else {
            return (1.0 / ((2 * n) + 1 )) + test05(n - 1);
        }
    }

    public static Double test06 (Integer n) {
        if(n == 0) {
            throw new IllegalArgumentException(logError.apply(6));
        } else if (n == 1) {
            return 0.5;
        } else {
            return (1.0 / (n * (n + 1))) + test06(n - 1);
        }
    }

    public static Double test07 (Integer n) {
        if(n == 0) {
            return 0.5;
        }  else {
            return ((double) n / ((double) n + 1)) + test07(n - 1);
        }
    }

    public static Double test08 (Integer n) {
        if(n == 0) {
            return 0.5;
        }  else {
            return ((double) (2*n + 1) / (double) (2*n + 2)) + test08(n - 1);
        }
    }

    public static Double test09 (Integer n) {
        if(n == 0) {
            return 1.0;
        }  else {
            return n *  test09(n - 1);
        }
    }

    public static Double test10 (Integer x, Integer n) {
        return Math.pow((double) x, (double) n);
    }

    public static Double test11 (Integer n) {
        if (n == 1) {
            return 1.0;
        } else {
            return test11(n - 1) + factorial(n);
        }
    }

    public static Double test12 (Integer x, Integer n) {
        if (n == 1) {
            return 1.0;
        } else {
            return test12(x, n - 1) + Math.pow((double) x, (double) n);
        }
    }

    public static Double test13 (Integer x, Integer n) {
        if (n == 0) {
            return 0.0;
        } else {
            return  test13(n - 1, x) + Math.pow(x, 2 * n) ;
        }
    }

    public static Double test14 (Integer x, Integer n) {
        if (n == 0) {
            return Math.pow(x, 1.0);
        } else {
            return  test14(n - 1, x) + Math.pow(x, 2 * (n + 1)) ;
        }
    }

    public static Double test15 (Integer n) {
        if (n == 0) {
            return 1.0;
        } else {
            return  test15(n - 1) + (1 / sum(n)) ;
        }
    }

    public static Double test16 (Integer x, Integer n) {
        if (n == 0) {
            return 0.0;
        } else {
            return  test16(x, n - 1) + (Math.pow(x, n) /  sum(n)) ;
        }
    }

    public static Double test17 (Integer x, Integer n) {
        if (n == 0) {
            return 0.0;
        } else {
            return  test17(x, n - 1) + (Math.pow(x, n) /  factorial(n)) ;
        }
    }

    public static Double test18 (Integer x, Integer n) {
        if (x == 0) {
            return 0.0;  // Nếu x = 0, trả về 0
        }
        if (n == 0) {
            return 0.0;  // Base case: nếu n = 0, trả về 0
        } else {
            return test18(x, n -1 ) + Math.pow(x, n) / factorial(n) ;
        }
    }

    public static Double test19 (Integer x, Integer n) {
        if (x == 0) {
            return 0.0;  // Nếu x = 0, trả về 0
        }
        if (n == 0) {
            return 1.0;
        } else {
            return  test19(x, n - 1) + (Math.pow(x, 2 * n + 1) / factorial(2 * n + 1)) ;
        }
    }

    public static Double test33 (Integer n) {
        if (n == 0) {
            return 1.0;
        } else {
            return Math.sqrt(2 + test33(n - 1));
        }
    }

    /**
     * 34. Tính S(n) = √𝑛 + √𝑛 − 1 + √𝑛 − 2 + ⋯ √2 + √1 có n dấu căn.
     */

    public static double test34 (int n) {
        if (n == 1) {
            return Math.sqrt(1);  // Trường hợp cơ sở: √1 = 1
        } else {
            return Math.sqrt(n + test34(n - 1));
        }
    }

    public static double test36 (int n) {
        if (n == 1) {
            return Math.sqrt(1);  // Trường hợp cơ sở: √1 = 1
        } else {
            return Math.sqrt(factorial(n) + test36(n - 1));
        }
    }

    public static double test37 (int n) {
        if (n == 1) {
            return Math.sqrt(1);  // Trường hợp cơ sở: √1 = 1
        } else {
            return Math.pow(n + test37(n - 1), (double) 1 / n);
        }
    }

    public static double test38 (int n) {
        if (n == 1) {
            return Math.sqrt(1);  // Trường hợp cơ sở: √1 = 1
        } else {
            return Math.pow(n + test38(n - 1), (double) 1 / (n + 1));
        }
    }

    public static double test39 (int n) {
        if (n == 1) {
            return Math.sqrt(1);  // Trường hợp cơ sở: √1 = 1
        } else {
            return Math.pow(factorial(n) + test39(n - 1), (double) 1 / (n + 1));
        }
    }

    public static double test40 (int x, int n) {
        if (n == 1) {
            return Math.sqrt(1);  // Trường hợp cơ sở: √1 = 1
        } else {
            return Math.pow(Math.pow(x, n) + test40(x, n - 1), 0.5);
        }
    }

    // Hàm đệ quy tính S(n)
    public static double test41(int n) {
        if (n == 0) {
            return 1.0; // Trường hợp cơ sở: S(0) = 1
        } else {
            return 1.0 / (1.0 + test41(n - 1)); // Đệ quy: tính 1 / (1 + S(n-1))
        }
    }

    /**
     * 64 -> 74
     */
    public static double test64(double a, double b ) {
        // ax + b = 0
        return (-b / a);
    }

    public static List<Double> test65 (double a, double b, double c) {
        // Giải phương trình bậc hai:

        double denta = Math.pow(b, 2) - (4 * a * c);

        if(denta > 0) {
            double x1 = (-b + Math.sqrt(denta)) / (2 * a);

            double x2 = (-b - Math.sqrt(denta)) / (2 * a);

            return List.of(x1, x2);

        } else if (denta == 0) {

            double x = (-b) / (2 * a);

            return List.of(x);

        } else {

            return Collections.emptyList();
        }
    }

    public static List<Double> test66 (double a, double b, double c) {
        // Giải phương trình bậc bốn: a^2 = t ( t > = 0)

        double denta = Math.pow(b, 2) - (4 * a * c);

        if(denta > 0.0) {
            double t1 = (-b + Math.sqrt(denta)) / (2 * a);

            double t2 = (-b - Math.sqrt(denta)) / (2 * a);

            var result = new ArrayList<Double>();

            if(t1 >= 0.0) {
                result.add(Math.sqrt(t1));
                result.add(-Math.sqrt(t1));
            }

            if(t2 >= 0.0) {
                result.add(Math.sqrt(t2));
                result.add(-Math.sqrt(t2));
            }

            return result;

        } else if (denta == 0.0) {

            double t = (-b) / (2 * a);

            var result = new ArrayList<Double>();

            if(t >= 0.0) {
                result.add(Math.sqrt(t));
                result.add(-Math.sqrt(t));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    public static double test67 (double x, double n) {
        if (n == 1.0) {
            return x;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test67(x, n - 1) + Math.pow(-1.0, n + 1) * Math.pow(x, n);
        }
    }

    public static double test68 (double x, double n) {
        if (n == 1.0) {
            return (-1.0) * Math.pow(x, 2);
        } else {
            return  test68(x, n - 1) + Math.pow(-1.0, n) * Math.pow(x, 2 * n);
        }
    }

    public static double test69 (double x, double n) {
        if (n == 1.0) {
            return x;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test69(x, n - 1) + Math.pow(-1.0, n) * Math.pow(x, (2 * n) - 1.0);
        }
    }

    public static double test70 (double n) {
        if (n == 1.0) {
            return 1.0;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test70(n - 1) + Math.pow(-1.0, n + 1) * (1 / sum((int) n));
        }
    }

    public static double test71 (double x, double n) {
        if (n == 1.0) {
            return -x;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test71(x, n - 1) + Math.pow(-1.0, n) * (Math.pow(x, n) / sum((int) n));
        }
    }

    public static double test72 (double x, double n) {
        if (n == 1.0) {
            return -x;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test72(x, n - 1) + Math.pow(-1.0, n) * (Math.pow(x, n) / factorial((int) n ));
        }
    }

    public static double test73 (double x, double n) {
        if (n == 1.0) {
            return -1.0;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test73(x, n - 1) + Math.pow(-1.0, n + 1) * (Math.pow(x, 2 * n) / factorial((int) (2 * n)));
        }
    }

    public static double test74 (double x, double n) {
        if (n == 1.0) {
            return 1.0;  // Trường hợp cơ sở: √1 = 1
        } else {
            return  test74(x, n - 1) +
                Math.pow(-1.0, n + 1) * (Math.pow(x, (2 * n) + 1) / factorial((int) ((2 * n) + 1)));
        }
    }


    public static double sum (int n) {
        if (n == 1) {
            return 1.0;
        } else {
            return sum(n - 1) + n;
        }
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }



}
