package com.meinil.metms.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.metms.server.mapper.ExperienceMapper;
import com.meinil.metms.server.model.Experience;
import com.meinil.metms.server.model.dto.ExperienceDto;
import com.meinil.metms.server.service.ExperienceService;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
public class ExperienceServiceImpl extends ServiceImpl<ExperienceMapper, Experience> implements ExperienceService {
    @Override
    public Result insertExperience(Experience experience) {
        int i = baseMapper.insertExperience(experience);
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

    @Override
    public void updateExperience(ExperienceDto experience) {
        Experience e = new Experience();
        e.setId(experience.getId());
        e.setContent(experience.getContent());
        e.setCorrect(experience.getCorrect());
        e.setLevel(experience.getLevel());

        baseMapper.updateById(e);
    }
}
