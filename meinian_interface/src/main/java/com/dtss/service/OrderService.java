package com.dtss.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map findById(Integer id) throws Exception;

    List<Map<String, Object>> findMealCount();
}
