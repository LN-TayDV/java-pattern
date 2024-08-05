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
package com.iluwatar.algorithm.theories.on.internet.optimization.simulated.annealing;

import java.util.Random;

public class SimulatedAnnealing {
    // Khai báo biến random để tạo số ngẫu nhiên
    private static final Random random = new Random();

    // Phương pháp chính để thực hiện Simulated Annealing
    public static double simulatedAnnealing(double initialTemp, double coolingRate, int numIterations) {
        // Khởi tạo nhiệt độ ban đầu
        double temperature = initialTemp;

        // Khởi tạo lời giải hiện tại ngẫu nhiên
        double currentSolution = random.nextDouble();

        // Đánh giá lời giải hiện tại
        double currentEnergy = objectiveFunction(currentSolution);

        // Khởi tạo lời giải tốt nhất
        double bestSolution = currentSolution;
        double bestEnergy = currentEnergy;

        // Vòng lặp cho đến khi số lần lặp đạt tới numIterations
        for (int i = 0; i < numIterations; i++) {
            // Tạo lời giải mới trong vùng lân cận của lời giải hiện tại
            double newSolution = currentSolution + (random.nextDouble() - 0.5);

            // Đánh giá lời giải mới
            double newEnergy = objectiveFunction(newSolution);

            // Tính xác suất chấp nhận lời giải mới
            if (acceptanceProbability(currentEnergy, newEnergy, temperature) > random.nextDouble()) {
                // Cập nhật lời giải hiện tại
                currentSolution = newSolution;
                currentEnergy = newEnergy;
            }

            // Cập nhật lời giải tốt nhất nếu lời giải mới tốt hơn
            if (currentEnergy < bestEnergy) {
                bestSolution = currentSolution;
                bestEnergy = currentEnergy;
            }

            // Làm lạnh (giảm nhiệt độ)
            temperature *= coolingRate;
        }

        // Trả về lời giải tốt nhất tìm được
        return bestSolution;
    }

    // Hàm mục tiêu để đánh giá chất lượng của lời giải
    private static double objectiveFunction(double solution) {
        // Ví dụ hàm mục tiêu: tìm cực tiểu của hàm x^2
        return solution * solution;
    }

    // Hàm tính xác suất chấp nhận lời giải mới
    private static double acceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        // Nếu lời giải mới tốt hơn, chấp nhận ngay
        if (newEnergy < currentEnergy) {
            return 1.0;
        }
        // Nếu lời giải mới không tốt hơn, chấp nhận theo xác suất e^(-(newEnergy - currentEnergy) / temperature)
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

    public static void main(String[] args) {
        // Thực thi thuật toán Simulated Annealing
        double initialTemp = 1000; // Nhiệt độ ban đầu
        double coolingRate = 0.95; // Tốc độ làm lạnh
        int numIterations = 10000; // Số lần lặp

        // Tìm lời giải tốt nhất
        double bestSolution = simulatedAnnealing(initialTemp, coolingRate, numIterations);
        System.out.println("Best Solution: " + bestSolution);
    }
}
