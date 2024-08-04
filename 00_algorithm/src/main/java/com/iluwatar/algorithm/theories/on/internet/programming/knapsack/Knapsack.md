### README: Knapsack Algorithm

## 1. Ý nghĩa của Thuật toán

Thuật toán Knapsack (bài toán cái túi) là một trong những bài toán cổ điển của lý thuyết tối ưu hóa và thường được sử dụng để giải quyết các vấn đề liên quan đến việc chọn lựa tối ưu. Mục tiêu của bài toán này là chọn các vật phẩm có giá trị nhất để đưa vào một cái túi có giới hạn về trọng lượng, sao cho tổng giá trị của các vật phẩm là lớn nhất.

### Bài toán Knapsack cụ thể:
- **Đầu vào**:
    - Một danh sách các vật phẩm, mỗi vật phẩm có giá trị và trọng lượng tương ứng.
    - Giới hạn trọng lượng của cái túi (knapsack).
- **Đầu ra**:
    - Tổng giá trị lớn nhất có thể đạt được mà không vượt quá giới hạn trọng lượng của cái túi.

## 2. Cách Ghi Nhớ Logic Thuật Toán

### Quy trình của thuật toán:

1. **Khởi tạo mảng `dp`**:
    - Tạo một mảng 2D `dp` với kích thước `(n+1) x (W+1)`, trong đó `n` là số lượng vật phẩm và `W` là trọng lượng tối đa của cái túi.
    - `dp[i][w]` sẽ lưu trữ giá trị tối đa có thể đạt được khi xem xét `i` vật phẩm đầu tiên và với trọng lượng tối đa là `w`.

2. **Duyệt qua các vật phẩm**:
    - Sử dụng vòng lặp lồng nhau để duyệt qua tất cả các vật phẩm và tất cả các mức trọng lượng từ 0 đến `W`.
    - Nếu không có vật phẩm nào hoặc trọng lượng tối đa là 0 (`i == 0` hoặc `w == 0`), giá trị tối đa là 0 (`dp[i][w] = 0`).

3. **Xét từng vật phẩm**:
    - Nếu trọng lượng của vật phẩm hiện tại `weights[i-1]` nhỏ hơn hoặc bằng trọng lượng tối đa hiện tại `w`, tính toán giá trị tối đa có thể đạt được:
        - Gồm vật phẩm hiện tại: `values[i-1] + dp[i-1][w-weights[i-1]]`
        - Không gồm vật phẩm hiện tại: `dp[i-1][w]`
        - Chọn giá trị lớn hơn giữa hai lựa chọn trên.

4. **Kết quả cuối cùng**:
    - Kết quả nằm ở `dp[n][W]`, đại diện cho giá trị tối đa có thể đạt được với `n` vật phẩm và trọng lượng tối đa `W`.

### Mã nguồn:
```java
package com.iluwatar.algorithm.dynamic.programming;

public class Knapsack {
    // Phương thức để tính giá trị tối đa mà knapsack có thể chứa
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        // Tạo một mảng 2D để lưu trữ giá trị tối đa có thể đạt được
        int[][] dp = new int[n + 1][W + 1];

        // Tính toán giá trị tối đa có thể đạt được cho mỗi trọng lượng và số lượng mục
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (weights[i - 1] <= w)
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        // Trả về giá trị tối đa có thể đạt được
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120}; // Giá trị của các mục
        int[] weights = {10, 20, 30}; // Trọng lượng của các mục
        int W = 50; // Trọng lượng tối đa của knapsack
        int n = values.length; // Số lượng mục

        System.out.println("Giá trị tối đa mà knapsack có thể chứa là: " + knapsack(W, weights, values, n));
    }
}
```

## 3. Ứng Dụng trong Thực Tế

Thuật toán Knapsack có nhiều ứng dụng thực tế trong các lĩnh vực khác nhau:

1. **Quản lý tài nguyên**:
    - Quyết định phân bổ tài nguyên hữu hạn (như ngân sách hoặc thời gian) để tối đa hóa lợi ích thu được.

2. **Lập lịch công việc**:
    - Xác định các nhiệm vụ nào nên được hoàn thành trong một khoảng thời gian nhất định để tối đa hóa hiệu quả công việc.

3. **Lựa chọn dự án**:
    - Lựa chọn các dự án để đầu tư nhằm tối đa hóa lợi nhuận trong điều kiện giới hạn về nguồn lực (như vốn đầu tư hoặc nhân lực).

4. **Quản lý danh mục đầu tư**:
    - Chọn các khoản đầu tư để tối đa hóa lợi nhuận trong khi tuân thủ các giới hạn về ngân sách.

5. **Tối ưu hóa lô hàng**:
    - Tối ưu hóa việc sắp xếp hàng hóa vào các container hoặc xe tải để tối đa hóa giá trị vận chuyển trong điều kiện giới hạn trọng lượng hoặc không gian.

6. **Kỹ thuật phần mềm**:
    - Sử dụng để tối ưu hóa các quyết định phân bổ tài nguyên trong các hệ thống phần mềm.

### Tóm Lại

Thuật toán Knapsack là một công cụ mạnh mẽ để giải quyết các vấn đề tối ưu hóa với các giới hạn tài nguyên. Bằng cách hiểu rõ logic và quy trình của thuật toán, bạn có thể áp dụng nó hiệu quả vào các tình huống thực tế khác nhau để tối đa hóa giá trị hoặc lợi ích đạt được.