package com.meinil.metms.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.metms.server.mapper.ExperienceMapper;
import com.meinil.metms.server.model.Experience;
import com.meinil.metms.server.model.dto.ExperienceDto;
import com.meinil.metms.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Meinil
 * @Version 1.0
 */
public interface ExperienceService extends IService<Experience> {
    public Result insertExperience(Experience experience);

    public void updateExperience(ExperienceDto experience);
}
