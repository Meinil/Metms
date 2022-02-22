package com.meinil.metms.client.model;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Experience {
    private String id;
    private String name;
    private String planId;
    private String stuId;
    private String content;
    private boolean finished;
    private LocalDate createdTime;
    private String correct;

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setCreatedTime(LocalDate createdTime) {
        this.createdTime = createdTime;
    }

    public String getCorrect() {
        return correct;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPlanId() {
        return planId;
    }

    public String getStuId() {
        return stuId;
    }

    public String getContent() {
        return content;
    }

    public boolean isFinished() {
        return finished;
    }

    public LocalDate getCreatedTime() {
        return createdTime;
    }
}
