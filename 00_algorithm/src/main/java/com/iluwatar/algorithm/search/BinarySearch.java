package com.iluwatar.algorithm.search;

public class BinarySearch {

    /**
     * Hàm tìm kiếm nhị phân trong mảng đã được sắp xếp.
     * @param array mảng đầu vào đã sắp xếp
     * @param key giá trị cần tìm
     * @return chỉ số của phần tử cần tìm hoặc -1 nếu không tìm thấy
     */
    public static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // Vòng lặp tìm kiếm
        while (left <= right) {
            // Tính chỉ số giữa mảng
            int mid = left + (right - left) / 2;

            // Nếu phần tử giữa bằng key, trả về chỉ số giữa
            if (array[mid] == key) {
                return mid;
            }

            // Nếu phần tử giữa nhỏ hơn key, bỏ qua nửa trái
            if (array[mid] < key) {
                left = mid + 1;
            }
            // Nếu phần tử giữa lớn hơn key, bỏ qua nửa phải
            else {
                right = mid - 1;
            }
        }
        // Nếu không tìm thấy key, trả về -1
        return -1;
    }

    /**
     * Hàm main để kiểm tra Binary Search
     * @param args không sử dụng
     */
    public static void main(String[] args) {
        int[] array = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        int key = 5;

        // Gọi hàm tìm kiếm nhị phân
        int result = binarySearch(array, key);

        // In kết quả tìm kiếm
        if (result != -1) {
            System.out.println("Phần tử " + key + " được tìm thấy tại chỉ số: " + result);
        } else {
            System.out.println("Phần tử " + key + " không được tìm thấy trong mảng.");
        }
    }
}
