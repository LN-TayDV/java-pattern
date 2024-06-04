package com.iluwatar.algorithm.optimization;

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
