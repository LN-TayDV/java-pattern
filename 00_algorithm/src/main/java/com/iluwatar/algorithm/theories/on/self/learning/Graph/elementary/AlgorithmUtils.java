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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.elementary;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Graph;

@SuppressWarnings("unchecked")
public class AlgorithmUtils {

    // Hàm tiện ích để cộng hai giá trị số học
    public static <T, W extends Number & Comparable<W>> W defaultValue(Graph<T, W> graph) {
        return graph.typeWeight()
            .map(targetClassTypeName -> {
                if (targetClassTypeName.equals(Integer.class.getTypeName())) {
                    return (W) Integer.valueOf(0);

                } else if (targetClassTypeName.equals(Double.class.getTypeName())) {
                    return (W) Double.valueOf(0);

                } else if (targetClassTypeName.equals(Long.class.getTypeName())) {
                    return (W) Long.valueOf(0);

                } else if (targetClassTypeName.equals(Float.class.getTypeName())) {
                    return (W) Float.valueOf(0);
                }

                return null;
            })
            .orElseThrow(() -> new UnsupportedOperationException("Type not supported"));
    }

    // Hàm tiện ích để cộng hai giá trị số học
    public static <T, W extends Number & Comparable<W>> W defaultValue(Graph<T, W> graph, Integer value) {
        return graph.typeWeight()
            .map(targetClassTypeName -> {
                if (targetClassTypeName.equals(Integer.class.getTypeName())) {
                    return (W) Integer.valueOf(value);

                } else if (targetClassTypeName.equals(Double.class.getTypeName())) {
                    return (W) Double.valueOf(value);

                } else if (targetClassTypeName.equals(Long.class.getTypeName())) {
                    return (W) Long.valueOf(value);

                } else if (targetClassTypeName.equals(Float.class.getTypeName())) {
                    return (W) Float.valueOf(value);
                }

                return null;
            })
            .orElseThrow(() -> new UnsupportedOperationException("Type not supported"));
    }

    // Hàm tiện ích để cộng hai giá trị số học
    public static <W extends Number & Comparable<W>> W sum(W a, W b) {

        if (a instanceof Integer) {
            return (W) Integer.valueOf(a.intValue() + b.intValue());

        } else if (a instanceof Double) {
            return (W) Double.valueOf(a.doubleValue() + b.doubleValue());

        } else if (a instanceof Long) {
            return (W) Long.valueOf(a.longValue() + b.longValue());

        } else if (a instanceof Float) {
            return (W) Float.valueOf(a.floatValue() + b.floatValue());

        } else {
            throw new UnsupportedOperationException("Type not supported");
        }
    }


    // Phương thức trừ hai giá trị của W
    public static <W extends Number & Comparable<W>> W subtract(W a, W b) {
        if (a instanceof Integer) {
            return (W) Integer.valueOf(a.intValue() - b.intValue());
        } else if (a instanceof Long) {
            return (W) Long.valueOf(a.longValue() - b.longValue());
        } else if (a instanceof Double) {
            return (W) Double.valueOf(a.doubleValue() - b.doubleValue());
        } else if (a instanceof Float) {
            return (W) Float.valueOf(a.floatValue() - b.floatValue());
        }
        throw new UnsupportedOperationException("Type not supported: " + a.getClass().getName());
    }

    public static <T, W extends Number & Comparable<W>> W max(Graph<T, W> graph) {
        return (W) graph.typeWeight()
            .map(targetClassTypeName -> {
                if (targetClassTypeName.equals(Integer.class.getTypeName())) {
                    return Integer.MAX_VALUE;

                } else if (targetClassTypeName.equals(Double.class.getTypeName())) {
                    return Double.MAX_VALUE;

                } else if (targetClassTypeName.equals(Long.class.getTypeName())) {
                    return Long.MAX_VALUE;

                } else if (targetClassTypeName.equals(Float.class.getTypeName())) {
                    return Float.MAX_VALUE;
                }

                return null;
            })
            .orElseThrow(() -> new UnsupportedOperationException("Type not supported"));
    }

    // Phương thức tìm giá trị nhỏ nhất giữa hai giá trị của W
    public static <W extends Number & Comparable<W>> W min(W a, W b) {
        return a.compareTo(b) < 0 ? a : b;
    }
}
