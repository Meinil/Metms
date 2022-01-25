module metms.client {
    requires javafx.controls;
    requires java.net.http;
    requires fastjson;

    requires com.jfxrouter;
    requires metms.commons;

    exports com.meinil.metms.client;
    exports com.meinil.metms.client.view;
    exports com.meinil.metms.client.component;
}