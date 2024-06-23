| Kiến thức                                             | Mô tả                                                                                                                                                               |
|--------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Exception Handling**                                 | Giải thích lợi ích của xử lý ngoại lệ và phân biệt giữa các loại ngoại lệ kiểm tra (checked), ngoại lệ không kiểm tra (unchecked) và lỗi (Errors).             |
| **Checked vs Unchecked Exceptions**                    | - **Checked Exceptions**: Các loại ngoại lệ mà phải được khai báo trong khai báo phương thức hoặc phải được bắt trong khối catch.<br>- **Unchecked Exceptions**: Các loại ngoại lệ không cần phải được khai báo.   |
| **Errors**                                             | Mô tả các loại lỗi trong Java và cách chúng khác biệt so với các ngoại lệ khác.                                                                                      |
| **Try-Catch Blocks**                                   | Tạo và sử dụng các khối try-catch để xử lý ngoại lệ và xác định cách ngoại lệ thay đổi luồng chương trình.                                                          |
| **Throwing Exceptions**                                | Tạo và gọi một phương thức mà ném (throw) một ngoại lệ.                                                                                                              |
| **Exception Handling Best Practices**                   | Những quy tắc và thực tiễn tốt khi xử lý ngoại lệ trong Java, bao gồm cách sử dụng throws và throw, xử lý các ngoại lệ nên được bắt và xử lý ở đâu trong chương trình. |


- Exceptions (Ngoại lệ)
    - Checked Exceptions (Ngoại lệ kiểm tra)
        - IOException
        - SQLException
        - ClassNotFoundException
        - FileNotFoundException
        - ...
    - Unchecked Exceptions (Ngoại lệ không kiểm tra)
        - RuntimeExceptions
            - NullPointerException
            - ArrayIndexOutOfBoundsException
            - ClassCastException
            - IllegalArgumentException
            - ...
        - Errors (Lỗi)
            - VirtualMachineError
                - OutOfMemoryError
                - StackOverflowError
            - AssertionError
            - ...
