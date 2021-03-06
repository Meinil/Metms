package com.meinil.metms.client.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class Plan {
    private String id;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty position = new SimpleStringProperty();
    private SimpleObjectProperty<LocalDate> startTime = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> endTime = new SimpleObjectProperty<>();
    private SimpleStringProperty content = new SimpleStringProperty();
    private String teacherId;

    public Plan() {
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime.set(startTime);
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime.set(endTime);
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public LocalDate getStartTime() {
        return startTime.get();
    }

    public SimpleObjectProperty<LocalDate> startTimeProperty() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime.get();
    }

    public SimpleObjectProperty<LocalDate> endTimeProperty() {
        return endTime;
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }
}
