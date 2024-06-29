package com.iluwatar.algorithm.setting.ws.controller;

import com.iluwatar.algorithm.setting.app.TaxSettingFinder;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSettingRepository;

public class TaxSettingWs {

    private TaxSettingFinder taxSettingFinder;

    public Object get (String historyId, int modeValue) {
        return taxSettingFinder.get(historyId, modeValue);
    }
}
