package org.link.core.service;


import org.link.core.exception.IdempotentException;

import java.util.concurrent.TimeUnit;

/**
 * 幂等处理器，封装不同类型的幂等处理逻辑
 * @author gol
 */
public interface IdempotentAuthorizationProcessor {

    /**
     * 执行幂等请求
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间
     * @return boolean
     * @throws IdempotentException IdempotentException
     */
    boolean execute(String idempotentKey, Long idempotentTime) throws IdempotentException;

    /**
     * 执行幂等请求
     *
     * @param idempotentKey   幂等key
     * @param idempotentTime  幂等时间
     * @param throwsException 是否抛出异常
     * @return boolean
     * @throws IdempotentException IdempotentException
     */
    boolean execute(String idempotentKey, Long idempotentTime, boolean throwsException) throws IdempotentException;

    /**
     * 执行幂等请求
     *
     * @param idempotentKey  幂等key
     * @param idempotentTime 幂等时间
     * @param timeUnit       timeUnit
     * @return boolean
     * @throws IdempotentException IdempotentException
     */
    boolean execute(String idempotentKey, Long idempotentTime, TimeUnit timeUnit) throws IdempotentException;

    /**
     * 执行幂等请求
     *
     * @param idempotentKey   幂等key
     * @param idempotentTime  幂等时间
     * @param timeUnit        timeUnit
     * @param throwsException 是否抛出异常
     * @return boolean
     * @throws IdempotentException IdempotentException
     */
    boolean execute(String idempotentKey, Long idempotentTime, TimeUnit timeUnit, boolean throwsException) throws IdempotentException;

}
