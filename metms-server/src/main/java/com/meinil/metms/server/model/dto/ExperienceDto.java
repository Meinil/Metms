package com.meinil.metms.server.model.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
public class ExperienceDto {
    /**
     * 学生提交计划的id
     */
    private String id;
    /**
     * 导师课标题
     */
    private String title;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 是否批改
     */
    private boolean finished;
    /**
     * 创建时间
     */
    private LocalDate createdTime;
    /**
     * 批改内容
     */
    private String correct;
}
