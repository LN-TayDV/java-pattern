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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * T : top
 * FC  : f-cost
 * @param <T>
 * @param <FC>
 */
public class Path<T, FC extends Number & Comparable<FC>> implements Iterable<Path<T, FC>> {

    // Đỉnh bắt đầu của đường đi
    private Vertex<T> fromVertex;

    // Chi phí ước tính từ đỉnh bắt đầu đến đỉnh kết thúc
    private FC fCost;

    // Đỉnh tiếp theo trong đường đi
    private Path<T, FC> toVertex;

    // Constructor không tham số
    public Path() {}

    // Constructor có tham số
    public Path(Vertex<T> fromVertex, FC fCost, Path<T, FC> toVertex) {
        this.fromVertex = fromVertex;
        this.fCost = fCost;
        this.toVertex = toVertex;
    }

    // Getter và Setter cho fromVertex
    public Vertex<T> getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Vertex<T> fromVertex) {
        this.fromVertex = fromVertex;
    }

    // Getter và Setter cho fCost
    public FC getFCost() {
        return fCost;
    }

    public void setFCost(FC fCost) {
        this.fCost = fCost;
    }

    // Getter và Setter cho toVertex
    public Path<T, FC> getToVertex() {
        return toVertex;
    }

    public void setToVertex(Path<T, FC> toVertex) {
        this.toVertex = toVertex;
    }

    // Phương thức add để thêm phần tử vào cuối path
    public void add(Vertex<T> fromVertex, FC fCost) {
        Path<T, FC> current = this;

        // Traverse to the end of the path
        while (current.toVertex != null) {
            current = current.toVertex;
        }

        // Append the new Path element to the end
        current.toVertex = new Path<>(fromVertex, fCost, null);
    }

    // Phương thức iterator để duyệt qua đường đi
    @Override
    public Iterator<Path<T, FC>> iterator() {
        return new PathIterator<>(this);
    }

    public void removeLast() {

        // Nếu đường đi không có đỉnh nào hoặc chỉ có một đỉnh, thiết lập lại đường đi thành null
        if (toVertex == null) {
            fromVertex = null;
            fCost = null;
            // Không cần thiết phải đặt `toVertex` thành null vì nó đã là null
        } else {
            // Tìm phần tử trước đỉnh cuối cùng
            Path<T, FC> current = this;
            Path<T, FC> previous = null;

            // Duyệt để tìm phần tử trước phần tử cuối cùng
            while (current.toVertex != null) {
                previous = current;
                current = current.toVertex;
            }

            // Nếu phần tử trước đỉnh cuối cùng không phải là null
            if (previous != null) {
                // Loại bỏ đỉnh cuối cùng
                previous.toVertex = null;
            }
        }
    }

    // Lớp PathIterator để thực hiện việc duyệt qua đường đi
    private static class PathIterator<T, FC extends Number & Comparable<FC>> implements Iterator<Path<T, FC>> {

        private Path<T, FC> currentPath;

        public PathIterator(Path<T, FC> startPath) {
            this.currentPath = startPath;
        }

        @Override
        public boolean hasNext() {
            return currentPath != null;
        }

        @Override
        public Path<T, FC> next() {
            if (currentPath == null) {
                throw new NoSuchElementException();
            }
            Path<T, FC> temp = currentPath;
            currentPath = currentPath.getToVertex();
            return temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(this.fromVertex != null) {
            sb.append(this.fromVertex.getTop()); // Thêm đỉnh bắt đầu vào chuỗi
        }

        if(this.fCost == null) {
            if (this.toVertex != null) {
                sb.append("--> "); // Thêm chi phí vào chuỗi
                sb.append(this.toVertex); // Đệ quy gọi phương thức toString của đỉnh tiếp theo
            } else {
                sb.append("--> ").append("[END]"); // Nếu là đỉnh cuối cùng, kết thúc chuỗi
            }
            return sb.toString(); // Trả về chuỗi kết quả

        }else {
            if (this.toVertex != null) {
                sb.append(" --").append("(").append("f-cost : ").append(this.fCost).append(")").append("-- "); // Thêm chi phí vào chuỗi
                sb.append(this.toVertex); // Đệ quy gọi phương thức toString của đỉnh tiếp theo
            } else {
                sb.append(" --").append("(").append("f-cost : ").append(this.fCost).append(")").append("-- [END]"); // Nếu là đỉnh cuối cùng, kết thúc chuỗi
            }

            return sb.toString(); // Trả về chuỗi kết quả
        }
    }
}
