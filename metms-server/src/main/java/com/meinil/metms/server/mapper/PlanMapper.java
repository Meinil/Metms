package com.meinil.metms.server.mapper;

import com.meinil.metms.server.model.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Mapper
public interface PlanMapper {
    @Insert("INSERT INTO tb_plan(id, name, position, start_time, end_time, content, teacher_id) VALUES(#{id}, #{name}, #{position}, #{startTime}, #{endTime}, #{content}, #{teacherId})")
    public int insertPlan(Plan plan);
}
