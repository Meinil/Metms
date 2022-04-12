package com.meinil.metms.server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
@TableName("tb_experience")
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String planId;
    private String stuId;
    private String content;
    private boolean finished;
    private LocalDate createdTime;
    private String correct;
    private Character level;
}
