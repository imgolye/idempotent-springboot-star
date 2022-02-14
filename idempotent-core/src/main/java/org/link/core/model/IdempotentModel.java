package org.link.core.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 幂等基类模型
 * @author gol

 */
public class IdempotentModel implements Serializable {

    private static final long serialVersionUID = -6020470039852318468L;

    /**
     * 幂等key
     */
    private String idempotentKey;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 时间策略
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public IdempotentModel(String idempotentKey, Long expireTime) {
        this.idempotentKey = idempotentKey;
        this.expireTime = expireTime;
    }

    public IdempotentModel(String idempotentKey, Long expireTime, TimeUnit timeUnit) {
        this.idempotentKey = idempotentKey;
        this.expireTime = expireTime;
        this.timeUnit = timeUnit;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getIdempotentKey() {
        return idempotentKey;
    }

    public void setIdempotentKey(String idempotentKey) {
        this.idempotentKey = idempotentKey;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "IdempotentModel{" +
                "idempotentKey='" + idempotentKey + '\'' +
                ", expireTime=" + expireTime +
                ", timeUnit=" + timeUnit +
                '}';
    }
}
