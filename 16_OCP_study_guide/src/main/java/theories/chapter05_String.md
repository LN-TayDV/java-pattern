Dưới đây là bảng thống kê các **phương thức quan trọng của lớp `String` trong Java** mà bạn cần **ghi nhớ để làm bài tập và thi OCP Java SE 8**. Bảng này chia ra từng phương thức, cách sử dụng, điểm cần chú ý và lỗi thường gặp để bạn dễ hệ thống:

---

### ✅ **BẢNG TỔNG HỢP PHƯƠNG THỨC `String` CẦN GHI NHỚ**

| Phương thức | Mô tả | Ví dụ | Lưu ý / Bẫy thường gặp |
|------------|-------|-------|------------------------|
| `length()` | Trả về số lượng ký tự trong chuỗi | `"animals".length()` → `7` | Đếm từ 1 (không phải index) |
| `charAt(int index)` | Lấy ký tự tại vị trí index | `"animals".charAt(0)` → `'a'` | `charAt(7)` → lỗi `StringIndexOutOfBoundsException` |
| `indexOf(char/String)` | Tìm vị trí đầu tiên của ký tự/chuỗi | `"animals".indexOf('a')` → `0` | Không tìm thấy → trả về `-1`, không gây lỗi |
| `indexOf(char/String, fromIndex)` | Bắt đầu tìm từ vị trí fromIndex | `"animals".indexOf('a', 4)` → `4` | |
| `substring(beginIndex)` | Lấy chuỗi con từ vị trí đến cuối | `"animals".substring(3)` → `"mals"` | |
| `substring(beginIndex, endIndex)` | Lấy chuỗi con từ vị trí begin đến **trước** end | `"animals".substring(3, 4)` → `"m"` | `substring(3,3)` → `""`, `substring(3,2)` → lỗi |
| `toUpperCase()` | Viết hoa toàn bộ chuỗi | `"animals".toUpperCase()` → `"ANIMALS"` | Không thay đổi chuỗi gốc (`String` là immutable) |
| `toLowerCase()` | Viết thường toàn bộ chuỗi | `"AbC".toLowerCase()` → `"abc"` | |
| `equals(Object)` | So sánh chuỗi (phân biệt hoa thường) | `"abc".equals("ABC")` → `false` | Chỉ trả về true khi giống 100% |
| `equalsIgnoreCase(String)` | So sánh chuỗi không phân biệt hoa thường | `"abc".equalsIgnoreCase("ABC")` → `true` | |
| `startsWith(String)` | Kiểm tra chuỗi bắt đầu bằng... | `"abc".startsWith("a")` → `true` | Phân biệt hoa thường |
| `endsWith(String)` | Kiểm tra chuỗi kết thúc bằng... | `"abc".endsWith("c")` → `true` | Phân biệt hoa thường |
| `replace(char, char)` | Thay thế ký tự | `"abcabc".replace('a','A')` → `"AbcAbc"` | |
| `replace(CharSequence, CharSequence)` | Thay thế chuỗi con | `"abcabc".replace("a","A")` → `"AbcAbc"` | |
| `contains(CharSequence)` | Kiểm tra chuỗi chứa chuỗi con | `"abc".contains("b")` → `true` | Không tìm thấy → `false`, phân biệt hoa thường |
| `trim()` | Xóa khoảng trắng đầu và cuối chuỗi | `" abc ".trim()` → `"abc"` | Loại bỏ tab (`\t`), newline (`\n`), space |
| `strip()` | Giống `trim()` nhưng hỗ trợ Unicode | Java 11+ | |
| `stripLeading()` | Xóa khoảng trắng đầu chuỗi | Java 11+ | |
| `stripTrailing()` | Xóa khoảng trắng cuối chuỗi | Java 11+ | |

---

### 📌 **TIPS GHI NHỚ**
- Chỉ `charAt()` và `substring()` có thể gây **exception nếu sai index**.
- `indexOf()` trả về `-1` nếu không tìm thấy – **không gây lỗi**.
- Các phương thức xử lý chữ hoa/thường như `equalsIgnoreCase`, `toUpperCase`, `toLowerCase` rất phổ biến.
- `replace()` có **2 phiên bản**: dùng `char` hoặc dùng `CharSequence`.
- `substring(begin, end)` → nhớ **end không được vượt quá chiều dài** và phải `end >= begin`.

---