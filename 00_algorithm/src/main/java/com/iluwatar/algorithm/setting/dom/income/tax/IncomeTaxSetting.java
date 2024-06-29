package com.iluwatar.algorithm.setting.dom.income.tax;

import java.util.List;

public interface IncomeTaxSetting<T> {
    // Phương thức mặc định, nếu không muốn thực thi trong các lớp con
    List<T> get();
}
