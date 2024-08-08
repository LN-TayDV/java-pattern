package com.iluwatar.algorithm.theories.on.learning.Sorting;

public class Bubble {

    // Phương thức sắp xếp nổi bọt với mảng các đối tượng Comparable
    public static void sort(Comparable[] arr) {
        int n = arr.length; // Lấy số lượng phần tử trong mảng

        // Lặp qua tất cả các phần tử trong mảng
        for (int i = 0; i < n - 1; i++) {
            // Biến để kiểm tra xem có thực hiện hoán đổi nào không
            boolean swapped = false;

            // Lặp qua mảng, bỏ qua các phần tử đã sắp xếp
            for (int j = 0; j < n - i - 1; j++) {
                // So sánh phần tử hiện tại với phần tử kế tiếp
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // Hoán đổi nếu phần tử hiện tại lớn hơn phần tử kế tiếp
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Đặt swapped thành true vì đã thực hiện hoán đổi
                    swapped = true;
                }
            }

            // Nếu không có hoán đổi nào trong vòng lặp bên trong, mảng đã được sắp xếp
            if (!swapped) {
                break;
            }
        }
    }

    // Phương thức main để kiểm tra thuật toán sắp xếp
    public static void main(String[] args) {
        // Mảng các đối tượng Integer cần sắp xếp
        Integer[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Mảng trước khi sắp xếp:");
        printArray(arr);

        sort(arr); // Gọi phương thức sắp xếp nổi bọt

        System.out.println("Mảng sau khi sắp xếp:");
        printArray(arr);
    }

    // Phương thức in mảng
    public static void printArray(Comparable[] arr) {
        for (Comparable num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
