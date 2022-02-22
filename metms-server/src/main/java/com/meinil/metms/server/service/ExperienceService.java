package com.meinil.metms.server.service;

import com.meinil.metms.server.mapper.ExperienceMapper;
import com.meinil.metms.server.model.Experience;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
public class ExperienceService {
    @Autowired
    private ExperienceMapper experienceMapper;

    public Result insertExperience(Experience experience) {
        int i = experienceMapper.insertExperience(experience);
        Result result;
        if (i > 0) {
            result = new Result(200);
            result.setMessage("ok");
        } else {
            result = new Result(400);
            result.setMessage("提交失败");
        }
        return result;
    }
}
