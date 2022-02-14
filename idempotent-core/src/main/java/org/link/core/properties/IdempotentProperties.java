package org.link.core.properties;

import org.link.core.constant.IdempotentConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的系统配置类
 * @author gol
 */
@Configuration
public class IdempotentProperties {

    /**
     * 应用名称
     */
    private String applicationName;

    @Value("${spring.application.name:}")
    public void setApplicationName(String prefix) {
        this.applicationName = prefix + IdempotentConstant.IDEMPOTENT_SPLIT;
    }

    public String getApplicationName() {
        return applicationName;
    }

}

