# Toán tử trong Java

| Loại toán tử        | Các loại và ví dụ                           |
|---------------------|---------------------------------------------|
| **Toán tử Unary**   |                                             |
| Unary Prefix        | ++, --, +, -, !, ~                          |
| Unary Postfix       | ++, --                                      |
| **Toán tử Binary**  |                                             |
| Toán tử Arithmetic  | +, -, *, /, %                                |
| Toán tử Assignment  | =, +=, -=, *=, /=, %=, &=, &#124;=, ^=, <<=, >>=, >>>= |
| Toán tử Relational  | ==, !=, >, <, >=, <=                         |
| Toán tử Logical     | &&, &#124;&#124;                              |
| Toán tử Bitwise     | &, &#124;, ^, <<, >>, >>>                    |
| **Toán tử Ternary** | ? :                                         |
| **Toán tử đặc biệt**| instanceof, (type)                           |


# Giải thích chi tiết về các toán tử ++ và -- trong Java

| Loại toán tử | Sử dụng        | Giải thích                                                                                                                                                 |
|--------------|----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `++`         | `++a` (Prefix) | Tăng giá trị của biến `a` lên 1 đơn vị trước khi sử dụng trong biểu thức.                                                                                 |
|              |                | Ví dụ:                                                                                                                                                      |
|              |                | ```java                                                                                                                                                     |
|              |                | int a = 5;                                                                                                                                                  |
|              |                | int b = ++a; // a được tăng lên trước khi gán vào b                                                                                                           |
|              |                | System.out.println("a = " + a); // Output: a = 6                                                                                                              |
|              |                | System.out.println("b = " + b); // Output: b = 6                                                                                                              |
| `++`         | `a++` (Postfix)| Tăng giá trị của biến `a` lên 1 đơn vị sau khi sử dụng trong biểu thức.                                                                                   |
|              |                | Ví dụ:                                                                                                                                                      |
|              |                | ```java                                                                                                                                                     |
|              |                | int a = 5;                                                                                                                                                  |
|              |                | int b = a++; // a được gán vào b trước khi tăng                                                                                                               |
|              |                | System.out.println("a = " + a); // Output: a = 6                                                                                                              |
|              |                | System.out.println("b = " + b); // Output: b = 5                                                                                                              |
| `--`         | `--a` (Prefix) | Giảm giá trị của biến `a` đi 1 đơn vị trước khi sử dụng trong biểu thức.                                                                                  |
|              |                | Ví dụ:                                                                                                                                                      |
|              |                | ```java                                                                                                                                                     |
|              |                | int a = 5;                                                                                                                                                  |
|              |                | int b = --a; // a được giảm đi trước khi gán vào b                                                                                                            |
|              |                | System.out.println("a = " + a); // Output: a = 4                                                                                                              |
|              |                | System.out.println("b = " + b); // Output: b = 4                                                                                                              |
| `--`         | `a--` (Postfix)| Giảm giá trị của biến `a` đi 1 đơn vị sau khi sử dụng trong biểu thức.                                                                                    |
|              |                | Ví dụ:                                                                                                                                                      |
|              |                | ```java                                                                                                                                                     |
|              |                | int a = 5;                                                                                                                                                  |
|              |                | int b = a--; // a được gán vào b trước khi giảm giá trị                                                                                                      |
|              |                | System.out.println("a = " + a); // Output: a = 4                                                                                                              |
|              |                | System.out.println("b = " + b); // Output: b = 5                                                                                                              |

Trong bảng trên, mỗi loại toán tử `++` và `--` được giải thích cụ thể, kèm theo ví dụ minh họa để bạn có thể dễ dàng hiểu cách mà mỗi toán tử hoạt động trong Java. Bảng này giúp bạn so sánh và áp dụng các toán tử này một cách hiệu quả trong lập trình.
