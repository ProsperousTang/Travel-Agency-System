package com.dtss.service;

import com.dtss.entity.Result;

import java.util.Map;

public interface MobileOrderService {
    Result order(Map map) throws Exception;
}
