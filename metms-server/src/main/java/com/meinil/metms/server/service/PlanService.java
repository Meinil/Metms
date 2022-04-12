package com.meinil.metms.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.metms.server.model.Plan;
import com.meinil.metms.server.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @Author Meinil
 * @Version 1.0
 */
public interface PlanService extends IService<Plan> {
    public Result insertPlan(Plan plan);

    public Result getPlan(String id);

    public Result getPendingPlan(String account);
}
