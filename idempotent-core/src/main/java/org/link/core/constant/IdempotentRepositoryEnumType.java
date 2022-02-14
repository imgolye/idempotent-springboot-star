package org.link.core.constant;


/**
 * 计数器枚举类型
 * @author gol
 */
public enum IdempotentRepositoryEnumType {

    /**
     * 基于单机计数器
     */
    STAND_ALONE("stand_alone"),

    /**
     * 基于redis计数器
     */
    REDIS("redis"),

    /**
     * 基于etcd计数器
     */
    ETCD("etcd"),

    /**
     * 基于zookeeper计数器
     */
    ZOOKEEPER("zookeeper"),

    ;

    private final String type;

    IdempotentRepositoryEnumType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
