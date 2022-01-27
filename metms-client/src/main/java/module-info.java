module metms.client {
    requires javafx.controls;
    requires java.net.http;
    requires fastjson;

    requires com.jfxrouter;

    opens com.meinil.metms.client.controller to com.jfxrouter;
    opens com.meinil.metms.client.model to fastjson;

    exports com.meinil.metms.client;
    exports com.meinil.metms.client.view;
    exports com.meinil.metms.client.component;
    exports com.meinil.metms.client.model;
}