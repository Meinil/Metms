package com.meinil.metms.server.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
public class Plan {
    private String id;
    private String name;
    private String position;
    private Date startTime;
    private Date endTime;
    private String content;
    private String teacherId;
}
