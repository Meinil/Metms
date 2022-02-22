package com.meinil.metms.server.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
public class Experience {
    private String id;
    private String name;
    private String planId;
    private String stuId;
    private String content;
    private boolean finished;
    private LocalDate createdTime;
    private String correct;
}
