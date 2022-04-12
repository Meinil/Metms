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
    private String content;
    private String correct;
    private String level;

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

    public void setContent(String content) {
        this.content = content;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getContent() {
        return content;
    }

    public String getCorrect() {
        return correct;
    }

    public String getLevel() {
        return level;
    }
}
