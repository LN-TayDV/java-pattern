package com.iluwatar.algorithm.theories.on.self.learning.Graph.elementary;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import java.util.Objects;

public class AlgorithmUtils {

    // Hàm tiện ích để cộng hai giá trị số học
    @SuppressWarnings("unchecked")
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
            .filter(Objects::nonNull)
            .orElseThrow(() -> new UnsupportedOperationException("Type not supported"));
    }

    // Hàm tiện ích để cộng hai giá trị số học
    @SuppressWarnings("unchecked")
    public static <W extends Number & Comparable<W>> W add(W a, W b) {

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
}
