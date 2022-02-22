package com.meinil.metms.client.model.dto;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class ExperienceDto {
    private String id;
    private String title;
    private String stuName;
    private boolean finished;
    private LocalDate createdTime;
    private String correct;

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStuName() {
        return stuName;
    }

    public boolean isFinished() {
        return finished;
    }

    public LocalDate getCreatedTime() {
        return createdTime;
    }
}
