module metms.client {
    requires javafx.controls;
    requires java.net.http;
    requires fastjson;
    requires javafx.fxml;

    opens com.meinil.metms.client.controller to javafx.fxml;
    opens com.meinil.metms.client.component to javafx.fxml;
    opens com.meinil.metms.client.controller.admin to javafx.fxml;
    opens com.meinil.metms.client.controller.teacher to javafx.fxml;
    opens com.meinil.metms.client.controller.student to javafx.fxml;
    opens com.meinil.metms.client.model to fastjson;
    opens com.meinil.metms.client.model.dto to fastjson;
    opens com.meinil.metms.client.model.vo to fastjson;
    opens com.meinil.metms.client.utils to fastjson;

    exports com.meinil.metms.client;
    exports com.meinil.metms.client.model;
    exports com.meinil.metms.client.model.dto;
    exports com.meinil.metms.client.model.vo;
    exports com.meinil.metms.client.controller;
    exports com.meinil.metms.client.component;
    exports com.meinil.metms.client.utils;
    exports com.meinil.metms.client.controller.admin;
    exports com.meinil.metms.client.controller.teacher;
    exports com.meinil.metms.client.controller.student;
}