package com.meinil.metms.server.mapper;

import com.meinil.metms.server.model.Experience;
import com.meinil.metms.server.model.dto.ExperienceDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Mapper
public interface ExperienceMapper {
    @Insert("INSERT INTO tb_experience(id, plan_id, stu_id, content) VALUES(#{id}, #{planId}, #{stuId}, #{content})")
    public int insertExperience(Experience experience);

    @Select("SELECT distinct tb_experience.id, tb_experience.finished, name title, username stuName, created_time, correct FROM tb_experience INNER JOIN tb_user ON tb_user.account=tb_experience.stu_id INNER JOIN tb_plan ON tb_plan.id=tb_experience.plan_id INNER JOIN tb_connect ON tb_connect.teacher_id=#{account}")
    public List<ExperienceDto> getAllExperience(String account);
}
