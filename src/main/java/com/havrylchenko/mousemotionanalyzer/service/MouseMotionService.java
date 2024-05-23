package com.havrylchenko.mousemotionanalyzer.service;

import com.havrylchenko.mousemotionanalyzer.listener.GlobalMouseListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MouseMotionService {

    private GlobalMouseListener globalMouseListener;

    @Autowired
    public MouseMotionService(GlobalMouseListener globalMouseListener) {
        this.globalMouseListener = globalMouseListener;
    }

    public void startMouseMotionCapturing() {
        globalMouseListener.startMouseMotionCapture();
    }
}
