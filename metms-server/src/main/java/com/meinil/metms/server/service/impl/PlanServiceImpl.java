package com.meinil.metms.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.metms.server.mapper.ExperienceMapper;
import com.meinil.metms.server.mapper.PlanMapper;
import com.meinil.metms.server.model.Plan;
import com.meinil.metms.server.model.dto.ExperienceDto;
import com.meinil.metms.server.service.PlanService;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
    @Autowired
    private ExperienceMapper experienceMapper;

    @Override
    public Result insertPlan(Plan plan) {
        int i = baseMapper.insertPlan(plan);
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

    @Override
    public Result getPlan(String id) {
        List<Plan> plan = baseMapper.getPlan(id);
        Result result = new Result(200);
        result.setMessage("ok");
        result.put("plans", plan);
        return result;
    }

    @Override
    public Result getPendingPlan(String account) {
        Result result = new Result(200);
        List<ExperienceDto> experiences = experienceMapper.getAllExperience(account);
        result.put("experience", experiences);
        return result;
    }
}
