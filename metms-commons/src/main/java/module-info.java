/**
 * @Author Meinil
 * @Version 1.0
 */
module metms.commons {
    requires fastjson;
    requires lombok;

    opens com.meinil.metms.commons to fastjson;

    exports com.meinil.metms.commons;
}