package com.iluwatar.algorithm.optimization;

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

