package com.iluwatar.algorithm.optimization;

import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int populationSize = 100; // Kích thước quần thể
        int chromosomeLength = 10; // Độ dài nhiễm sắc thể
        int maxGenerations = 1000; // Số thế hệ tối đa
        double mutationRate = 0.01; // Tỉ lệ đột biến
        double crossoverRate = 0.9; // Tỉ lệ lai ghép

        int[][] population = initializePopulation(populationSize, chromosomeLength);

        for (int generation = 0; generation < maxGenerations; generation++) {
            population = evolvePopulation(population, crossoverRate, mutationRate);
        }

        int[] bestSolution = getBestSolution(population);
        System.out.println("Best Solution: " + Arrays.toString(bestSolution));
        System.out.println("Best Fitness: " + calculateFitness(bestSolution));
    }

    private static int[][] initializePopulation(int populationSize, int chromosomeLength) {
        int[][] population = new int[populationSize][chromosomeLength];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < chromosomeLength; j++) {
                population[i][j] = random.nextInt(2); // Khởi tạo ngẫu nhiên với 0 hoặc 1
            }
        }
        return population;
    }

    private static int[][] evolvePopulation(int[][] population, double crossoverRate, double mutationRate) {
        int populationSize = population.length;
        int chromosomeLength = population[0].length;
        int[][] newPopulation = new int[populationSize][chromosomeLength];

        for (int i = 0; i < populationSize; i++) {
            int[] parent1 = selectParent(population);
            int[] parent2 = selectParent(population);
            int[] child;
            if (random.nextDouble() < crossoverRate) {
                child = crossover(parent1, parent2);
            } else {
                child = parent1.clone();
            }
            mutate(child, mutationRate);
            newPopulation[i] = child;
        }
        return newPopulation;
    }

    private static int[] selectParent(int[][] population) {
        // Chọn lọc theo phương pháp giải đấu
        int tournamentSize = 5;
        int[] best = null;
        for (int i = 0; i < tournamentSize; i++) {
            int index = random.nextInt(population.length);
            int[] individual = population[index];
            if (best == null || calculateFitness(individual) > calculateFitness(best)) {
                best = individual;
            }
        }
        return best;
    }

    private static int[] crossover(int[] parent1, int[] parent2) {
        int crossoverPoint = random.nextInt(parent1.length);
        int[] child = new int[parent1.length];
        for (int i = 0; i < parent1.length; i++) {
            if (i < crossoverPoint) {
                child[i] = parent1[i];
            } else {
                child[i] = parent2[i];
            }
        }
        return child;
    }

    private static void mutate(int[] individual, double mutationRate) {
        for (int i = 0; i < individual.length; i++) {
            if (random.nextDouble() < mutationRate) {
                individual[i] = 1 - individual[i]; // Đảo bit (0 thành 1 và ngược lại)
            }
        }
    }

    private static int[] getBestSolution(int[][] population) {
        int[] best = null;
        for (int[] individual : population) {
            if (best == null || calculateFitness(individual) > calculateFitness(best)) {
                best = individual;
            }
        }
        return best;
    }

    private static int calculateFitness(int[] individual) {
        // Hàm mục tiêu: tổng số bit 1 trong nhiễm sắc thể
        int fitness = 0;
        for (int gene : individual) {
            if (gene == 1) {
                fitness++;
            }
        }
        return fitness;
    }
}

