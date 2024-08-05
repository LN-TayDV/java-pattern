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
package com.iluwatar.algorithm.theories.on.internet.encryption;

import java.util.Arrays;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;

public class MachineLearning {

    public static void main(String[] args) throws Exception {
        // Dữ liệu huấn luyện
        Instances data = getData();

        // Linear Regression
        System.out.println("Linear Regression:");
        LinearRegression linearRegression = new LinearRegression();
        linearRegression.buildClassifier(data);
        System.out.println(linearRegression);

        // K-Nearest Neighbors (KNN)
        System.out.println("K-Nearest Neighbors (KNN):");
        IBk knn = new IBk();
        knn.buildClassifier(data);
        System.out.println(knn);

        // Support Vector Machines (SVM)
        System.out.println("Support Vector Machines (SVM):");
        SMO svm = new SMO();
        svm.buildClassifier(data);
        System.out.println(svm);

        // Neural Networks
        System.out.println("Neural Networks:");
        MultilayerPerceptron neuralNetwork = new MultilayerPerceptron();
        neuralNetwork.buildClassifier(data);
        System.out.println(neuralNetwork);

        // Decision Trees
        System.out.println("\nDecision Trees:");
        J48 decisionTree = new J48();
        decisionTree.buildClassifier(data);
        System.out.println(decisionTree);
    }

    // Tạo dữ liệu huấn luyện đơn giản
    public static Instances getData() {
        // Tạo các thuộc tính
        Attribute outlook = new Attribute("outlook", Arrays.asList("sunny", "overcast", "rainy"));
        Attribute temperature = new Attribute("temperature");
        Attribute humidity = new Attribute("humidity");
        Attribute windy = new Attribute("windy", Arrays.asList("true", "false"));
        Attribute play = new Attribute("play", Arrays.asList("yes", "no"));

        // Tạo danh sách thuộc tính
        FastVector attributes = new FastVector();
        attributes.addElement(outlook);
        attributes.addElement(temperature);
        attributes.addElement(humidity);
        attributes.addElement(windy);
        attributes.addElement(play);

        // Tạo dataset với danh sách thuộc tính
        Instances data = new Instances("Weather", attributes, 0);

        // Định nghĩa dữ liệu cho mỗi mẫu
        double[] data1 = {0, 85, 85, 0, 0}; // [sunny, 85, 85, false, no]
        double[] data2 = {0, 80, 90, 1, 0}; // [sunny, 80, 90, true, no]
        double[] data3 = {1, 83, 86, 0, 1}; // [overcast, 83, 86, false, yes]
        double[] data4 = {2, 70, 96, 0, 1}; // [rainy, 70, 96, false, yes]
        double[] data5 = {2, 68, 80, 0, 1}; // [rainy, 68, 80, false, yes]
        double[] data6 = {2, 65, 70, 1, 0}; // [rainy, 65, 70, true, no]
        double[] data7 = {1, 64, 65, 1, 1}; // [overcast, 64, 65, true, yes]
        double[] data8 = {0, 72, 95, 0, 0}; // [sunny, 72, 95, false, no]
        double[] data9 = {0, 69, 70, 0, 1}; // [sunny, 69, 70, false, yes]
        double[] data10 = {2, 75, 80, 0, 1}; // [rainy, 75, 80, false, yes]

        // Thêm mỗi mẫu vào dataset
        data.add(new DenseInstance(1.0, data1));
        data.add(new DenseInstance(1.0, data2));
        data.add(new DenseInstance(1.0, data3));
        data.add(new DenseInstance(1.0, data4));
        data.add(new DenseInstance(1.0, data5));
        data.add(new DenseInstance(1.0, data6));
        data.add(new DenseInstance(1.0, data7));
        data.add(new DenseInstance(1.0, data8));
        data.add(new DenseInstance(1.0, data9));
        data.add(new DenseInstance(1.0, data10));

        // Đặt thuộc tính dự đoán (play) là thuộc tính cuối cùng
        data.setClassIndex(data.numAttributes() - 1);

        return data;
    }
}

