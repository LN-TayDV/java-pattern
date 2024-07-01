package com.iluwatar.algorithm.setting.ws.controller;

import com.iluwatar.algorithm.setting.app.IncomeTaxSettingDto;
import com.iluwatar.algorithm.setting.app.TaxSettingFinder;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSettingRepository;
import java.util.List;

public class TaxSettingWs {

    private TaxSettingFinder taxSettingFinder;

    public IncomeTaxSettingDto get (String historyId, int modeValue) {
        return taxSettingFinder.get(historyId, modeValue);
    }
}
