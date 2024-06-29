package com.iluwatar.algorithm.setting.dom.income.tax;

import java.util.List;

public abstract class IncomeTaxSetting<T> {
    // Phương thức mặc định, nếu không muốn thực thi trong các lớp con
    public abstract List<T> get();
}
