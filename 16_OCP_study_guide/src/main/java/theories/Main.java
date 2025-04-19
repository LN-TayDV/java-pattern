package theories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static class Airplane {
        static int start = 2;
        final int end;
        public Airplane(int x) {
            x = 4;
            end = x;
        }
        public void fly(int distance) {
            System.out.print(end-start+" ");
            System.out.print(distance);
        }

    }

    public static void main(String[] start) {
        //new Airplane(10).fly(5);

//        var line = new StringBuilder("-");  // Dùng chuỗi hợp lệ
//        var anotherLine = line.append("-"); // Thêm một dòng mới vào StringBuilder
//
//        System.out.print(line == anotherLine);  // Kiểm tra xem line và anotherLine có trỏ đến đối tượng giống nhau không
//        System.out.print(" ");
//        System.out.print(line.length());  // In chiều dài của line

//        var line = new String("-");               // Tạo chuỗi mới với ký tự '-' và xuống dòng
//        var anotherLine = line.concat("-");       // Nối thêm chuỗi mới => tạo ra đối tượng mới
//
//        System.out.print(line == anotherLine);      // So sánh địa chỉ bộ nhớ => false
//        System.out.print(" ");
//        System.out.print(line.length());

//        var sb = new StringBuilder("radical")
//            .insert(sb.length(),
//                "robots");
//        System.out.println(sb);

//        int time = 9;
//        int day = 3;
//        var dinner = ++time>= 10 ? day-- <= 2
//            ? "Takeout" : "Salad" : "Leftovers";
//
//        System.out.print(dinner);

//

//        bool b = null;
//        Bool bl = null;
//        int i = null;
//        Integer in = null;
//        String s = null;

//        int height = 2, length = 3;
//        boolean w = height> 1 | --length < 4;
//        var x = height!=2 ? length++ : height;
//        boolean z = height % length == 0;
//        System.out.println(w + " - " + x + " - " + z);

//        var sb = new StringBuilder();
//        sb.append("red");         // sb = "red"
//        sb.deleteCharAt(0);       // xóa ký tự tại vị trí 0 → "ed"
//        sb.delete(1, 2);          // xóa từ index 1 đến < 2 → xóa 'd' → "e"
//        System.out.println(sb);   // in ra: e
//
//        boolean carrot = true;
//        Boolean potato = false;
//        var broccoli = true;
//        carrot = carrot & potato;//false
//        broccoli = broccoli ? !carrot : potato;//!fasle -> true
//        potato = !broccoli ^ carrot; //!true ^ false -> false ^ false -> false
//        System.out.println(carrot + " , " + potato + " , " + broccoli);

        var babies = Arrays.asList("chick" , "cygnet" ,      "duckling");
        babies.replaceAll(x -> {
            var newValue = "baby";
            return newValue;
        });
        System.out.println(babies);
    }

}

abstract class Bird {
    private final void fly() { System.out.println("Bird"); }
    protected Bird() { System.out.print("Wow-"); }
}

