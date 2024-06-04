package com.iluwatar.algorithm.search;

public class LinearSearch {

    /**
     * Hàm tìm kiếm tuyến tính trong mảng.
     * @param array mảng đầu vào
     * @param key giá trị cần tìm
     * @return chỉ số của phần tử cần tìm hoặc -1 nếu không tìm thấy
     */
    public static int linearSearch(int[] array, int key) {
        // Duyệt qua từng phần tử của mảng
        for (int i = 0; i < array.length; i++) {
            // Nếu phần tử tại vị trí i bằng với key
            if (array[i] == key) {
                // Trả về chỉ số i
                return i;
            }
        }
        // Nếu không tìm thấy key, trả về -1
        return -1;
    }

    /**
     * Hàm main để kiểm tra Linear Search
     * @param args không sử dụng
     */
    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int key = 5;

        // Gọi hàm tìm kiếm tuyến tính
        int result = linearSearch(array, key);

        // In kết quả tìm kiếm
        if (result != -1) {
            System.out.println("Phần tử " + key + " được tìm thấy tại chỉ số: " + result);
        } else {
            System.out.println("Phần tử " + key + " không được tìm thấy trong mảng.");
        }
    }
}
