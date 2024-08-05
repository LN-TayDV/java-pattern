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
package com.iluwatar.algorithm.theories.on.internet.optimization.hill.climbing;

import java.util.Random;

public class HillClimbing {

    private static final Random random = new Random();

    public static void main(String[] args) {
        double startSolution = random.nextDouble(); // Khởi tạo lời giải ban đầu ngẫu nhiên
        double bestSolution = hillClimbing(startSolution, 1000); // Thực hiện thuật toán Hill Climbing
        System.out.println("Best Solution: " + bestSolution);
        System.out.println("Best Fitness: " + objectiveFunction(bestSolution));
    }

    // Thực hiện thuật toán Hill Climbing
    public static double hillClimbing(double startSolution, int maxIterations) {
        double currentSolution = startSolution;
        double currentFitness = objectiveFunction(currentSolution);

        for (int i = 0; i < maxIterations; i++) {
            double neighbor = currentSolution + (random.nextDouble() - 0.5); // Tạo lời giải lân cận
            double neighborFitness = objectiveFunction(neighbor);

            if (neighborFitness < currentFitness) { // Nếu lời giải lân cận tốt hơn
                currentSolution = neighbor; // Cập nhật lời giải hiện tại
                currentFitness = neighborFitness;
            }
        }

        return currentSolution;
    }

    // Hàm mục tiêu để tính toán độ thích nghi của lời giải
    private static double objectiveFunction(double solution) {
        return solution * solution; // Ví dụ: tìm cực tiểu của hàm x^2
    }
}

