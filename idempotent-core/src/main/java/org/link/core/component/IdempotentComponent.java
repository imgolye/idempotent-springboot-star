package org.link.core.component;

import org.link.core.service.IdempotentAuthorizationProcessor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 幂等校验实现类
 *
 * @author gol
 */
@Component
public class IdempotentComponent {

    private final IdempotentAuthorizationProcessor idempotentAuthorizationProcessor;

    public IdempotentComponent(IdempotentAuthorizationProcessor idempotentAuthorizationProcessor) {
        this.idempotentAuthorizationProcessor = idempotentAuthorizationProcessor;
    }

    /**
     * 幂等校验
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间,默认毫秒级别
     * @return boolean
     */
    public boolean idempotentCheck(String idempotentKey, Long idempotentTime) {
        return idempotentAuthorizationProcessor.execute(idempotentKey, idempotentTime);
    }

    /**
     * 幂等校验
     *
     * @param idempotentKey   幂等key
     * @param idempotentTime  幂等时间,默认毫秒级别
     * @param throwsException 是否抛出异常
     * @return boolean
     */
    public boolean idempotentCheck(String idempotentKey, Long idempotentTime, boolean throwsException) {
        return idempotentAuthorizationProcessor.execute(idempotentKey, idempotentTime, throwsException);
    }

    /**
     * 幂等校验
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间
     * @param timeUnit       时间策略
     * @return boolean
     */
    public boolean idempotentCheck(String idempotentKey, Long idempotentTime, TimeUnit timeUnit) {
        return idempotentAuthorizationProcessor.execute(idempotentKey, idempotentTime, timeUnit);
    }

    /**
     * 幂等校验
     *
     * @param idempotentKey   幂等key
     * @param idempotentTime  幂等时间
     * @param timeUnit        时间策略
     * @param throwsException 是否抛出异常
     * @return boolean
     */
    public boolean idempotentCheck(String idempotentKey, Long idempotentTime, TimeUnit timeUnit, boolean throwsException) {
        return idempotentAuthorizationProcessor.execute(idempotentKey, idempotentTime, timeUnit, throwsException);
    }

}
