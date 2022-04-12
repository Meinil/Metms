package com.meinil.metms.server.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
@TableName("tb_plan")
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    private String id;
    private String name;
    private String position;
    private Date startTime;
    private Date endTime;
    private String content;
    private String teacherId;
}
