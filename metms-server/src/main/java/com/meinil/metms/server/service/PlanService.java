package com.meinil.metms.server.service;

import com.meinil.metms.server.mapper.PlanMapper;
import com.meinil.metms.server.model.Plan;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
public class PlanService {
    @Autowired
    private PlanMapper planMapper;

    public Result insertPlan(Plan plan) {
        int i = planMapper.insertPlan(plan);
        Result result = new Result();
        if (i != 0) {
            result.setCode(200);
            result.setMessage("ok");
        } else {
            result.setCode(400);
            result.setMessage("插入失败");
        }
        return result;
    }
}
