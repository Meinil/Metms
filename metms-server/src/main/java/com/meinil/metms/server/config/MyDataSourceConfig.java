package com.meinil.metms.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Configuration
public class MyDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") //绑定配置文件
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }
}
